package entity;

public enum Relation {
    BLOCKED(3), FOLLOWING(2), REQUESTED(1), NA(0);

    private final int code;

    Relation(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

