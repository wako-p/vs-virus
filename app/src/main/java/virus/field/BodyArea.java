package virus.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import virus.card.BodyPartCard;

/**
 * からだエリア
 */
public final class BodyArea {

    private List<BodyPartCard> cards;

    private BodyArea(final List<BodyPartCard> cards) {
        this.cards = cards;
    }

    public static BodyArea create() {
        List<BodyPartCard> cards = new ArrayList<>();
        return new BodyArea(cards);
    }

    public List<BodyPartCard> evilCards() {
        // 追加、変更、削除ができないようにして返す
        return Collections.unmodifiableList(this.cards);
    }

    public int count() {
        return this.cards.size();
    }

    public void place(final BodyPartCard card) {
        if (this.cards.size() == 4) {
            throw new RuntimeException();
        }

        // 同じ色のからだパーツカードを複数置くことはできない
        if (isDuplicate(card)) {
            throw new RuntimeException();
        }

        this.cards.add(card);
    }

    private boolean isDuplicate(final BodyPartCard newCard) {
        // 同じ色のからだパーツカードでフィルタしてその数をカウントする
        var duplicateCount = this.cards
                .stream()
                .filter(card -> card.color == newCard.color)
                .count();

        // 重複してたら0にならない
        return duplicateCount != 0;
    }

}
