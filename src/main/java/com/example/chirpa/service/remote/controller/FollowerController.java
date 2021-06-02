package com.example.chirpa.service.remote.controller;

import com.example.chirpa.model.FollowerModel;
import com.example.chirpa.service.local.follow.FollowerLocalService;
import com.example.chirpa.service.remote.BaseRemoteServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("v1.FollowerController")
@RequestMapping("/ws/v1/follower")
public class FollowerController extends BaseRemoteServiceController {

    @Autowired
    FollowerLocalService followerLocalService;

    @RequestMapping(value = { "/list" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FollowerModel> list()  throws Exception {
        return followerLocalService.list();
    }

    @RequestMapping(value = { "/create" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public FollowerModel createFollower(@RequestBody FollowerModel followerModel)  throws Exception {
        return followerLocalService.create(followerModel);
    }



}
