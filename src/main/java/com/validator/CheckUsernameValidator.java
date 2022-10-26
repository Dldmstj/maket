package com.validator;

import com.model.User;
import com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckUsernameValidator extends AbstractValidator<User> {
    private final UserRepository userRepository;
    @Override
    protected void doValidate(User user, Errors errors) {
        if (userRepository.existsByUsername(user.getUsername())) {
            errors.rejectValue("username", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
        }
    }
}
