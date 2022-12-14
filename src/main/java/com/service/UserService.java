package com.service;

import com.model.User;
import com.repository.UserRepository;
import com.validator.CheckNicknameValidator;
import com.validator.CheckPhoneValidator;
import com.validator.CheckUsernameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public UserService() {
    }

    @Transactional
    public User findId(String name, String email) {
        User user = userRepository.findByNameAndEmail(name, email).orElseGet(() -> {
            return new User();
        });
        return user;
    }

    @Transactional
    public int userSave(User user) {
        String rawPassword = user.getPw(); // 1234 원문
        String encPassword = encoder.encode(rawPassword); // 해쉬
        user.setPw(encPassword);
        try {
            userRepository.save(user);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Transactional
    public void userUpdate(User user) {
        User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
            return new IllegalArgumentException("회원 찾기 실패");
        });

    }

    @Transactional
    public int charge(int id, int payMoney) {
        return userRepository.charge(id, payMoney);
    }

    @Transactional
    public int withdrow(int id, int payMoney) {
        return userRepository.withdraw(id, payMoney);
    }

    @Transactional
    public int buy(int id, int payMoney) {
        return userRepository.withdraw(id, payMoney);
    }

    @Transactional
    public int sell(int id, int payMoney) {
        return userRepository.charge(id, payMoney);
    }

    /* 회원가입 시, 유효성 및 중복 검사 */
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        /* 유효성 및 중복 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }        return validatorResult;
    }


}



