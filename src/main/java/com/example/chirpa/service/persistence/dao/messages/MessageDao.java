package com.example.chirpa.service.persistence.dao.messages;

import com.example.chirpa.service.persistence.dao.SimpleCrudDao;
import com.example.chirpa.service.persistence.domain.Message;
import com.example.chirpa.service.persistence.exception.DataNotFoundException;

import javax.persistence.PersistenceException;
import java.util.List;

public interface MessageDao extends SimpleCrudDao<Message, Long> {

    public List<Message> findAll() throws PersistenceException, DataNotFoundException;

    public List<Message> findMessagesByFromUserName(String fromUserName) throws PersistenceException, DataNotFoundException;




}
