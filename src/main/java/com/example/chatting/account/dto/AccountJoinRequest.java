package com.example.chatting.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountJoinRequest {
    private String loginId;
    private String loginPw;
    private String name;
}
