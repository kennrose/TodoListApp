package util;

import java.util.concurrent.ThreadLocalRandom;

public final class IdGenerator {
    private IdGenerator() { }

    public static String generateId() {
        return String.format("%06d", ThreadLocalRandom.current().nextInt(1_000_000));
    }

    // Retained for compatibility with older code that supplied a year.
    public static String generateId(int year) {
        return year + "-" + generateId();
    }
}
