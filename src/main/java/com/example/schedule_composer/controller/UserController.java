package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.post.ChangePasswordDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.UserService;
import com.example.schedule_composer.utils.ApiConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordDTOPost changePasswordDTOPost,
                                                 @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.changePassword(changePasswordDTOPost, user.getEmail()));
    }
}
