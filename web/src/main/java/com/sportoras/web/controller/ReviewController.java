package com.sportoras.web.controller;

import com.sportoras.database.entity.User;
import com.sportoras.service.dto.reviewDto.ReviewDto;
import com.sportoras.service.service.ReviewService;
import com.sportoras.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReviewController {

    private  final ReviewService reviewService;
    private final UserService userService;

    @GetMapping("/reviews")
    public String getAllReviews(Model model, Authentication authentication) {
        String email = userService.findUserByEmail(authentication.getName()).getEmail();
        List<ReviewDto> reviews = reviewService.findAllReviews();
        model.addAttribute("reviews", reviews);
        model.addAttribute("email", email);
        return "reviews";
    }

    @GetMapping("/delete-review")
    public String deleteReview(@RequestParam("id") Long id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews";
    }

    @GetMapping("/review-save")
    public String saveReview(Model model) {
        model.addAttribute("reviewDto", new ReviewDto());
        return "/review-save";
    }

    @PostMapping("/review-save")
    public String saveReview(ReviewDto reviewDto, Authentication authentication) {
        User user = userService.findUserByEmail(authentication.getName());
        reviewDto.setUser(user);
        reviewService.saveReview(reviewDto);
        return "redirect:/reviews";
    }
}
