package vsvirus.card.body;

import java.util.List;

import lombok.Getter;
import vsvirus.card.Color;
import vsvirus.card.ICard;
import vsvirus.card.rules.ColorRule;

/**
 * からだパーツカード
 */
public final class BodyPartCard implements ICard {

    @Getter
    private final Color color;

    @Getter
    private Status status;

    private final ApplicationPolicy applicationPolicy;
    private final ApplicationCards applicationCards;

    private BodyPartCard(final Color color, final Status status, final ApplicationCards applyedCards) {
        this.color = color;
        this.status = status;

        this.applicationPolicy = new ApplicationPolicy(new ColorRule(color));
        this.applicationCards = applyedCards;
    }

    public static BodyPartCard create(final Color color) {
        return new BodyPartCard(color, Status.HEALTHY, new ApplicationCards(2));
    }

    public List<ICard> getApplicationCards() {
        return this.applicationCards.getEvilCards();
    }

    public void apply(final ICard card) {
        // 適用ポリシー満たしてる？
        if (!this.applicationPolicy.complyWithAll(card)) {
            throw new IllegalArgumentException();
        }

        this.applicationCards.add(card);
        this.status = this.status.next(card);
    }

}
