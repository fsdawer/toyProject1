package com.example.chatting.order.entity;

import com.example.chatting.order.dto.OrderReadDto;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer memberId;

    @Column(length = 50 , nullable = false)
    private String name;

    @Column(length = 500, nullable = false)
    private String address;

    @Column(name = "option_type", length = 10, nullable = false)
    private String optionType;   // call / put

    @Column(length = 10, nullable = false)
    private String payment;

    @Column(length = 16)
    private String cardNumber;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created;

    public Order() {}


    public Order(Integer memberId, String name, String address, String optionType, String payment, String cardNumber, Long amount) {
        this.memberId = memberId;
        this.name = name;
        this.address = address;
        this.optionType = optionType;
        this.payment = payment;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public OrderReadDto toRead() {
        return OrderReadDto.builder()
                .id(id)
                .name(name)
                .address(address)
                .optionType(optionType)
                .payment(payment)
                .amount(amount)
                .created(created)
                .build();
    }
}