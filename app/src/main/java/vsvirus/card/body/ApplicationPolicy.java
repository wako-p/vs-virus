package vsvirus.card.body;

import vsvirus.card.ICard;
import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.rules.ColorRule;
import vsvirus.card.rules.TypeRule;
import vsvirus.card.virus.VirusCard;
import vsvirus.shared.policy.Policy;

/**
 * 適用ポリシー
 */
public final class ApplicationPolicy {

    private final Policy policy;

    ApplicationPolicy(final ColorRule colorRule) {
        this.policy = new Policy();
        this.policy.add(new TypeRule(VirusCard.class).or(new TypeRule(MedicineCard.class)));
        this.policy.add(colorRule);
    }

    boolean complyWithAll(final ICard card) {
        return this.policy.complyWithAll(card);
    }

}
