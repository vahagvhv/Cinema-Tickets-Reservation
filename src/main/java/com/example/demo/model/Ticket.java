package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "row")
    private int row;

    @Column(name = "place")
    private int place;

    @Column(name = "price")
    private int price;

    @Column(name = "movie_date")
    private LocalDateTime movieDate;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "is_successfully_purchased")
    private boolean isSuccessfullyPurchased;

    @Column(name = "purchase_date")
    private Date purchaseDate;
}
