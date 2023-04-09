package vsvirus.card.body;

import vsvirus.card.Color;
import vsvirus.card.ICard;

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
        // TODO: 適用ルールとか定義してそっちに記述する
        if (!canApplyed(card))
        {
            throw new IllegalArgumentException();
        }
        this.status = this.status.next(card);
    }

    private boolean canApplyed(ICard virus) {
        return virus.color() == Color.MULTI || virus.color() == this.color;
    }

}
