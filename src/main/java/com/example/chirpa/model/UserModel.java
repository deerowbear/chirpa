package com.example.chirpa.model;

import com.example.chirpa.service.remote.common.BaseRequest;

import java.util.ArrayList;
import java.util.List;

public class UserModel extends BaseRequest {


    private long userId;
    private String userName;

    public UserModel() {

    }

    List<FollowerModel> followerModels = new ArrayList<>();

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<FollowerModel> getFollowerModels() {
        return followerModels;
    }

    public void setFollowerModels(List<FollowerModel> followerModels) {
        this.followerModels = followerModels;
    }

}
