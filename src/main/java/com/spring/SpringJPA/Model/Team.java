package com.spring.SpringJPA.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToMany (cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "team")
    private Set<Player> players;

    public Team(String name) {
        this.name = name;
    }
}
