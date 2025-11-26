package com.example.chatting.item.entity;

import com.example.chatting.item.dto.ItemDto;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50 , nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String imgPath;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int discountPer;


    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime created;


    // item 엔티티를 -> itemDTO로 변환
    public ItemDto toRead() {
        return ItemDto.builder()
                .id(id)
                .name(name)
                .imgPath(imgPath)
                .price(price)
                .discountPer(discountPer)
                .build();
    }

}
