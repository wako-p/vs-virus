package vsvirus.shared.policy;

import java.util.ArrayList;
import java.util.List;

import vsvirus.card.ICard;
import vsvirus.shared.rules.Rule;

/**
 * ポリシー
 */
public class Policy {

    private final List<Rule> rules = new ArrayList<>();

    public void add(final Rule rule) {
        this.rules.add(rule);
    }

    public boolean complyWithAll(final ICard card) {
        return this.rules
            .stream()
            .allMatch(rule -> rule.ok(card));
    }

}
