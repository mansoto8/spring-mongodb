package com.example.springmongodb.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Getter
@Setter
public class Review {
    private String userName;
    private int rating;
    private boolean approved;

    public Review(String userName, int rating, boolean approved) {
        this.userName = userName;
        this.rating = rating;
        this.approved = approved;
    }

    public Review(String userName) {
        this.userName = userName;
    }

}
