package com.sportoras.web.controller;

import com.sportoras.database.entity.User;
import com.sportoras.service.dto.rewiewDto.RewiewDto;
import com.sportoras.service.service.RewiewService;
import com.sportoras.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RewiewController {

    private  final RewiewService rewiewService;
    private final UserService userService;

    @GetMapping("/rewiews")
    public String getAllRewiews(Model model) {
        List<RewiewDto> rewiews = rewiewService.allRewiews();
        model.addAttribute("rewiews", rewiews);
        return "rewiews";
    }
    @GetMapping("/rewiew-save")
    public String openRewiewSave(Model model) {
        model.addAttribute("rewiewDto", new RewiewDto());
        return "/rewiew-save";
    }

    @PostMapping("/rewiew-save")
    public String saveRewiew(RewiewDto rewiewDto, Authentication authentication) {
        User user = userService.findUserByEmail(authentication.getName());
        rewiewDto.setUser(user);
        rewiewService.saveRewiew(rewiewDto);
        return "redirect:/rewiews";
    }
}
