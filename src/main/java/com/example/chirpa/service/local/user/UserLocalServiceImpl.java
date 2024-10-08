package com.example.chirpa.service.local.user;

import com.example.chirpa.model.UserModel;
import com.example.chirpa.service.local.follow.FollowerLocalService;
import com.example.chirpa.service.modelmapper.UserModelMapper;
import com.example.chirpa.service.persistence.dao.users.UserDao;
import com.example.chirpa.service.persistence.domain.User;
import com.example.chirpa.service.persistence.exception.DataNotFoundException;
import com.example.chirpa.service.persistence.exception.LocalServiceException;
import com.example.chirpa.util.ModelUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserLocalServiceImpl implements UserLocalService {

    private static final Logger LOG = Logger.getLogger(UserLocalServiceImpl.class.getName());

    @Autowired
    private UserDao userDao;

    @Autowired
    private FollowerLocalService followerLocalService;

    @Override
    public List<UserModel> list() throws LocalServiceException {
        try {

            return userDao.findAll().stream()
                    .map(userModel -> ModelUtils.toModel(userModel, UserModelMapper.class))
                    .peek(userModel -> userModel.setFollowerModels(followerLocalService.findFollowersById(userModel.getUserId()).orElse(new ArrayList<>())))
                    .collect(Collectors.toList());


        } catch (Exception ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }
    }

    @Override
    public UserModel findById(long id) throws LocalServiceException {
        try {
            UserModel userModel = ModelUtils.toModel(userDao.findById(id), UserModelMapper.class);
            userModel.setFollowerModels(followerLocalService.findFollowersById(userModel.getUserId()).orElse(new ArrayList<>()));
            return userModel;
        } catch (Exception ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }
    }

    @Override
    public UserModel create(UserModel userModel) throws LocalServiceException {
        try {
            if(this.findByUserName(userModel.getUserName()).size() > 0) throw new LocalServiceException("Duplicate Username");
            User user = ModelUtils.toDomain(userModel, UserModelMapper.class);
            return ModelUtils.toModel(userDao.create(user), UserModelMapper.class);
        } catch (Exception ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }
    }

    @Override
    public UserModel update(long id, UserModel userModel) throws LocalServiceException {
        try {
            UserModel updateUserModel = findById(id);

            User user = ModelUtils.toDomain(updateUserModel, UserModelMapper.class);
            return ModelUtils.toModel(userDao.update(user), UserModelMapper.class);
        } catch (Exception ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }
    }

    @Override
    public void delete(long id) throws LocalServiceException {
        try {
            userDao.deleteById(id);
        } catch (PersistenceException | DataNotFoundException ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }
    }

    @Override
    public List<UserModel> findByUserName(String userName) throws LocalServiceException {
        try {


            return userDao.findByUserName(userName).stream()
                    .map(user -> ModelUtils.toModel(user, UserModelMapper.class))
                    .peek(userModel -> userModel.setFollowerModels(followerLocalService.findFollowersById(userModel.getUserId()).orElse(new ArrayList<>())))
                    .collect(Collectors.toList());
        } catch (PersistenceException | DataNotFoundException ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }
    }

}
