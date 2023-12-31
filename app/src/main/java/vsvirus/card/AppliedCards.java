package vsvirus.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 適用されたカード
 */
public final class AppliedCards {

    private final int max;
    private final List<ICard> cards;

    public List<ICard> getEvilCards() {
        // 変更できないようにして返す
        return Collections.unmodifiableList(this.cards);
    }

    public AppliedCards(final int max) {
        this.max = max;
        this.cards = new ArrayList<>();
    }

    public void add(final ICard card) {
        // 適用できるカードは最大枚数まで
        if (this.cards.size() == this.max) {
            throw new IllegalStateException();
        }
        this.cards.add(card);
    }

}
