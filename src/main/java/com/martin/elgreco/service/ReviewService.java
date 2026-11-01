package com.martin.elgreco.service;

import com.martin.elgreco.domain.Prestacion;
import com.martin.elgreco.domain.Review;
import com.martin.elgreco.dto.review.ReviewRequest;
import com.martin.elgreco.dto.review.ReviewResponse;
import com.martin.elgreco.exception.ResourceNotFoundException;
import com.martin.elgreco.repository.PrestacionRepository;
import com.martin.elgreco.repository.ReviewRepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PrestacionRepository prestacionRepository;

    public ReviewService(ReviewRepository reviewRepository, PrestacionRepository prestacionRepository) {
        this.reviewRepository = reviewRepository;
        this.prestacionRepository = prestacionRepository;
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> findPublic() {
        return reviewRepository.findAllByOrderByFechaReviewDesc().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> findAllAdmin() {
        return reviewRepository.findAllByOrderByFechaReviewDesc().stream().map(this::toResponse).toList();
    }

    public ReviewResponse create(ReviewRequest request) {
        Review review = new Review();
        apply(review, request);
        return toResponse(reviewRepository.save(review));
    }

    public void delete(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reseña no encontrada: " + id));
        reviewRepository.delete(review);
    }

    private void apply(Review review, ReviewRequest request) {
        review.setAutor(request.autor());
        review.setEstrellas(request.estrellas());
        review.setComentario(request.comentario());
        review.setDestacada(Boolean.TRUE.equals(request.destacada()));
        review.setFechaReview(request.fechaReview() == null ? LocalDate.now() : request.fechaReview());
        if (request.prestacionId() == null) {
            review.setPrestacion(null);
        } else {
            Prestacion prestacion = prestacionRepository.findById(request.prestacionId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Prestación no encontrada: " + request.prestacionId()));
            review.setPrestacion(prestacion);
        }
    }

    private ReviewResponse toResponse(Review review) {
        Prestacion prestacion = review.getPrestacion();
        return new ReviewResponse(
                review.getId(),
                review.getAutor(),
                review.getEstrellas(),
                review.getComentario(),
                review.getDestacada(),
                review.getFechaReview(),
                prestacion == null ? null : prestacion.getId(),
                prestacion == null ? null : prestacion.getNombre(),
                prestacion == null ? null : prestacion.getSlug());
    }
}
