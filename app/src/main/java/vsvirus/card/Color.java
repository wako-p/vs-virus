package vsvirus.card;

/**
 * 色
 */
public enum Color {
    RED("Red"),
    BLUE("Blue"),
    GREEN("Green"),
    YELLOW("Yellow"),
    MULTI("Multi");

    private String value;

    private Color(String value) {
        this.value = value;
    }

    public boolean equals(Color other) {

        if (this.value.equals("Multi") || other.value.equals("Multi")) {
            return true;
        }

        if (this.value.equals(other.value)) {
            return true;
        }

        return false;
    }

}
