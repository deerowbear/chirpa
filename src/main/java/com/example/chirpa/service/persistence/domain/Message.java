package com.example.chirpa.service.persistence.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="MESSAGE")
public class Message implements Serializable {

    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @Column(name = "from_user_name")
    private String fromUserName;

    @Column(name = "to_user_name")
    private String toUserName;

    @Column(name = "message_body")
    private String messageBody;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Message other = (Message) obj;
        if (messageId == null) {
            if (other.messageId != null)
                return false;
        } else if (!messageId.equals(other.messageId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("com.example.chirpa.service.persistence.domain.Follower[ id=" + messageId);
        sb.append("\n fromUserName = ").append(fromUserName);
        sb.append("\n toUserName = ").append(toUserName);
        sb.append("\n messageBody = ").append(messageBody);
        sb.append(" ]");
        return sb.toString();
    }

}
