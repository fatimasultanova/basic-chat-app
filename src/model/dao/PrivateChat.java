package model.dao;

import model.enums.Chat;

public class PrivateChat {
    private Chat chat;
    private long targetClientId;

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public long getTargetClientId() {
        return targetClientId;
    }

    public void setTargetClientId(long targetClientId) {
        this.targetClientId = targetClientId;
    }
}
