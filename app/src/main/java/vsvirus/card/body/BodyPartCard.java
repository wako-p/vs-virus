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

    public void apply(ICard card) {
        // TODO: 適用ポリシーとか定義してそっちに記述する

        // 適用できるカードはウィルスカードと薬カードのみ
        if (!(card.getClass() == VirusCard.class || card.getClass() == MedicineCard.class)) {
            throw new IllegalArgumentException();
        }

        // 適用できるカードは同じ色のカード
        if (!canApplyedCardColor(card))
        {
            throw new IllegalArgumentException();
        }

        this.status = this.status.next(card);
    }

    private boolean canApplyedCardColor(ICard virus) {
        return virus.color() == Color.MULTI || virus.color() == this.color;
    }

}
