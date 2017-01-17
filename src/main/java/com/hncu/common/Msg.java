package com.hncu.common;

import java.io.Serializable;

/**
 * 返回消息对象
 */
public class Msg implements Serializable{

    private static final long serialVersionUID = -6532366534692890978L;

    public enum MsgType {
        success,
        info,
        error,
        block,
        danger,
        ok,
        remove
    }

    private MsgType type;
    private String content;

    public Msg() {
    }

    public Msg(MsgType type, String content) {
        this.type = type;
        this.content = content;
    }

    public MsgType getType() {
        return type;
    }

    public void setType(MsgType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
