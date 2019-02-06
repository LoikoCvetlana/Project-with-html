package com.sportoras.web.controller;

import com.sportoras.database.entity.User;
import com.sportoras.service.dto.userDto.UserCreateDto;
import com.sportoras.service.dto.userDto.UserDetailCreateDto;
import com.sportoras.service.service.UserDetailService;
import com.sportoras.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes(value = "user")
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailController {

    private final UserDetailService userDetailService;
    private final UserService userService;


    @GetMapping("/detail-save")
    public String openUserDetailSave(Model model) {
        model.addAttribute("userDetailCreateDto", new UserDetailCreateDto());
        return "/detail-save";
    }

    @PostMapping("/detail-save")
    public String saveUserDetail(UserDetailCreateDto userDetailCreateDto, Authentication authentication) {
        User user = userService.findUserByEmail(authentication.getName());
        userDetailCreateDto.setUser(user);
        userDetailService.saveUserDetail(userDetailCreateDto);
        return "redirect:/my-page";
    }
}
