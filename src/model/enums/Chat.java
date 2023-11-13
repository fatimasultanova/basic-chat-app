package model.enums;

public enum Chat {

    PUBLIC_CHAT, PRIVATE_CHAT, NONE;

    private int x;

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }
}
