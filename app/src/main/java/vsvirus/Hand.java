package vsvirus;

import java.util.ArrayList;
import java.util.List;

import vsvirus.card.ICard;

/**
 * 手札
 */
public final class Hand {

    private List<ICard> cards;

    private Hand(final List<ICard> cards) {
        this.cards = cards;
    }

    public static Hand create() {
        var cards = new ArrayList<ICard>();
        return new Hand(cards);
    }

    public int count() {
        return this.cards.size();
    }

    public void add(final ICard card) {
        if (this.cards.size() == 3) {
            throw new RuntimeException("");
        }
        this.cards.add(card);
    }

}
