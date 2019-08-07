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
public class Sponsorship {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "sponsorships")
    private Set<Player> players;

    public Sponsorship(String name) {
        this.name = name;
    }
}
