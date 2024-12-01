package com.substr.chat.controller;

import com.substr.chat.payload.MessageRequest;
import com.substr.chat.repository.RoomRepository;
import entities.Message;
import entities.Room;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@CrossOrigin("http://localhost:3000")
public class ChatController {
    private RoomRepository roomRepository;

    // Sending and Receiving messages
    @MessageMapping("/sendMessage/{roomId}") // "/app/sendMessage/roomId"
    @SendTo("/topic/room/{roomId}") // Subscribe
    public Message sendMessage(@DestinationVariable String roomId,
                               @RequestBody MessageRequest request){
        Room room = roomRepository.findByRoomId(request.getRoomId());
        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());

        if(room != null){
            room.getMessages().add(message);
            roomRepository.save(room);
        }else{
            throw new RuntimeException("Room Not found !");
        }

        return message;
    }

}
