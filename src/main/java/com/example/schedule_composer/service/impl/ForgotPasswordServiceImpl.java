package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.post.EmailDTOPost;
import com.example.schedule_composer.dto.post.ResetPasswordDTOPost;
import com.example.schedule_composer.dto.post.SetupSharedSetDTOPost;
import com.example.schedule_composer.dto.post.TimeSlotDTOPost;
import com.example.schedule_composer.email.MailBody;
import com.example.schedule_composer.entity.ForgotPassword;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.exception.VerificationCodeExpiredException;
import com.example.schedule_composer.repository.ForgotPasswordRepository;
import com.example.schedule_composer.repository.UserRepository;
import com.example.schedule_composer.service.ForgotPasswordService;
import com.example.schedule_composer.service.UserService;
import com.example.schedule_composer.utils.OtpGenerator;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ForgotPasswordServiceImpl implements ForgotPasswordService {


    private final UserService userService;
    private final EmailServiceImpl emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public ForgotPassword getByUser(User user) {
        return forgotPasswordRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("ForgotPassword not found for user" + user.getEmail()));
    }

    @Override
    public String sendVerifyEmail(EmailDTOPost emailDTO) {
        String message = "";
        String email = emailDTO.getEmail();
        if(userService.checkIsExists(email)){
            User user = userService.getByEmail(email);
            String verificationCode = OtpGenerator.otpGenerator();
            ForgotPassword fp = ForgotPassword.builder()
                    .verificationCode(passwordEncoder.encode(verificationCode))
                    .expirationTime(LocalDateTime.now().plusMinutes(1))
                    .user(user)
                    .build();

            forgotPasswordRepository.save(fp);
            sendVerificationEmail(user, verificationCode);
            message = "Account verification email sent";

        } else {
            message = "Email not registered";
        }

        return message;
    }

    @Override
    public String resetPassword(ResetPasswordDTOPost resetPasswordDTOPost) {
        String email = resetPasswordDTOPost.getEmail();
        String verificationCode = resetPasswordDTOPost.getVerificationCode();

        User user = userService.getByEmail(email);

        ForgotPassword fp = forgotPasswordRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("No reset request found for email: " + email));

        if (!passwordEncoder.matches(verificationCode, fp.getVerificationCode())) {
            throw new RuntimeException("Invalid verification code for email: " + email);
        }

        if(fp.getExpirationTime().isBefore(LocalDateTime.now())) {
            forgotPasswordRepository.deleteById(fp.getId());
            throw new VerificationCodeExpiredException("Verification code expired");
        }

        String encodedPassword = passwordEncoder.encode(resetPasswordDTOPost.getNewPassword());
        userRepository.updatePassword(email, encodedPassword);

        return "Password has been changed!";
    }

    @Override
    public void sendVerificationEmail(User user, String verCode) {
        String subject = "Forgot password email verification";
        String verificationCode = "VERIFICATION CODE " + "<div style=\"font-size: 18px; font-weight: bold; color: black;\">" + verCode + "</div>" ;
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Welcome to Schedule Composer app!</h2>"
                + "<p style=\"font-size: 16px;\">Please enter the verification code below to update password:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try {
            MailBody mailBody = MailBody.builder()
                    .to(user.getEmail())
                    .subject(subject)
                    .text(htmlMessage)
                    .build();
            emailService.sendVerificationEmail(mailBody);
        } catch (MessagingException e) {
            // Handle email sending exception
            e.printStackTrace();
        }
    }
}
