package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

  @Modifying
  @Query("DELETE Message m WHERE m.messageId = :id")
  int deleteById(@Param("id") int id);

  @Modifying
  @Query("UPDATE Message m SET m.messageText = :text WHERE m.messageId = :id")
  int updateMessageById(@Param("id") int id, @Param("text") String text);

  List<Message> findByPostedBy(int id);

}
