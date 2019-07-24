package model;

public class Code {

    private int codeValue;
    private User user;

    public Code(int code, User user) {
        this.codeValue = code;
        this.user = user;
    }

    public int getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(int code) {
        this.codeValue = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Code code1 = (Code) o;

        if (getCodeValue() != code1.getCodeValue())
            return false;
        return getUser() != null ? getUser().equals(code1.getUser()) : code1.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = getCodeValue();
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }
}
