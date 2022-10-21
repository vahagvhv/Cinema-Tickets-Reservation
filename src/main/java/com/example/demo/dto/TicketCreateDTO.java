package com.example.demo.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCreateDTO {

    private int row;

    private int place;

    private int price;

    private LocalDateTime movieDate;

    private Integer hallId;

    private Integer userId;

    private Integer movieId;

    private boolean isSuccessfullyPurchased;

    private Date purchaseDate;
}
