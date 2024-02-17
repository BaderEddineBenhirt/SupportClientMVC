package com.practice.supportclient.repositories;

import com.practice.supportclient.models.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChatMessageRepository extends JpaRepository<ChatMessage,Long> {

}
