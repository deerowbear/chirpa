package com.example.chirpa.service.local.follow;

import com.example.chirpa.model.FollowerModel;
import com.example.chirpa.service.persistence.exception.LocalServiceException;

import java.util.List;
import java.util.Optional;

public interface FollowerLocalService {

    public List<FollowerModel> list() throws LocalServiceException;

    public FollowerModel findById(long id) throws LocalServiceException;

    public FollowerModel create(FollowerModel followerModel) throws LocalServiceException;

    public FollowerModel update(long id, FollowerModel followerModel) throws LocalServiceException;

    public void delete(long id) throws LocalServiceException;

    public List<FollowerModel> findByUserName(String userName) throws LocalServiceException;

    public Optional<List<FollowerModel>> findFollowersById(long userId);

}
