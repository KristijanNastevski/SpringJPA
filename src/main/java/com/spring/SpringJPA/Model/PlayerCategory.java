package com.spring.SpringJPA.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class PlayerCategory {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String position;

    @OneToOne
    private Player player;

    public PlayerCategory(String position) {
        this.position = position;
    }
}
