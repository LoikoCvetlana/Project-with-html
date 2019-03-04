package com.sportoras.web.restController;

import com.sportoras.database.entity.User;
import com.sportoras.service.dto.productDto.ProductCreateDto;
import com.sportoras.service.dto.userDto.UserBasicDto;
import com.sportoras.service.dto.userDto.UserCreateDto;
import com.sportoras.service.service.UserSaveServise;
import com.sportoras.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@SessionAttributes(value = "user")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;
    private final UserSaveServise userSaveServise;
    private Logger LOGGER = LogManager.getLogger(UserController.class);

    @GetMapping(value = "/users/")
    public ResponseEntity<List<UserBasicDto>> listAllUsers() {
        List<UserBasicDto> users = userService.findAllUsers();
        if (users == null) {
            LOGGER.error("Users are not found");
            throw new EntityNotFoundException("Users not found");
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/user-info/{id}")
    public ResponseEntity<User> getProductById(@PathVariable("id") long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            LOGGER.error("User with id " + id + " not found.");
            throw new EntityNotFoundException("User not found");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user-save")
    public String saveNewUser(UserCreateDto userCreateDto) {
        userSaveServise.saveNewUser(userCreateDto);
        return "redirect:/my-page";
    }


    @PostMapping(value = "/user-save/")
    public ResponseEntity<ProductCreateDto> saveProduct(@RequestBody UserCreateDto userCreateDto) {
        if (userService.findUserByEmail(userCreateDto.getEmail()) != null) {
            LOGGER.error("This user already exists");
            throw new EntityExistsException("User already exists");
        }
        userSaveServise.saveNewUser(userCreateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/my-page")
//    public String userById(Model model, Authentication authentication) {
//        User user = userService.findUserByEmail(authentication.getName());
//        String email = user.getEmail();
//        model.addAttribute("user", user);
//        model.addAttribute("email", email);
//        return "/my-page";
//    }
}
