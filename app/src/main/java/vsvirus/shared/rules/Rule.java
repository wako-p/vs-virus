package vsvirus.shared.rules;

import vsvirus.card.ICard;

public abstract class Rule {

    public abstract boolean ok(final ICard card);

    public Rule or(final Rule other) {
        return new OrRule(this, other);
    }

}
