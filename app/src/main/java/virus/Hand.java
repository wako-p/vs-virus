package virus;

import java.util.LinkedList;
import java.util.List;

import virus.card.ICard;

/**
 * 手札
 */
public class Hand {

    private List<ICard> cards;

    private Hand(final List<ICard> cards) {
        this.cards = cards;
    }

    public static Hand create() {
        // 追加、削除が多いのでLinkedList使う
        List<ICard> cards = new LinkedList<>();
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
