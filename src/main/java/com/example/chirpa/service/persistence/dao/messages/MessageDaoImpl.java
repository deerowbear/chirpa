package com.example.chirpa.service.persistence.dao.messages;

import com.example.chirpa.service.persistence.dao.messages.repository.MessageRepository;
import com.example.chirpa.service.persistence.domain.Message;
import com.example.chirpa.service.persistence.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageDaoImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    @Transactional(readOnly = true , propagation = Propagation.REQUIRED)
    public List<Message> findAll() throws PersistenceException, DataNotFoundException {
        return messageRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true , propagation = Propagation.REQUIRED)
    public List<Message> findMessagesByFromUserName(String fromUserName) throws PersistenceException, DataNotFoundException {
        if ( fromUserName == null || fromUserName.isBlank()) {
            throw new DataNotFoundException("UserName id " + fromUserName + " does not exist");
        }
        try {
            return messageRepository.findMessagesByFromUserName(fromUserName);
        } catch (Exception e) {
            throw new DataNotFoundException(e);
        }
    }


    @Override
    @Transactional(readOnly = true , propagation = Propagation.REQUIRED)
    public Message findById(Long id) throws DataNotFoundException, PersistenceException {
        if ( id == null ) {
            throw new DataNotFoundException("Message id " + id + " does not exist");
        }
        try {
            return messageRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new DataNotFoundException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Message create(Message message) throws PersistenceException, PersistenceException {
        if ( message == null ) {
            throw new IllegalArgumentException("Message object can not be null");
        }
        return messageRepository.save(message);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Message update(Message message) throws PersistenceException, DataNotFoundException {
        if ( message == null ) {
            throw new IllegalArgumentException("Message object can not be null");
        }
        try {
            return messageRepository.saveAndFlush(message);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) throws DataNotFoundException, PersistenceException {
        if ( !messageRepository.existsById(id) ) {
            throw new DataNotFoundException("Message with id " + id + " does not exist");
        }
        try {
            Message message = messageRepository.getById(id);
            messageRepository.delete(message);
            messageRepository.flush();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    @Transactional(readOnly = true , propagation = Propagation.REQUIRED)
    public boolean exists(Long id) throws PersistenceException {
        if ( id == null ) {
            throw new IllegalArgumentException("Message id can not be null");
        }
        try {
            return messageRepository.existsById(id);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }


}
