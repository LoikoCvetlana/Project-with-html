package com.sportoras.web.restController;

import com.sportoras.database.entity.UserDetail;
import com.sportoras.service.dto.productDto.ProductCreateDto;
import com.sportoras.service.dto.userDto.UserDetailCreateDto;
import com.sportoras.service.dto.userDto.UserDetailUpdateDto;
import com.sportoras.service.service.UserDetailService;
import com.sportoras.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@SessionAttributes(value = "user")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailController {

    private final UserDetailService userDetailService;
    private final UserService userService;
    private Logger LOGGER = LogManager.getLogger(UserDetailController.class);

    @PostMapping("/detail-save")
    public ResponseEntity<ProductCreateDto> saveProduct(@RequestBody UserDetailCreateDto userDetailCreateDto) {
        if (userDetailService.findUserDetailByUser(userDetailCreateDto.getUser()) != null) {
            LOGGER.error("This user detail already exists");
            throw new EntityExistsException("User detail already exists");
        }
        userDetailService.saveUserDetail(userDetailCreateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/change-user-detail")
    public String changeUserDetailSave(Model model) {
        model.addAttribute("userDetailUpdateDto", new UserDetailUpdateDto());
        return "/change-user-detail";
    }

    @PutMapping("/change-user-detail")
    public ResponseEntity<UserDetail> changeUserDetail(@PathVariable UserDetailUpdateDto userDetailUpdateDto){
        UserDetail userDetail = userDetailService.findUserDetailByUser(userDetailUpdateDto.getUser());
        Long id = userDetailUpdateDto.getId();
        if (userDetail == null) {
            LOGGER.error("Unable to cancel. User detail with id " + id + " not found.");
            throw new EntityNotFoundException("User detail not found");
        }
        UserDetail updatedUserDetail = userDetailService.updarteUserDetail(userDetailUpdateDto);
        return new ResponseEntity<>(updatedUserDetail, HttpStatus.OK);
    }
}
//    @PostMapping("/change-user-detail")
//    public String changeUserDetailSave(UserDetailUpdateDto userDetailUpdateDto, Authentication authentication) {
//        User user = userService.findUserByEmail(authentication.getName());
//        UserDetail userDetail = userDetailService.findUserDetailByUser(user);
////        userDetailUpdateDto.setUser(user);
//        userDetailService.updarteUserDetail(userDetailUpdateDto);
//        return "redirect:/my-page";
//    }

