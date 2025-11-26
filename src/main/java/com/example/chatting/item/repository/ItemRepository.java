package com.example.chatting.item.repository;

import com.example.chatting.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {


    //
    List<Item> findAllByIdIn(List<Integer> ids);
}
