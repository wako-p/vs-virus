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

    public void infection(VirusCard virus) {
        // TODO: 感染ルールとか定義してそっちに記述する
        if (!canInfected(virus))
        {
            throw new IllegalArgumentException();
        }
        this.status = this.status.next(virus);
    }

    private boolean canInfected(VirusCard virus) {
        return virus.color() == Color.MULTI || virus.color() == this.color;
    }

    public void care(MedicineCard medicine) {
        // TODO: ケアルールとか定義してそっちに記述する
        if (!canCared(medicine)) {
            throw new IllegalArgumentException();
        }
        this.status = this.status.next(medicine);
    }

    private boolean canCared(MedicineCard medicine) {
        return medicine.color() == Color.MULTI || medicine.color() == this.color;
    }

}
