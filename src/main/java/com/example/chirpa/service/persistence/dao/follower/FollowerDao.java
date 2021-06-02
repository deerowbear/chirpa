package com.example.chirpa.service.persistence.dao.follower;

import com.example.chirpa.service.persistence.dao.SimpleCrudDao;
import com.example.chirpa.service.persistence.domain.follower.Follower;
import com.example.chirpa.service.persistence.exception.DataNotFoundException;

import javax.persistence.PersistenceException;
import java.util.List;

public interface FollowerDao extends SimpleCrudDao<Follower, Long> {

    public List<Follower> findAll() throws PersistenceException, DataNotFoundException;

}
