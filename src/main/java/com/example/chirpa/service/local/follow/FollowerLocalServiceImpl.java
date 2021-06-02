package com.example.chirpa.service.local.follow;

import com.example.chirpa.model.FollowerModel;
import com.example.chirpa.model.UserModel;
import com.example.chirpa.service.local.user.UserLocalService;
import com.example.chirpa.service.modelmapper.FollowerModelMapper;
import com.example.chirpa.service.persistence.dao.follower.FollowerDao;
import com.example.chirpa.service.persistence.domain.follower.Follower;
import com.example.chirpa.service.persistence.exception.DataNotFoundException;
import com.example.chirpa.service.persistence.exception.LocalServiceException;
import com.example.chirpa.util.ModelUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.List;


@Service
public class FollowerLocalServiceImpl implements FollowerLocalService {

    private static final Logger LOG = Logger.getLogger(FollowerLocalServiceImpl.class.getName());

    @Autowired
    private FollowerDao followerDao;

    @Autowired
    private UserLocalService userLocalService;

    @Override
    public List<FollowerModel> list() throws LocalServiceException {
        try {
            List<Follower> followers = followerDao.findAll();
            return ModelUtils.toModels(followers, FollowerModelMapper.class);
        } catch (Exception ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }
    }

    @Override
    public FollowerModel findById(long id) throws LocalServiceException {
        try {
            return ModelUtils.toModel(followerDao.findById(id), FollowerModelMapper.class);
        } catch (Exception ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }
    }

    @Override
    public FollowerModel create(FollowerModel followerModel) throws LocalServiceException {
        UserModel currentUserModel =  userLocalService.findById(followerModel.getUserId());
        if(null == currentUserModel) throw new LocalServiceException("Error user with userid " + followerModel.getUserId() + " does not exist");

        List<UserModel> followedUserModel =  userLocalService.findByUserName(followerModel.getUserName());
        if(null == followedUserModel || followedUserModel.isEmpty()) throw new LocalServiceException("Error user with username " + followerModel.getUserName() + " does not exist.");

        List<FollowerModel> followerModels = this.findByUserName(followerModel.getUserName());
        if(followerModels.size() > 1) throw new LocalServiceException("Error user is already followed");

        try {
            Follower follower = ModelUtils.toDomain(followerModel, FollowerModelMapper.class);
            return ModelUtils.toModel(followerDao.create(follower), FollowerModelMapper.class);
        } catch (Exception ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }

    }

    @Override
    public FollowerModel update(long id, FollowerModel followerModel) throws LocalServiceException {
        try {
            FollowerModel updateFollowerModel = findById(id);

            Follower follower = ModelUtils.toDomain(updateFollowerModel, FollowerModelMapper.class);
            return ModelUtils.toModel(followerDao.update(follower), FollowerModelMapper.class);
        } catch (Exception ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }
    }

    @Override
    public void delete(long id) throws LocalServiceException {
        try {
            followerDao.deleteById(id);
        } catch (PersistenceException | DataNotFoundException ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }
    }

    @Override
    public List<FollowerModel> findByUserName(String userName) throws LocalServiceException {
        try {
            return null;
        } catch (PersistenceException ex) {
            LOG.error(ex);
            throw new LocalServiceException(ex);
        }
    }

}
