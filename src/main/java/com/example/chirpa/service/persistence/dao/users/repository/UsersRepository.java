package com.example.chirpa.service.persistence.dao.users.repository;

import com.example.chirpa.service.persistence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

        @Transactional
        public List<User> findByUserName(String userName);

}
