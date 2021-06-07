package com.hsmq.data;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author ：河神
 * @date ：Created in 2021/6/7 3:05 下午
 */
public class Message implements Serializable {

    private String msgId;
    private String topic;
    private String tag;
    private String body;


    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Message.class.getSimpleName() + "[", "]")
                .add("msgId='" + msgId + "'")
                .add("topic='" + topic + "'")
                .add("tag='" + tag + "'")
                .add("body='" + body + "'")
                .toString();
    }
}
