package com.practice.supportclient.service.kafka;

import com.google.gson.Gson;
import com.practice.supportclient.models.ChatMessage;
import com.practice.supportclient.service.IChatService;
import com.practice.supportclient.service.impl.AESEncryptionServiceImpl;
import lombok.RequiredArgsConstructor;
import netscape.javascript.JSObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final IChatService iChatService;
    private final AESEncryptionServiceImpl encryptionService;


    @KafkaListener(topics = "messaging", groupId = "team")
    public void listen(String message) throws Exception {

        System.out.println("Received message: " + message);

    }
}
