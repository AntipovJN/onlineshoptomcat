package model;

public class Code {

    private int code;
    private long userId;

    public Code(int code, long userId) {
        this.code = code;
        this.userId = userId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getUser() {
        return userId;
    }

    public void setUser(long userId) {
        this.userId = userId;
    }
}
