package vsvirus.card.body;

import vsvirus.card.ICard;

/**
 * 遷移前、遷移後、カード型の組み合わせを扱う
 */
public final class Trasition {

    private final Status to;
    private final Status from;
    private final Class<? extends ICard> type;

    Trasition(final Status from, final Status to, final Class<? extends ICard> type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    boolean match(final Status from, final Class<? extends ICard> type) {
        return this.from == from && this.type == type;
    }

    Status next() {
        return this.to;
    }

}
