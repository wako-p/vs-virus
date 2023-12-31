package vsvirus.shared.rules;

import vsvirus.card.ICard;

public final class OrRule extends Rule {

    private final Rule rule1;
    private final Rule rule2;

    public OrRule(final Rule rule1, final Rule rule2) {
        this.rule1 = rule1;
        this.rule2 = rule2;
    }

    @Override
    public boolean ok(final ICard card) {
        return this.rule1.ok(card) || this.rule2.ok(card);
    }

}
