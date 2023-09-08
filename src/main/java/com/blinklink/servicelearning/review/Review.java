package com.blinklink.servicelearning.review;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@Table(name = "bl_review")
@NoArgsConstructor(force = true)
public class Review {

    @Id
    private UUID Id;

    private Integer Rating;

    private String Comment;

    public Review(Integer Rating, String Comment){
        this.Id=UUID.randomUUID();
        this.Rating=Rating;
        this.Comment=Comment;
    }
}
