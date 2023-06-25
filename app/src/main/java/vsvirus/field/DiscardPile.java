package vsvirus.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vsvirus.card.ICard;

/**
 * 捨て札
 */
public final class DiscardPile {

    private final List<ICard> cards;

    private DiscardPile(final List<ICard> cards) {
        this.cards = cards;
    }

    public static DiscardPile create() {
        var cards = new ArrayList<ICard>();
        return new DiscardPile(cards);
    }

    public List<ICard> evilCards() {
        // 追加、変更、削除ができないようにして返す
        return Collections.unmodifiableList(this.cards);
    }

    public int count() {
        return this.cards.size();
    }

    public void place(final ICard card) {
        this.cards.add(card);
    }

}
