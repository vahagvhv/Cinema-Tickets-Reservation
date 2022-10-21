package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "halls")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "hall_name")
    private String hallName;

    @Column(name = "row_count")
    private int row_count;

    @Column(name = "place_count")
    private int place_count;

    @OneToMany(mappedBy = "hall")
    private List<Ticket> tickets;
}
