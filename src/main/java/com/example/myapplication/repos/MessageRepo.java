package com.example.myapplication.repos;

import com.example.myapplication.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
        //CrudRepository<Message, Integer> {
    List<Message> findByTextContainingIgnoreCase (String text);
    //List<Message> findByTag(String tag);
}
