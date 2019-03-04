package com.sportoras.web.restController;

import com.sportoras.database.entity.Review;
import com.sportoras.database.entity.User;
import com.sportoras.service.dto.reviewDto.ReviewDto;
import com.sportoras.service.service.ReviewService;
import com.sportoras.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private Logger LOGGER = LogManager.getLogger(ReviewController.class);

    @GetMapping(value = "/reviews/")
    public ResponseEntity<List<ReviewDto>> listAllReviews() {
        List<ReviewDto> reviews = reviewService.findAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-review/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable("id") long id) {
        Optional<Review> review = reviewService.findById(id);
        if (!review.isPresent()) {
            LOGGER.error("Unable to delete. Review with id " + id + " not found");
            return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
        }
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/review-save/")
    public ResponseEntity<ReviewDto> saveReview(@RequestBody ReviewDto reviewDto, Authentication authentication) {
        User user = userService.findUserByEmail(authentication.getName());
        reviewDto.setUser(user);
        Review review = reviewService.saveReview(reviewDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
