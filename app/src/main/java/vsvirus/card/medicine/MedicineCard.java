package vsvirus.card.medicine;

import lombok.Getter;
import vsvirus.card.Color;
import vsvirus.card.ICard;
import vsvirus.card.virus.VirusCard;

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
    public void apply(final MedicineCard medicine) {
        if (!isValidColor(medicine.getColor())) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void apply(final VirusCard virus) {
        if (!isValidColor(virus.getColor())) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValidColor(final Color color) {
        return this.color.equals(color);
    }

}
