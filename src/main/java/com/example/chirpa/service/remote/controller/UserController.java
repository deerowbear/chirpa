package com.example.chirpa.service.remote.controller;

import com.example.chirpa.model.UserModel;
import com.example.chirpa.service.local.user.UserLocalService;
import com.example.chirpa.service.remote.BaseRemoteServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("v1.UserController")
@RequestMapping("/ws/v1/user")
public class UserController extends BaseRemoteServiceController {

    @Autowired
    UserLocalService userLocalService;

    @RequestMapping(value = { "/list" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserModel> list()  throws Exception {
        return userLocalService.list();
    }

    @RequestMapping(value = { "/create" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel create(@RequestBody UserModel userModel)  throws Exception {
        return userLocalService.create(userModel);
    }

    @RequestMapping(value = { "/update/{id}" }, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel update(@PathVariable long id,@RequestBody UserModel userModel)  throws Exception {
        return userLocalService.update(id, userModel);
    }

    @RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable long id)  throws Exception {
        userLocalService.delete(id);
    }

    @RequestMapping(value = { "/search/{userName}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserModel> search(@PathVariable String userName)  throws Exception {
        return userLocalService.findByUserName(userName);
    }



}
