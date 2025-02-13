package com.wavy.server.domain.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto.Response createReview(@RequestBody ReviewDto.Request request) {
        Review savedReview = reviewRepository.save(request.toEntity());
        return new ReviewDto.Response(savedReview);
    }

    @GetMapping("/{id}")
    public ReviewDto.Response getReview(@PathVariable Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.map(ReviewDto.Response::new).orElse(null);
    }

    @GetMapping
    public List<ReviewDto.Response> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(ReviewDto.Response::new)
                .collect(Collectors.toList());
    }
}
