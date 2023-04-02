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
        // TODO: 感染ルールとか定義してそっちに記述する
        if (virus.color() != Color.MULTI && this.color != virus.color())
        {
            throw new IllegalArgumentException();
        }
        this.status = this.status.next(virus);
    }

    public void care(MedicineCard medicine) {
        // TODO: ケアルールとか定義してそっちに記述する
        if (medicine.color() != Color.MULTI && this.color != medicine.color()) {
            throw new IllegalArgumentException();
        }
        this.status = this.status.next(medicine);
    }

}
