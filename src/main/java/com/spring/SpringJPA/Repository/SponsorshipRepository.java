package com.spring.SpringJPA.Repository;

import com.spring.SpringJPA.Model.Sponsorship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorshipRepository extends JpaRepository<Sponsorship, Long> {
}
