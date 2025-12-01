package com.example.chatting.member.service;

import com.example.chatting.common.util.HashUtils;
import com.example.chatting.member.entitiy.Member;
import com.example.chatting.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseMemberService implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void save(String name, String login_id, String login_pw) {

        //중복 아이디 체크
        if(memberRepository.findByLoginId(login_id).isPresent()) {
            throw new IllegalArgumentException("중복된 이메일입니다.");
        }

        String loginPwSalt = HashUtils.generateSalt(16); // 솔트 생성
        String loginPwSalted = HashUtils.generateHash(login_pw, loginPwSalt); // 입력 패스워드에 솔트를 적용
        memberRepository.save(new Member(name, login_id, loginPwSalted, loginPwSalt)); // 회원 데이터 저장

    }


    // 회원 데이터 조회
    @Override
    public Member find(String login_id, String login_pw) {
        Optional<Member> memberOptional = memberRepository.findByLoginId(login_id); // 로그인 아이디로 회원 조회

        // 회원 데이터가 있으면
        if(memberOptional.isPresent()) {
            Member member = memberOptional.get();

            String loginPwSalt = memberOptional.get().getLoginPwSalt(); // 솔트 조회
            String loginPwSalted = HashUtils.generateHash(login_pw, loginPwSalt); // 입력 패스워드에 솔트 적용

            if(member.getLoginPw().equals(loginPwSalted)) { // 저장된 회원 로그인 패스워드와 일치하면
                return member;
            }
        }
        return null;
    }
}
