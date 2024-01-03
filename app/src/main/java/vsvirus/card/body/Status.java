package vsvirus.card.body;

import lombok.Getter;
import vsvirus.card.ICard;

/**
 * 状態
 */
enum Status {
    HEALTHY("健康"),
    INFECTED("感染"),
    SYMPTOMATIC("発症"),
    PASSIVELY_IMMUNIZED("仮免疫"),
    IMMUNIZED("免疫");

    @Getter
    private String name;
    private static Trasitions trasitions = new Trasitions();

    private Status(final String name) {
        this.name = name;
    }

    Status next(final ICard card) {
        return trasitions.next(this, card.getClass());
    }

}
