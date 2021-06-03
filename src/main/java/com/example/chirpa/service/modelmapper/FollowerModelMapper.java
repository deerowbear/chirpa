package com.example.chirpa.service.modelmapper;

import com.example.chirpa.model.FollowerModel;
import com.example.chirpa.service.persistence.domain.Follower;
import com.example.chirpa.util.ModelMapper;
import com.example.chirpa.util.ModelMapperException;

public class FollowerModelMapper extends BaseModelMapper implements ModelMapper<FollowerModel, Follower> {


    @Override
    public FollowerModel toModel(Follower follower) throws ModelMapperException {
        FollowerModel followerModel = new FollowerModel();
        followerModel.setFollowerId(follower.getFollowerId());
        followerModel.setUserId(follower.getUserId());
        followerModel.setUserName(follower.getUserName());
        return followerModel;
    }


    @Override
    public Follower toDomain(FollowerModel followerModel) throws ModelMapperException {
        Follower follower = new Follower();
        if(followerModel.getFollowerId() != 0) {
            follower.setFollowerId(followerModel.getFollowerId());
        }
        follower.setUserId(followerModel.getUserId());
        follower.setUserName(followerModel.getUserName());
        return follower;
    }
}
