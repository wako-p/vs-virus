package virus.card;

/**
 * からだパーツカード
 */
public final class BodyPartCard implements ICard {

    private final Color color;
    private final Status status;

    private BodyPartCard(final Color color, final Status health) {
        this.color = color;
        this.status = health;
    }

    public static BodyPartCard create(final Color color) {
        return new BodyPartCard(color, Status.HEALTHY);
    }

    public Color color() {
        return this.color;
    }

    public Status status() {
        return this.status;
    }

}
