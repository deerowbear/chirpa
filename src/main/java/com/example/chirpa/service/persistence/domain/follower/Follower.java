package com.example.chirpa.service.persistence.domain.follower;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="FOLLOWER")
public class Follower implements Serializable {

    @Id
    @Column(name = "follower_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followerId;

    @Basic(optional = false)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Follower other = (Follower) obj;
        if (followerId == null) {
            if (other.followerId != null)
                return false;
        } else if (!followerId.equals(other.followerId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("com.example.chirpa.service.persistence.domain.follower.Follower[ id=" + followerId);
        sb.append("\n userId = ").append(userId);
        sb.append("\n userName = ").append(userName);
        sb.append(" ]");
        return sb.toString();
    }

}
