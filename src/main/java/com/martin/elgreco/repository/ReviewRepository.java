package com.martin.elgreco.repository;

import com.martin.elgreco.domain.Review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByOrderByFechaReviewDesc();

    List<Review> findAllByDestacadaTrueOrderByFechaReviewDesc();
}
