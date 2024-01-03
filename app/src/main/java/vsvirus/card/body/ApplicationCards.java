package vsvirus.card.body;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vsvirus.card.ICard;

/**
 * 適用カード
 */
final class ApplicationCards {

    private final List<ICard> cards;

    List<ICard> getEvilCards() {
        // 変更できないようにして返す
        return Collections.unmodifiableList(this.cards);
    }

    ApplicationCards() {
        this.cards = new ArrayList<>();
    }

    void add(final ICard card) {
        // 適用できるカードは最大枚数まで
        if (this.cards.size() == 2) {
            throw new IllegalStateException();
        }
        this.cards.add(card);
    }

}
