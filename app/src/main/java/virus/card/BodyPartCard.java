package virus.card;

/**
 * からだパーツカード
 */
public final class BodyPartCard implements ICard {

    private final Color color;
    private Status status;

    private BodyPartCard(final Color color, final Status status) {
        this.color = color;
        this.status = status;
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

    public void infection(VirusCard virus) {

        if (this.status == Status.HEALTHY) {
            this.status = Status.INFECTED;
            return;
        }

        if (this.status == Status.INFECTED) {
            this.status = Status.SYMPTOMATIC;
            return;
        }

        if (this.status == Status.PASSIVELY_IMMUNIZED) {
            this.status = Status.HEALTHY;
            return;
        }

    }

    public void care(MedicineCard medicine) {

        if (this.status == Status.HEALTHY) {
            this.status = Status.PASSIVELY_IMMUNIZED;
            return;
        }

        if (this.status == Status.PASSIVELY_IMMUNIZED) {
            this.status = Status.IMMUNIZED;
            return;
        }

        if (this.status == Status.INFECTED) {
            this.status = Status.HEALTHY;
            return;
        }

    }

}
