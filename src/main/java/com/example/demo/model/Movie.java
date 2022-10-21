package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "duration")
    private int duration;

    @Column(name = "first_presentation_date")
    private Date firstPresentationDate;

    @Column(name = "rating")
    private float rating;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "movie")
    private List<Ticket> tickets;
}
