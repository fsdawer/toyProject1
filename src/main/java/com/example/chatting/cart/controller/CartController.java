package com.example.chatting.cart.controller;

import com.example.chatting.account.helper.AccountHelper;
import com.example.chatting.cart.dto.CartRead;
import com.example.chatting.cart.repository.service.CartRequest;
import com.example.chatting.cart.repository.service.CartService;
import com.example.chatting.item.dto.ItemDto;
import com.example.chatting.item.repository.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ItemService itemService;
    private final AccountHelper accountHelper;

    @GetMapping("/api/cart/items")
    public ResponseEntity<?> readAll(HttpServletRequest request) {
        // 로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(request);

        // 장바구니 목록 먼저 조회
        List<CartRead> carts = cartService.findAll(memberId);

        // 조회한 장바구니 안에 있는 상품 아이디로 상품조회
        List<Integer> itemsIds = carts.stream().map(CartRead::getItemId).toList();

        // 상품 상세 데이터 조회
        List<ItemDto> items = itemService.selectAll(itemsIds);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // 장바구니에 상품 담기
    @PostMapping("/api/carts")
    public ResponseEntity<?> push(HttpServletRequest request, @RequestBody CartRequest cartReq) {
        //로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(request);

        // 해당 상품이 장바구니에 있는지 확인
        CartRead cart = cartService.find(memberId, cartReq.getItemId());

        //        //사용자가 장바구니에 넣으려는 상품이 이미 있으면 그대로 반환하고,
        //        //없으면 새로 장바구니에 저장한다.
        if (cart == null) {
            cartService.save(cartReq.toEntity(memberId));
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
