package com.practice.supportclient.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private MessageType type;
    private String content;
    private Date sentAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sender" /*, nullable=false*/)
    private UserEntity user;



    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

}


