package com.spring.SpringJPA.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    private Integer age;

    private Integer number;

    @OneToOne(mappedBy = "player")
    private PlayerCategory playerCategory;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sponsorships",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "sponsorship_id"))
    private Set<Sponsorship> sponsorships = new HashSet<>();

    public Player(String name, Integer age, Integer number) {
        this.name = name;
        this.age = age;
        this.number = number;
    }
}
