package vsvirus.card.virus;

import lombok.Getter;
import vsvirus.card.Color;
import vsvirus.card.ICard;
import vsvirus.card.medicine.MedicineCard;

/**
 * ウィルスカード
 */
public final class VirusCard implements ICard {

    @Getter
    private final Color color;

    private VirusCard(final Color color) {
        this.color = color;
    }

    public static VirusCard create(final Color color) {
        return new VirusCard(color);
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
