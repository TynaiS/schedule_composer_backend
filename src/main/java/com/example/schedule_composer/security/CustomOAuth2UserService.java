package com.example.schedule_composer.security;

import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.entity.oauth2.UserPrincipal;
import com.example.schedule_composer.factory.OAuth2UserInfoFactory;
import com.example.schedule_composer.repository.UserRepository;
import com.example.schedule_composer.service.OAuth2UserInfo;
import com.example.schedule_composer.utils.types.AuthProvider;
import com.example.schedule_composer.utils.types.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String provider = userRequest.getClientRegistration().getRegistrationId();

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(provider, attributes);

        if (userInfo.getEmail() == null || userInfo.getEmail().isEmpty()) {
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }

        System.out.println("Inside oauth2 loadUser");
        System.out.println(attributes);
        System.out.println(provider);

        String email = oAuth2User.getAttribute("email");

        Optional<User> existingUser = userRepository.findByEmail(email);
        User user;


        if (existingUser.isEmpty()) {
            user = register(userInfo, provider);
        } else if(existingUser.get().getProvider() == AuthProvider.valueOf(provider.toUpperCase())){
            user = existingUser.get();
        } else {
            throw new OAuth2AuthenticationException("Account with email " + email + " already exists");
        }

        return UserPrincipal.create(user, attributes);
    }

    private User register(OAuth2UserInfo userInfo, String provider) {
        System.out.println("Inside registering new oauth2 loadUser");
        User user = User.builder()
                .name(userInfo.getName())
                .email(userInfo.getEmail())
                .password("")
                .isEmailVerified(true)
                .role(UserRole.VIEWER)
                .provider(AuthProvider.valueOf(provider.toUpperCase()))
                .isAccountNonLocked(true)
                .isAccountNonExpired(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();
        return userRepository.save(user);
    }
}
