package virus.card;

/**
 * 遷移前、遷移後、カード型の組み合わせを扱う
 */
public final class Trasition {

    private final Status to;
    private final Status from;
    private final Class<? extends ICard> card;

    Trasition(Status from, Status to, Class<? extends ICard> card) {
        this.from = from;
        this.to = to;
        this.card = card;
    }

    boolean match(Status from, Class<? extends ICard> card) {
        return this.from == from && this.card == card;
    }

    Status next() {
        return this.to;
    }

}
