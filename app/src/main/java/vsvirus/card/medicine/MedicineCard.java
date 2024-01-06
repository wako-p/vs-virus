package vsvirus.card.medicine;

import lombok.Getter;
import vsvirus.card.Color;
import vsvirus.card.ICard;

/**
 * 薬カード
 */
public final class MedicineCard implements ICard {

    @Getter
    private final Color color;

    private MedicineCard(final Color color) {
        this.color = color;
    }

    public static MedicineCard create(final Color color) {
        return new MedicineCard(color);
    }

    @Override
    public String toString() {
        return "[M%s]".formatted(this.color.toString());
    }

}
