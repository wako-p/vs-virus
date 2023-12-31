package vsvirus.card.rules;

import vsvirus.card.Color;
import vsvirus.card.ICard;
import vsvirus.shared.rules.Rule;

/**
 * カードカラーのルール
 */
public final class ColorRule extends Rule {

    private final Color color;

    public ColorRule(final Color color) {
        this.color = color;
    }

    @Override
    public boolean ok(final ICard card) {
        return this.color.equals(card.getColor());
    }

}
