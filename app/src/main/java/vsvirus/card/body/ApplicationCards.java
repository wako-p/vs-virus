package vsvirus.card.body;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import vsvirus.card.ICard;

/**
 * 適用カード
 */
final class ApplicationCards {

    private static final int FULL = 2;
    private final List<Optional<ICard>> cards;

    List<Optional<ICard>> getEvilCards() {
        // 変更できないようにして返す
        return Collections.unmodifiableList(this.cards);
    }

    ApplicationCards() {
        this.cards = new ArrayList<>();
    }

    ApplicationCards(final List<Optional<ICard>> cards) {
        this.cards = cards;
    }

    void add(final ICard card) {
        // 適用できるカードは最大枚数まで
        if (this.cards.size() == FULL) {
            throw new IllegalStateException();
        }
        this.cards.add(Optional.of(card));
    }

    boolean isFull() {
        return this.cards.size() == FULL;
    }

    boolean isNotFull() {
        return !isFull();
    }

    boolean isEmpty() {
        return this.cards.isEmpty();
    }

    Optional<ICard> first() {
        return this.cards.get(0);
    }

    Optional<ICard> second() {
        return this.cards.get(1);
    }

    Optional<ICard> removeLast() {
        var lastIndex = this.cards.size() - 1;
        if (lastIndex < 0) {
            throw new IllegalStateException();
        }
        return this.cards.remove(lastIndex);
    }

    List<Optional<ICard>> removeAll() {
        var removeCards = this.cards
            .stream()
            .collect(Collectors.toList());

        this.cards.clear();

        return removeCards;
    }

}
