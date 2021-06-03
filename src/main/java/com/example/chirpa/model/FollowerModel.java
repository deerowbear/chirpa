package com.example.chirpa.model;

import com.example.chirpa.service.remote.common.BaseRequest;

public class FollowerModel extends BaseRequest {

    private long followerId;
    private long userId;
    private String userName;

    public long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(long followerId) {
        this.followerId = followerId;
    }

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

}
