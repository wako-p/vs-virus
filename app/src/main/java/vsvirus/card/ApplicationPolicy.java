package vsvirus.card;

import java.util.ArrayList;
import java.util.List;

import vsvirus.shared.rules.Rule;

/**
 * 適用ポリシー
 */
public final class ApplicationPolicy {

    private final List<Rule> rules = new ArrayList<>();

    public void add(final Rule rule) {
        this.rules.add(rule);
    }

    public boolean verify(final ICard card) {
        return this.rules
            .stream()
            .allMatch(rule -> rule.ok(card));
    }

}
