package vsvirus.card.body;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vsvirus.card.Color;
import vsvirus.card.ICard;
import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.policy.ApplicationPolicy;
import vsvirus.card.policy.ColorRule;
import vsvirus.card.policy.TypeRule;
import vsvirus.card.virus.VirusCard;

/**
 * からだパーツカード
 */
public final class BodyPartCard implements ICard {

    private final Color color;
    private Status status;
    private List<ICard> applyedCards;

    private final ApplicationPolicy policy;

    private BodyPartCard(final Color color, final Status status, List<ICard> applyedCards) {
        this.color = color;
        this.status = status;
        this.applyedCards = applyedCards;

        this.policy = new ApplicationPolicy();
        this.policy.add(new TypeRule(VirusCard.class).or(new TypeRule(MedicineCard.class)));
        this.policy.add(new ColorRule(color));
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

    public void apply(final ICard card) {
        // 適用ポリシー満たしてる？
        if (!this.policy.verify(card)) {
            throw new IllegalArgumentException();
        }

        // TODO: applyedCardsはファーストクラスコレクションクラスにする
        // 適用できるカードの枚数は2枚まで
        if (this.applyedCards.size() == 2) {
            throw new IllegalStateException();
        }
        this.applyedCards.add(card);

        this.status = this.status.next(card);
    }

}
