package com.spring.SpringJPA.Repository;


import com.spring.SpringJPA.Model.PlayerCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerCategoryRepository extends JpaRepository<PlayerCategory, Long> {
}
