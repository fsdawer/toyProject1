package com.example.chatting.account.controller;


import com.example.chatting.account.dto.AccountJoinRequest;
import com.example.chatting.account.dto.AccountLoginRequest;
import com.example.chatting.account.helper.AccountHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2")
public class AccountController {
    private final AccountHelper accountHelper;

    // 회원가입
    @PostMapping("/api/account/join")
    public ResponseEntity<?> join(@RequestBody AccountJoinRequest joinReq) {
        // 회원가입 할 때 하나라도 입력값이 비어있다면
        if (joinReq.getName() == null || joinReq.getLoginId() == null || joinReq.getLoginPw() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        accountHelper.join(joinReq);
        return ResponseEntity.ok().build();
    }


    // 로그인
    @PostMapping("/api/account/login")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response, @RequestBody AccountLoginRequest loginReq) {
        // 사용자가 아이디 비밀번호를 입력 안 했을때 오류처리
        if (loginReq.getLoginId() == null || loginReq.getLoginPw() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        String output = accountHelper.login(loginReq, request, response);
        // 사용자가 입력한 아이디 비밀번호가 DB에 없을때 오류처리
        if (output == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return new ResponseEntity<>(output, HttpStatus.OK);
    }


    // 로그인 여부 확인
    @GetMapping("/api/account/check")
    public ResponseEntity<?> check(HttpServletRequest request) {
        return new ResponseEntity<>(accountHelper.isLogin(request), HttpStatus.OK);
    }


    // 로그아웃
    @PostMapping("/api/account/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        accountHelper.Logout(request, response);
        return ResponseEntity.ok().build();
    }
}
