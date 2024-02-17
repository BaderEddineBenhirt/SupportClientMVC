package com.practice.supportclient.controllers;

import com.practice.supportclient.models.ChatMessage;
import com.practice.supportclient.service.IAESEncryptionService;
import com.practice.supportclient.service.IChatService;
import com.practice.supportclient.service.impl.AESEncryptionServiceImpl;
import com.practice.supportclient.service.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
    public class ChatController {
    private final IChatService iChatService;
    private final KafkaProducer kafkaProducer;
    private final AESEncryptionServiceImpl encryptionService;
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) throws Exception {
        kafkaProducer.sendMessage("messaging", String.valueOf(chatMessage));
        iChatService.sendMessage(chatMessage);
        chatMessage.setContent(encryptionService.decrypt(chatMessage.getContent()));
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getUser().getUsername());
        return chatMessage;
    }
}

