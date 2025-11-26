package com.example.chatting.order.dto;


import com.example.chatting.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// 사용자가 주문 창에서 주문할 때 입력하는 값들
// 이건 주문 요청 -> DTO -> Entity로 변환 -> DB에 저장
@Getter
@Setter
public class OrderRequestDto {
    private String name;
    private String optionType;   // call / put
    private String address;
    private String payment;
    private String cardNumber;
    private Long amount;
    private List<Integer> itemIds;

    public Order toEntity(Integer memberId) {
        return new Order(
                memberId,
                this.name,
                this.address,
                this.optionType,
                this.payment,
                this.cardNumber,
                this.amount
        );
    }
}
