package com.example.chirpa.service.remote.controller;

import com.example.chirpa.model.MessageModel;
import com.example.chirpa.model.UserModel;
import com.example.chirpa.service.local.message.MessageLocalService;
import com.example.chirpa.service.persistence.domain.Message;
import com.example.chirpa.service.remote.BaseRemoteServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("v1.MessageController")
@RequestMapping("/ws/v1/message")
public class MessageController extends BaseRemoteServiceController {

    @Autowired
    MessageLocalService messageLocalService;

    @RequestMapping(value = { "/list" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageModel> list()  throws Exception {
        return messageLocalService.list();
    }

    @RequestMapping(value = { "/create" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageModel create(@RequestBody MessageModel messageModel)  throws Exception {
        return messageLocalService.create(messageModel);
    }

    @RequestMapping(value = { "/update/{id}" }, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageModel update(@PathVariable long id,@RequestBody MessageModel messageModel)  throws Exception {
        return messageLocalService.update(id, messageModel);
    }

    @RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable long id)  throws Exception {
        messageLocalService.delete(id);
    }

    @RequestMapping(value = { "/search/{fromUserName}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageModel> search(@PathVariable String fromUserName)  throws Exception {
        return messageLocalService.findMessagesByFromUserName(fromUserName);
    }



}
