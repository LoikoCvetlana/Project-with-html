package com.sportoras.web.controller;

import com.sportoras.database.entity.User;
import com.sportoras.service.dto.userDto.UserBasicDto;
import com.sportoras.service.dto.userDto.UserCreateDto;
import com.sportoras.service.service.UserSaveServise;
import com.sportoras.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@SessionAttributes(value = "user")
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;
    private final UserSaveServise userSaveServise;

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<UserBasicDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user-info")
    public String productInfo(Model model,Authentication authentication, Long id) {
        User user = userService.findUserById(id);
        String email = userService.findUserByEmail(authentication.getName()).getEmail();
        model.addAttribute("user", user);
        model.addAttribute("email", email);
        return "/user-info";
    }

    @GetMapping("/user-save")
    public String openUserSave(Model model) {
        model.addAttribute("userCreateDto", new UserCreateDto());
        return "/user-save";
    }

    @PostMapping("/user-save")
    public String saveNewUser(UserCreateDto userCreateDto) {
        userSaveServise.saveNewUser(userCreateDto);
        return "redirect:/my-page";
    }

    @GetMapping("/my-page")
    public String userById(Model model, Authentication authentication) {
        User user = userService.findUserByEmail(authentication.getName());
        String email = user.getEmail();
        model.addAttribute("user", user);
        model.addAttribute("email", email);
        return "/my-page";
    }
}
