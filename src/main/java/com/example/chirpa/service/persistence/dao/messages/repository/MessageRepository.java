package com.example.chirpa.service.persistence.dao.messages.repository;

import com.example.chirpa.service.persistence.domain.Follower;
import com.example.chirpa.service.persistence.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Transactional
    public List<Message> findMessagesByFromUserName(String fromUserName);


}
