package vsvirus.card;

/**
 * 色
 */
public enum Color {
    BLUE("B"),
    RED("R"),
    GREEN("G"),
    YELLOW("Y"),
    MULTI("M");

    private final String symbol;

    private Color(final String symbol) {
        this.symbol = symbol;
    }

    public boolean equals(final Color other) {

        if (this == MULTI || other == MULTI) {
            return true;
        }

        if (this == other) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return this.symbol;
    }

}
