package com.example.chatting.order.dto;

import com.example.chatting.item.dto.ItemDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

// 주문 결과를 클라이언트에게 보여줄 때
// 이건 조회니까 Entity -> DTO 변환 해줘야함 (Order에서 변환)
@Getter
@Setter
@Builder
public class OrderReadDto {

    private int id;
    private String name;
    private String address;
    private String payment;
    private Long amount;
    private LocalDateTime created;
    private List<ItemDto> items;
}
