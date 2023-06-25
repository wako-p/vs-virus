package vsvirus.card.medicine;

import vsvirus.card.Color;
import vsvirus.card.ICard;

/**
 * 薬カード
 */
public final class MedicineCard implements ICard {

    private final Color color;

    private MedicineCard(final Color color) {
        this.color = color;
    }

    public static MedicineCard create(final Color color) {
        return new MedicineCard(color);
    }

    public Color getColor() {
        return this.color;
    }

}
