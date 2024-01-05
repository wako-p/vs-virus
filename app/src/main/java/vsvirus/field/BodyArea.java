package vsvirus.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import vsvirus.card.body.BodyPartCard;

/**
 * からだエリア
 */
public final class BodyArea {

    private final List<Optional<BodyPartCard>> cards;

    private BodyArea(final List<Optional<BodyPartCard>> cards) {
        this.cards = cards;
    }

    public static BodyArea create() {
        var cards = new ArrayList<Optional<BodyPartCard>>(Collections.nCopies(4, Optional.empty()));
        return new BodyArea(cards);
    }

    public long count() {
        return this.cards
                .stream()
                .filter(Optional::isPresent)
                .count();
    }

    public List<Optional<BodyPartCard>> getEvilCards() {
        // 追加、変更、削除ができないようにして返す
        return Collections.unmodifiableList(this.cards);
    }

    public void place(final int index, final BodyPartCard card) {

        if (!isValidIndex(index)) {
            throw new IllegalArgumentException();
        }

        if (isValidExist(index)) {
            throw new IllegalStateException();
        }

        // 同じ色のからだパーツカードを複数置くことはできない
        if (isValidDuplicate(card)) {
            throw new IllegalStateException();
        }

        this.cards.add(index, Optional.of(card));
    }

    public Optional<BodyPartCard> get(final int index) {
        if (!isValidIndex(index)) {
            throw new IllegalArgumentException();
        }
        return this.cards.get(index);
    }

    private boolean isValidIndex(final int index) {
        return 0 <= index && index <= 3;
    }

    private boolean isValidExist(final int index) {
        return this.cards.get(index).isPresent();
    } 

    private boolean isValidDuplicate(final BodyPartCard newCard) {
        // 同じ色のからだパーツカードでフィルタしてその数をカウントする
        var duplicateCount = this.cards
                .stream()
                .filter(maybeCard -> maybeCard
                    .map(card -> card.getColor() == newCard.getColor())
                    .orElse(false))
                .count();

        // 重複してたら0にならない
        return duplicateCount != 0;
    }

}
