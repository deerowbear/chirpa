package com.example.chirpa.service.local.user;

import com.example.chirpa.model.UserModel;
import com.example.chirpa.service.persistence.exception.LocalServiceException;

import java.util.List;

public interface UserLocalService {

    public List<UserModel> list() throws LocalServiceException;

    public UserModel findById(long id) throws LocalServiceException;

    public UserModel create(UserModel userModel) throws LocalServiceException;

    public UserModel update(long id, UserModel userModel) throws LocalServiceException;

    public void delete(long id) throws LocalServiceException;

    public List<UserModel> findByUserName(String userName) throws LocalServiceException;

}
