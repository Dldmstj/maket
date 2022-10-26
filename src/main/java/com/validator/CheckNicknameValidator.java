package com.validator;

import com.model.User;
import com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckNicknameValidator extends AbstractValidator<User> {
    private final UserRepository userRepository;
    @Override
    protected void doValidate(User user, Errors errors) {
        if (userRepository.existsByNickname(user.getNickname())) {
            errors.rejectValue("nickname", "닉네임 중복 오류", "이미 사용중인 닉네임 입니다.");
        }
    }
}
