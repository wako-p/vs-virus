package vsvirus.card.rules;

import vsvirus.card.ICard;
import vsvirus.shared.rules.Rule;

/**
 * カード種のルール
 */
public final class TypeRule extends Rule {

    private final Class<? extends ICard> type;

    public TypeRule(final Class<? extends ICard> type) {
        this.type = type;
    }

    @Override
    public boolean ok(final ICard card) {
        return this.type == card.getClass();
    }

}
