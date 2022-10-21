package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieCreateDTO {

    private String movieName;

    private int duration;

    private Date firstPresentationDate;

    private float rating;

    private String description;
}
