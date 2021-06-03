package com.example.chirpa.service.persistence.dao.users;


import com.example.chirpa.service.persistence.dao.SimpleCrudDao;
import com.example.chirpa.service.persistence.domain.User;
import com.example.chirpa.service.persistence.exception.DataNotFoundException;

import javax.persistence.PersistenceException;
import java.util.List;

public interface UserDao extends SimpleCrudDao<User, Long> {

    public List<User> findAll() throws PersistenceException, DataNotFoundException;

    public List<User> findByUserName(String userName) throws PersistenceException, DataNotFoundException;

}
