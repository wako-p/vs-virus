package vsvirus.card.body;

import vsvirus.card.ICard;

/**
 * 状態
 */
public enum Status {
    HEALTHY,                // 健康
    INFECTED,               // 感染
    SYMPTOMATIC,            // 発症
    PASSIVELY_IMMUNIZED,    // 仮免駅
    IMMUNIZED;              // 免疫

    private static Trasitions trasitions = new Trasitions();

    public Status next(final ICard card) {
        return trasitions.next(this, card.getClass());
    }

}
