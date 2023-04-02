package virus.card.body;

import virus.card.ICard;

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

    public Status next(ICard card) {
        return trasitions.next(this, card.getClass());
    }

}
