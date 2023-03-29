package virus.card;

/**
 * からだパーツカード
 */
public class BodyPartCard implements ICard {

    public final Color color;
    public final Status status;

    private BodyPartCard(final Color color, final Status health) {
        this.color = color;
        this.status = health;
    }

    public static BodyPartCard create(final Color color) {
        return new BodyPartCard(color, Status.HEALTHY);
    }

}
