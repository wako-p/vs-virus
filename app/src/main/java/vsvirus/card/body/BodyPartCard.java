package vsvirus.card.body;

import java.util.ArrayList;
import java.util.List;

import vsvirus.card.ApplicationPolicy;
import vsvirus.card.Color;
import vsvirus.card.ICard;
import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.rules.ColorRule;
import vsvirus.card.rules.TypeRule;
import vsvirus.card.virus.VirusCard;

/**
 * からだパーツカード
 */
public final class BodyPartCard implements ICard {

    private final Color color;
    private Status status;
    private final ApplicationPolicy policy;
    private final ApplicationCards applyedCards;

    private BodyPartCard(final Color color, final Status status, List<ICard> applyedCards) {
        this.color = color;
        this.status = status;

        this.policy = new ApplicationPolicy();
        this.policy.add(new TypeRule(VirusCard.class).or(new TypeRule(MedicineCard.class)));
        this.policy.add(new ColorRule(color));

        this.applyedCards = new ApplicationCards();
    }

    public static BodyPartCard create(final Color color) {
        return new BodyPartCard(color, Status.HEALTHY, new ArrayList<ICard>());
    }

    public Color getColor() {
        return this.color;
    }

    public Status getStatus() {
        return this.status;
    }

    public List<ICard> getApplyedCards() {
        return this.applyedCards.getEvilCards();
    }

    public void apply(final ICard card) {
        // 適用ポリシー満たしてる？
        if (!this.policy.verify(card)) {
            throw new IllegalArgumentException();
        }

        this.applyedCards.add(card);
        this.status = this.status.next(card);
    }

}
