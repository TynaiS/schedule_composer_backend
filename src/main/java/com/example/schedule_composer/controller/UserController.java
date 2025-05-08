package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.post.ChangePasswordDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.UserService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User API", description = "Endpoints managing user information")
public class UserController {

    private final UserService userService;

    @PostMapping("/change-password")
    @Operation(summary = "Change password", description = "Change password using access token")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordDTOPost changePasswordDTOPost,
                                                 @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.changePassword(changePasswordDTOPost, user.getEmail()));
    }
}
