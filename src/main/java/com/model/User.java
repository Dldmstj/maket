package com.model;


import com.model.type.BankType;
import com.model.type.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;     // PK, Auto_increment

    @Column(nullable = false, length = 30)
    private String userName;    // 아이디

    @Column(nullable = false, length = 100)
    private String pw;      // 비밀번호

    @Column(nullable = false, length = 50)
    private String name;        // 이름

    @Column(nullable = false, length = 50)
    private String phoneNum;        // 전화번호

    @Column(nullable = false, length = 200)
    private String address;     // 주소

    @Enumerated(EnumType.STRING)
    private GenderType gender;      // 성별

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;        // 생년월일

    @Enumerated(EnumType.STRING)
    private BankType bank;      // 은행

    @Column(nullable = false, length = 50)
    private String account;     // 계좌번호

    @Column(nullable = false, length = 50)
    private int payMoney;       // 페이머니 잔액

    @CreationTimestamp
    private Timestamp createDate;   // 가입일

}