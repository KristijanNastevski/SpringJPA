package com.spring.SpringJPA;

import com.spring.SpringJPA.Model.Player;
import com.spring.SpringJPA.Model.PlayerCategory;
import com.spring.SpringJPA.Model.Sponsorship;
import com.spring.SpringJPA.Model.Team;
import com.spring.SpringJPA.Repository.PlayerCategoryRepository;
import com.spring.SpringJPA.Repository.PlayerRepository;
import com.spring.SpringJPA.Repository.SponsorshipRepository;
import com.spring.SpringJPA.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private PlayerCategoryRepository playerCategoryRepository;
	@Autowired
	private SponsorshipRepository sponsorshipRepository;

	public static void main(String[] args) { SpringApplication.run(SpringDataApplication.class, args); }

	@Override
	@Transactional
	public void run(String... args) {

		Player player = new Player("Kyle Kuzma", 24, 0);
		Player player1 = new Player("LeBron James", 34, 23);
		Player player2 = new Player("Rajon Rondo", 33, 9);
		Player player3 = new Player("Kawhi Leonard", 27, 15);

		Team team = new Team ("Los Angeles Lakers");
		Team team1 = new Team("Los Angeles Clippers");

		//One-to-Many - Many-to-One

		team.setPlayers(Stream.of(player, player1, player2).collect(Collectors.toSet()));
		team1.setPlayers(Stream.of(player3).collect(Collectors.toSet()));

		teamRepository.save(team);
		teamRepository.save(team1);

		System.out.println("TESTING ONE TO MANY RELATIONSHIP");
		System.out.println("Team name: " + teamRepository.findById(team.getId()).get().getName());
		teamRepository.findById(team.getId()).get().getPlayers().stream().forEach(player4 -> System.out.println("Player name: " + player4.getName()));

		player.setTeam(team);
		player1.setTeam(team);
		player2.setTeam(team);
		player3.setTeam(team1);

		playerRepository.save(player);
		playerRepository.save(player1);
		playerRepository.save(player2);
		playerRepository.save(player3);

		System.out.println("\n" + "TESTING MANY TO ONE RELATIONSHIP");
		System.out.println("Player name: " + playerRepository.findById(player.getId()).get().getName());
		System.out.println("Players Team Name: " + playerRepository.findById(player.getId()).get().getTeam().getName() + "\n");

		// One - to - One

		PlayerCategory playerCategory = new PlayerCategory("Wing");
		playerCategory.setPlayer(player);
		playerCategoryRepository.save(playerCategory);

		player.setPlayerCategory(playerCategory);
		playerRepository.save(player);

		System.out.println("\n" + "TESTING ONE-to-ONE RELATIONSHIP");
		System.out.println("Player name:" + playerRepository.findById(player.getId()).get().getName());
		System.out.println("Player position:" + playerRepository.findById(player.getId()).get().getPlayerCategory().getPosition() + "\n");

		// MANY-TO-MANY

		Sponsorship nikeSponsorship = new Sponsorship("Nike");
		Sponsorship sportsDrinkSponsorship = new Sponsorship("Gatorade");

		nikeSponsorship.setPlayers(Stream.of(player, player3, player2).collect(Collectors.toSet()));
		sportsDrinkSponsorship.setPlayers(Stream.of(player1, player2).collect(Collectors.toSet()));

		sponsorshipRepository.save(nikeSponsorship);
		sponsorshipRepository.save(sportsDrinkSponsorship);

		player.getSponsorships().add(nikeSponsorship);
		player1.getSponsorships().add(nikeSponsorship);
		player2.getSponsorships().add(nikeSponsorship);
		player2.getSponsorships().add(sportsDrinkSponsorship);
		player3.getSponsorships().add(nikeSponsorship);
		player3.getSponsorships().add(sportsDrinkSponsorship);

		playerRepository.save(player);
		playerRepository.save(player1);
		playerRepository.save(player2);
		playerRepository.save(player3);

		System.out.println("\n" + "TESTING MANY-TO-MANY RELATIONSHIP");
		System.out.println("\n" + "Players that have this sponsorship: ");
		System.out.println("Sponsorship name: " + sponsorshipRepository.findById(nikeSponsorship.getId()).get().getName());
		sponsorshipRepository.findById(nikeSponsorship.getId()).get().getPlayers().stream().forEach(player4 -> System.out.println("Player name: " + player4.getName()));

		System.out.println("\n" + "Players that have this sponsorship: ");
		System.out.println("Sponsorship name: " + sponsorshipRepository.findById(sportsDrinkSponsorship.getId()).get().getName());
		sponsorshipRepository.findById(sportsDrinkSponsorship.getId()).get().getPlayers().stream().forEach(player4 -> System.out.println("Player name: " + player4.getName()));

		System.out.println("\n" + "Sponsorship for this player: ");
		System.out.println("Player name: " + playerRepository.findById(player2.getId()).get().getName());
		playerRepository.findById(player2.getId()).get().getSponsorships().stream().forEach(sponsorship -> System.out.println("Sponsorship name: " + sponsorship.getName()));

	}
}
