package com.example.chatting.order.controller;

import com.example.chatting.account.helper.AccountHelper;
import com.example.chatting.order.dto.OrderReadDto;
import com.example.chatting.order.dto.OrderRequestDto;
import com.example.chatting.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2")
public class OrderController {

    private final OrderService orderService;
    private final AccountHelper accountHelper;

    @GetMapping("/api/orders")
    public ResponseEntity<?> readAll(HttpServletRequest request) {
        // 로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(request);

        // 주문 목록
        List<OrderReadDto> orders = orderService.findAll(memberId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @GetMapping("/api/orders/{id}")
    public ResponseEntity<?> read(HttpServletRequest request, @PathVariable Integer id) {
        // 로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(request);

        // 회원 아이디로 조회 하는 거니까(사용자 화면에 보여짐) DTO타입 / service.findByUserId
        OrderReadDto orderRead = orderService.findByUserId(id, memberId);

        // 주문 상품이 없다면
        if(orderRead == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderRead, HttpStatus.OK);
    }



    @PostMapping("/api/orders")
    public ResponseEntity<?> add(HttpServletRequest request, @RequestBody OrderRequestDto orderRequestDto) {
        Integer memberId = accountHelper.getMemberId(request);

        // 주문 입력 -> orderRequestDto에 주문 정보가 들어가있음 / memberid로 등록
        orderService.order(orderRequestDto, memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
