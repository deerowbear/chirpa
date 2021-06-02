package com.example.chirpa.service.persistence.dao.users;

import com.example.chirpa.service.persistence.dao.users.repository.UsersRepository;
import com.example.chirpa.service.persistence.domain.users.User;
import com.example.chirpa.service.persistence.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    private final UsersRepository usersRepository;

    @Autowired
    public UserDaoImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");



    @Override
    @Transactional(readOnly = true , propagation = Propagation.REQUIRED)
    public List<User> findAll() throws PersistenceException, DataNotFoundException {
        return usersRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true , propagation = Propagation.REQUIRED)
    public List<User> findByUserName(String userName) throws PersistenceException, DataNotFoundException {
        return usersRepository.findByUserName(userName);
    }

    @Override
    @Transactional(readOnly = true , propagation = Propagation.REQUIRED)
    public User findById(Long id) throws DataNotFoundException, PersistenceException {
        if ( id == null ) {
            throw new DataNotFoundException("User id " + id + " does not exist");
        }
        try {
            return usersRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new DataNotFoundException(e);
        }
    }

    @Override
    @Transactional(readOnly = true , propagation = Propagation.REQUIRED)
    public User create(User user) throws PersistenceException, PersistenceException {
        if ( user == null ) {
            throw new IllegalArgumentException("User object can not be null");
        }
        return usersRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true , propagation = Propagation.REQUIRED)
    public User update(User user) throws DataNotFoundException, PersistenceException, DataNotFoundException {
        if ( user == null ) {
            throw new IllegalArgumentException("User object can not be null");
        }
        try {
            return usersRepository.saveAndFlush(user);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    @Transactional(readOnly = true , propagation = Propagation.REQUIRED)
    public void deleteById(Long id) throws DataNotFoundException, PersistenceException {
        if ( !usersRepository.existsById(id) ) {
            throw new DataNotFoundException("User with id " + id + " does not exist");
        }
        try {
            User user = usersRepository.getById(id);
            usersRepository.delete(user);
            usersRepository.flush();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }


    @Override
    @Transactional(readOnly = true , propagation = Propagation.REQUIRED)
    public boolean exists(Long id) throws PersistenceException {
        if ( id == null ) {
            throw new IllegalArgumentException("User id can not be null");
        }
        try {
            return usersRepository.existsById(id);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }
}
