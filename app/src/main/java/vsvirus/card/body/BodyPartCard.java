package vsvirus.card.body;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vsvirus.card.Color;
import vsvirus.card.ICard;
import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.virus.VirusCard;

/**
 * からだパーツカード
 */
public final class BodyPartCard implements ICard {

    private final Color color;
    private Status status;
    private List<ICard> applyedCards;

    private BodyPartCard(final Color color, final Status status, List<ICard> applyedCards) {
        this.color = color;
        this.status = status;
        this.applyedCards = applyedCards;
    }

    public static BodyPartCard create(final Color color) {
        return new BodyPartCard(color, Status.HEALTHY, new ArrayList<ICard>());
    }

    public Color color() {
        return this.color;
    }

    public Status status() {
        return this.status;
    }

    public List<ICard> evilApplyedCards() {
        return Collections.unmodifiableList(this.applyedCards);
    }

    public void apply(ICard card) {
        // TODO: 適用ポリシーとか定義してそっちに記述する

        // 適用できるカードはウィルスカードと薬カードのみ
        if (!(card.getClass() == VirusCard.class || card.getClass() == MedicineCard.class)) {
            throw new IllegalArgumentException();
        }

        // 適用できるカードは同じ色のカード
        if (!this.color().equals(card.color()))
        {
            throw new IllegalArgumentException();
        }

        // 適用できるカードの枚数は2枚まで
        if (this.applyedCards.size() == 2) {
            throw new IllegalStateException();
        }

        this.applyedCards.add(card);
        this.status = this.status.next(card);
    }

}
