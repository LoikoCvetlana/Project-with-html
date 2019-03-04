package com.sportoras.web.controller;

import com.sportoras.database.entity.User;
import com.sportoras.service.dto.userDto.UserDetailCreateDto;
import com.sportoras.service.dto.userDto.UserDetailUpdateDto;
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

    @GetMapping("/change-user-detail")
    public String changeUserDetailSave(Model model) {
        model.addAttribute("userDetailUpdateDto", new UserDetailUpdateDto());
        return "/change-user-detail";
    }

    @PostMapping("/change-user-detail")
    public String changeUserDetailSave(UserDetailUpdateDto userDetailUpdateDto, Authentication authentication) {
        User user = userService.findUserByEmail(authentication.getName());
        Long id = userDetailService.findUserDetailByUser(user).getId();
        userDetailUpdateDto.setUser(user);
        userDetailUpdateDto.setId(id);
        userDetailService.updarteUserDetail(userDetailUpdateDto);
        return "redirect:/my-page";
    }

//    @PostMapping("/change-user-detail")
//    public String changeUserDetailSave(UserDetailUpdateDto userDetailUpdateDto, Authentication authentication) {
//        User user = userService.findUserByEmail(authentication.getName());
//        UserDetail userDetail = userDetailService.findUserDetailByUser(user);
////        userDetailUpdateDto.setUser(user);
//        userDetailService.updarteUserDetail(userDetailUpdateDto);
//        return "redirect:/my-page";
//    }
}
