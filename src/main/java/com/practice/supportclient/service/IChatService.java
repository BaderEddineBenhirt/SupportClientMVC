package com.practice.supportclient.service;

import com.practice.supportclient.models.ChatMessage;

import java.util.List;

public interface IChatService {
    public void sendMessage(ChatMessage chatMessage) throws Exception;

    public List<ChatMessage> getChatHistory() ;

    public void notifyUsers(ChatMessage chatMessage) ;
}
