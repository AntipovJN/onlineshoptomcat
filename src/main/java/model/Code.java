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

    public long getUserId() {
        return userId;
    }

    public void setUser(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Code code1 = (Code) o;

        if (getCode() != code1.getCode())
            return false;
        return getUserId() == code1.getUserId();
    }

    @Override
    public int hashCode() {
        int result = getCode();
        result = 31 * result + (int) (getUserId() ^ (getUserId() >>> 32));
        return result;
    }
}
