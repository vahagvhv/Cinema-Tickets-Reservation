package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMovieDTO {

    private Integer ticketId;

    private int row;

    private int place;

    private int price;

    private LocalDateTime movieDate;

    private Integer hallId;

    private Integer userId;

    private Integer movieId;

    private String movieName;

    private int duration;
}
