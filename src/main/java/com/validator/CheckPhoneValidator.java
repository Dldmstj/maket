package com.validator;

import com.model.User;
import com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckPhoneValidator extends AbstractValidator<User>{

    private final UserRepository userRepository;
    @Override
    protected void doValidate(User user, Errors errors) {
        if (userRepository.existsByPhone(user.getPhone())) {
            errors.rejectValue("phone", "휴대폰 번호 중복 오류", "이미 가입된 전화번호 입니다.");
        }
    }

}
