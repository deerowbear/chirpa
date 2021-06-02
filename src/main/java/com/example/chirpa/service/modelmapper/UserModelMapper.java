package com.example.chirpa.service.modelmapper;

import com.example.chirpa.model.UserModel;
import com.example.chirpa.service.persistence.domain.users.User;
import com.example.chirpa.util.ModelMapper;
import com.example.chirpa.util.ModelMapperException;

public class UserModelMapper extends BaseModelMapper implements ModelMapper<UserModel, User> {


    @Override
    public UserModel toModel(User user) throws ModelMapperException {
        UserModel userModel = new UserModel();
        userModel.setUserId(user.getUserId());
        userModel.setUserName(user.getUserName());
        return userModel;
    }

    @Override
    public User toDomain(UserModel userModel) throws ModelMapperException {
        User user = new User();
        if(userModel.getUserId() != 0) {
            user.setUserId(userModel.getUserId());
        }
        user.setUserName(userModel.getUserName());
        return user;
    }
}
