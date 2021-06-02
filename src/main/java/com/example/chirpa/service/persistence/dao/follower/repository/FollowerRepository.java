package com.example.chirpa.service.persistence.dao.follower.repository;

import com.example.chirpa.service.persistence.domain.follower.Follower;
import com.example.chirpa.service.persistence.exception.DataNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;


@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {



}
