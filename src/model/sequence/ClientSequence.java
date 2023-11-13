package model.sequence;

public class ClientSequence {
    private static long sequenceId;

    public static long nextVal() {
        return ++sequenceId;
    }
}
