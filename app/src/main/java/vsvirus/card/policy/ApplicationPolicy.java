package vsvirus.card.policy;

import java.util.ArrayList;
import java.util.List;

import vsvirus.card.ICard;

/**
 * 適用ポリシー
 */
public final class ApplicationPolicy {

    private final List<Rule> rules = new ArrayList<>();

    public void add(final Rule rule) {
        this.rules.add(rule);
    }

    public boolean verify(final ICard card) {
        for (Rule rule : this.rules) {
            if (!rule.ok(card)) {
                return false;
            }
        }
        return true;
    }

}
