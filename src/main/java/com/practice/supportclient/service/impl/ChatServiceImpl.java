package com.practice.supportclient.service.impl;

import com.practice.supportclient.models.ChatMessage;
import com.practice.supportclient.repositories.IChatMessageRepository;
import com.practice.supportclient.service.IChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements IChatService {

    private final SimpMessagingTemplate messagingTemplate;
    private final IChatMessageRepository chatMessageRepository;
    private final AESEncryptionServiceImpl encryptionService;


    public void sendMessage(ChatMessage chatMessage) throws Exception {


        if (chatMessage.getType() == ChatMessage.MessageType.CHAT) {
            // Save the chat message if you need to persist

            String encryptedContent = encryptionService.encrypt(chatMessage.getContent());

            chatMessage.setContent(encryptedContent);
           chatMessageRepository.save(chatMessage);

        }
        // Broadcast the message to everyone subscribing to the chat topic
       //messagingTemplate.convertAndSend("/topic/publicChatRoom", chatMessage);
    }

    public List<ChatMessage> getChatHistory() {
        // Fetch the chat history from the repository
        return chatMessageRepository.findAll();
    }

    public void notifyUsers(ChatMessage chatMessage) {
        // Notify users when someone joins or leaves the chat
        messagingTemplate.convertAndSend("/topic/publicChatRoom", chatMessage);
    }
}
