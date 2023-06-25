package vsvirus.card.virus;

import vsvirus.card.Color;
import vsvirus.card.ICard;

/**
 * ウィルスカード
 */
public final class VirusCard implements ICard {

    private final Color color;

    private VirusCard(final Color color) {
        this.color = color;
    }

    public static VirusCard create(final Color color) {
        return new VirusCard(color);
    }

    public Color getColor() {
        return this.color;
    }

}
