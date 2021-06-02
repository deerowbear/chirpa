package com.example.chirpa.service.persistence.dao.follower;

import com.example.chirpa.service.persistence.dao.follower.repository.FollowerRepository;
import com.example.chirpa.service.persistence.domain.follower.Follower;
import com.example.chirpa.service.persistence.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class FollowerDaoImpl implements FollowerDao {

    private final FollowerRepository followerRepository;

    @Autowired
    public FollowerDaoImpl(FollowerRepository followerRepository) {
        this.followerRepository = followerRepository;
    }

    @Override
    @Transactional(readOnly = true , propagation = Propagation.REQUIRED)
    public List<Follower> findAll() throws PersistenceException, DataNotFoundException {
        return followerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true , propagation = Propagation.REQUIRED)
    public Follower findById(Long id) throws DataNotFoundException, PersistenceException {
        if ( id == null ) {
            throw new DataNotFoundException("Follower id " + id + " does not exist");
        }
        try {
            return followerRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new DataNotFoundException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Follower create(Follower follower) throws PersistenceException, PersistenceException {
        if ( follower == null ) {
            throw new IllegalArgumentException("Follower object can not be null");
        }
        return followerRepository.save(follower);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Follower update(Follower follower) throws DataNotFoundException, PersistenceException, DataNotFoundException {
        if ( follower == null ) {
            throw new IllegalArgumentException("Follower object can not be null");
        }
        try {
            return followerRepository.saveAndFlush(follower);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) throws DataNotFoundException, PersistenceException {
        if ( !followerRepository.existsById(id) ) {
            throw new DataNotFoundException("Follower with id " + id + " does not exist");
        }
        try {
            Follower follower = followerRepository.getById(id);
            followerRepository.delete(follower);
            followerRepository.flush();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    @Transactional(readOnly = true , propagation = Propagation.REQUIRED)
    public boolean exists(Long id) throws PersistenceException {
        if ( id == null ) {
            throw new IllegalArgumentException("Follower id can not be null");
        }
        try {
            return followerRepository.existsById(id);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

}
