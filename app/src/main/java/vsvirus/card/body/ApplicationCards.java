package vsvirus.card.body;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vsvirus.card.ICard;

/**
 * 適用カード
 */
public final class ApplicationCards {

    private final List<ICard> cards;

    public List<ICard> getEvilCards() {
        // 変更できないようにして返す
        return Collections.unmodifiableList(this.cards);
    }

    // TODO: ApplicationPolicy持たせてadd()でverify()する？
    ApplicationCards() {
        this.cards = new ArrayList<>();
    }

    public void add(final ICard card) {
        // 適用できるカードは2枚まで
        if (this.cards.size() == 2) {
            throw new IllegalStateException();
        }
        this.cards.add(card);
    }

}
