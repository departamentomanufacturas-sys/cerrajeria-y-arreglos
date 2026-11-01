package com.martin.elgreco.publicapi;

import com.martin.elgreco.dto.review.ReviewResponse;
import com.martin.elgreco.service.ReviewService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public List<ReviewResponse> findAll() {
        return service.findPublic();
    }
}
