package com.example.chirpa.service.local.message;

import com.example.chirpa.model.MessageModel;
import com.example.chirpa.model.UserModel;
import com.example.chirpa.service.local.user.UserLocalService;
import com.example.chirpa.service.modelmapper.MessageModelMapper;
import com.example.chirpa.service.persistence.dao.messages.MessageDao;
import com.example.chirpa.service.persistence.domain.Message;
import com.example.chirpa.service.persistence.exception.DataNotFoundException;
import com.example.chirpa.service.persistence.exception.LocalServiceException;
import com.example.chirpa.util.ModelUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.List;


@Service
public class MessageLocalServiceImpl implements MessageLocalService {

    private static final Logger LOG = Logger.getLogger(MessageLocalServiceImpl.class.getName());

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserLocalService userLocalService;

    @Override
    public List<MessageModel> list() throws LocalServiceException {
        try {
            List<Message> messages = messageDao.findAll();
            return ModelUtils.toModels(messages, MessageModelMapper.class);
        } catch (Exception ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }
    }

    @Override
    public MessageModel findById(long id) throws LocalServiceException {
        try {
            return ModelUtils.toModel(messageDao.findById(id), MessageModelMapper.class);
        } catch (Exception ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }
    }

    @Override
    public MessageModel create(MessageModel messageModel) throws LocalServiceException {
        List<UserModel> fromUserModels =  userLocalService.findByUserName(messageModel.getFromUserName());
        if(null == fromUserModels || fromUserModels.isEmpty()) throw new LocalServiceException("Error user with userid " + messageModel.getFromUserName() + " does not exist");

        List<UserModel> toUserModels =  userLocalService.findByUserName(messageModel.getToUserName());
        if(null == toUserModels || toUserModels.isEmpty()) throw new LocalServiceException("Error user with username " + messageModel.getToUserName() + " does not exist.");

        try {
            Message message = ModelUtils.toDomain(messageModel, MessageModelMapper.class);
            return ModelUtils.toModel(messageDao.create(message), MessageModelMapper.class);
        } catch (Exception ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }

    }

    @Override
    public MessageModel update(long id, MessageModel messageModel) throws LocalServiceException {
        try {
            MessageModel updateMessageModel = findById(id);

            Message message = ModelUtils.toDomain(updateMessageModel, MessageModelMapper.class);
            return ModelUtils.toModel(messageDao.update(message), MessageModelMapper.class);
        } catch (Exception ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }
    }

    @Override
    public void delete(long id) throws LocalServiceException {
        try {
            messageDao.deleteById(id);
        } catch (PersistenceException | DataNotFoundException ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }
    }

    @Override
    public List<MessageModel> findMessagesByFromUserName(String fromUserName) throws LocalServiceException {
        try {
            return ModelUtils.toModels(messageDao.findMessagesByFromUserName(fromUserName), MessageModelMapper.class);
        } catch (PersistenceException | DataNotFoundException ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }
    }




}
