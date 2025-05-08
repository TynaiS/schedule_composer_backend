package com.example.schedule_composer.factory;

import com.example.schedule_composer.service.OAuth2UserInfo;
import com.example.schedule_composer.service.impl.oauth2.GoogleOAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {
  public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
    switch (registrationId.toLowerCase()) {
      case "google":
        return new GoogleOAuth2UserInfo(attributes);
//      case "facebook":
//        return new FacebookOAuth2UserInfo(attributes);
//      case "github":
//        return new GithubOAuth2UserInfo(attributes);
      default:
        throw new IllegalArgumentException("Unsupported OAuth2 provider");
    }
  }
}