package com.example.chirpa.service.modelmapper;

import com.example.chirpa.model.MessageModel;
import com.example.chirpa.service.persistence.domain.Message;
import com.example.chirpa.util.ModelMapper;
import com.example.chirpa.util.ModelMapperException;

public class MessageModelMapper extends BaseModelMapper implements ModelMapper<MessageModel, Message> {


    @Override
    public MessageModel toModel(Message message) throws ModelMapperException {
        MessageModel messageModel = new MessageModel();
        messageModel.setMessageId(message.getMessageId());
        messageModel.setFromUserName(message.getFromUserName());
        messageModel.setToUserName(message.getToUserName());
        messageModel.setMessageBody(message.getMessageBody());
        return messageModel;
    }


    @Override
    public Message toDomain(MessageModel messageModel) throws ModelMapperException {
        Message message = new Message();
        if(messageModel.getMessageId() != 0) {
            message.setMessageId(messageModel.getMessageId());
        }
        message.setFromUserName(messageModel.getFromUserName());
        message.setToUserName(messageModel.getToUserName());
        message.setMessageBody(messageModel.getMessageBody());
        return message;
    }
}
