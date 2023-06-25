package vsvirus.card;

/**
 * 色
 */
public enum Color {
    RED,
    BLUE,
    GREEN,
    YELLOW,
    MULTI;

    public boolean equals(final Color other) {

        if (this == MULTI || other == MULTI) {
            return true;
        }

        if (this == other) {
            return true;
        }

        return false;
    }

}
