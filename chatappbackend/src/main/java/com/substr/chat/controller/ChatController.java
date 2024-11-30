package com.substr.chat.controller;

import com.substr.chat.repository.RoomRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rooms")
public class ChatController {
    private RoomRepository roomRepository;
    public void RoomController(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }
    // create room

    // get room

    // get messages of room
}
