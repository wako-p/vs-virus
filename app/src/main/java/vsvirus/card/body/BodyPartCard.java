package vsvirus.card.body;

import vsvirus.card.Color;
import vsvirus.card.ICard;
import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.virus.VirusCard;

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

    public void apply(VirusCard virus) {
        // TODO: 感染ルールとか定義してそっちに記述する
        if (!canApplyed(virus))
        {
            throw new IllegalArgumentException();
        }
        this.status = this.status.next(virus);
    }

    private boolean canApplyed(VirusCard virus) {
        return virus.color() == Color.MULTI || virus.color() == this.color;
    }

    public void apply(MedicineCard medicine) {
        // TODO: ケアルールとか定義してそっちに記述する
        if (!canApplyed(medicine)) {
            throw new IllegalArgumentException();
        }
        this.status = this.status.next(medicine);
    }

    private boolean canApplyed(MedicineCard medicine) {
        return medicine.color() == Color.MULTI || medicine.color() == this.color;
    }

}
