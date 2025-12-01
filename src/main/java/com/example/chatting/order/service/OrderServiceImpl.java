package com.example.chatting.order.service;

import com.example.chatting.cart.repository.service.CartService;
import com.example.chatting.common.util.EncryptionUtils.EncryptionUtils;
import com.example.chatting.item.dto.ItemDto;
import com.example.chatting.item.repository.service.ItemService;
import com.example.chatting.order.dto.OrderReadDto;
import com.example.chatting.order.dto.OrderRequestDto;
import com.example.chatting.order.entity.Order;
import com.example.chatting.order.entity.OrderItem;
import com.example.chatting.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository; // ③
    private final OrderItemService orderItemService; // ④
    private final ItemService itemService; // ⑤
    private final CartService cartService; // ⑥

    // 특정 회원의 주문 전체 목록 조회
    @Override
    public List<OrderReadDto> findAll(Integer memberId) {
        return orderRepository.findByMemberIdOrderByIdDesc(memberId).stream().map(Order::toRead).toList();
    }

    // 특정 회원의 상세 주문 조회
    @Override
    public OrderReadDto findByUserId(Integer id, Integer memberId) {
        Optional<Order> orderOptional = orderRepository.findByIdAndMemberId(id, memberId);

        // 주문이 존재하는 경우
        if(orderOptional.isPresent()) {
            // OrderRepository에서 가져왔으니까 ->  DTO로 변환
            OrderReadDto order = orderOptional.get().toRead();

            // 주분 번호로 주문 상품 목록 조회
            List<OrderItem> orderItems = orderItemService.findAll(order.getId());

            // 주문 상품에서 itemId만 추출
            List<Integer> orderItemIds = orderItems.stream().map(OrderItem::getItemId).toList();

            // 상품 ID 목록으로 실제 상품 상세 정보 조회
            List<ItemDto> items = itemService.selectAll(orderItemIds);

            // 응답 값에 상품 리스트 데이터를 설정
            order.setItems(items);

            return order;
        }
        return null;
    }

    // 주문 내역 저장
    @Override
    @Transactional
    public void order(OrderRequestDto orderRequest, Integer memberId) {
        // 주문할 상품 정보 조회
        List<ItemDto> items = itemService.selectAll(orderRequest.getItemIds());
        //최종 결제 금액 계산을 위한 변수
        long amount = 0L;

        for (ItemDto item : items) {
            long discounted = item.getPrice() - (item.getPrice() * item.getDiscountPer() / 100);
            amount += discounted;
        }

        // 주문 요청에 최종 결제 금액 입력
        orderRequest.setAmount(amount);

        // 결제 수단이 카드일 때 카드 번호 암호화
        if ("card".equals(orderRequest.getPayment())) {
            orderRequest.setCardNumber(EncryptionUtils.encrypt(orderRequest.getCardNumber()));
        }

        // 주문 저장
        // OrderRequest DTO → Order 엔티티 변환
        //DB에 주문 생성 -> 생성된 주문 ID 자동 생성됨
        Order order = orderRepository.save(orderRequest.toEntity(memberId));

        // 주문 상품 데이터 생성
        List<OrderItem> newOrderItems = new ArrayList<>();

        // 주문한 상품 개수만큼 OrderItem 객체 생성
        // 주문 5번에 대해 상품 101, 202, 303이 들어감
        orderRequest.getItemIds().forEach((itemId) -> {
            OrderItem newOrderItem = new OrderItem(order.getId(), itemId);
            newOrderItems.add(newOrderItem);
        });

        // 주문 상품 데이터 저장
        orderItemService.saveAll(newOrderItems);

        // 장바구니 데이터 삭제(특정 회원)
        cartService.removeAll(order.getMemberId());
    }
}

