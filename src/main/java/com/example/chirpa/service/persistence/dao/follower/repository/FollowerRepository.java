package com.example.chirpa.service.persistence.dao.follower.repository;

import com.example.chirpa.service.persistence.domain.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
    @Transactional
    public List<Follower> findByUserName(String userName);

    @Transactional
    public List<Follower> findByUserId(long userId);


}
