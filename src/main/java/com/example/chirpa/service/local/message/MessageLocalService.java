package com.example.chirpa.service.local.message;

import com.example.chirpa.model.MessageModel;
import com.example.chirpa.service.persistence.exception.LocalServiceException;

import java.util.List;

public interface MessageLocalService {

    public List<MessageModel> list() throws LocalServiceException;

    public MessageModel findById(long id) throws LocalServiceException;

    public MessageModel create(MessageModel followerModel) throws LocalServiceException;

    public MessageModel update(long id, MessageModel messageMOdel) throws LocalServiceException;

    public void delete(long id) throws LocalServiceException;

    public List<MessageModel> findMessagesByFromUserName(String userName) throws LocalServiceException;

}
