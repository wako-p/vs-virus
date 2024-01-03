package vsvirus.card.virus;

import lombok.Getter;
import vsvirus.card.Color;
import vsvirus.card.ICard;

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

}
