package virus.card;

import java.util.ArrayList;
import java.util.List;

/**
 * からだパーツカードの状態遷移を扱う
 */
public final class Trasitions {

    private final List<Trasition> trasitions;

    Trasitions() {
        this.trasitions = new ArrayList<>();
        this.trasitions.add(new Trasition(Status.HEALTHY, Status.INFECTED, VirusCard.class));
        this.trasitions.add(new Trasition(Status.HEALTHY, Status.PASSIVELY_IMMUNIZED, MedicineCard.class));
        this.trasitions.add(new Trasition(Status.INFECTED, Status.SYMPTOMATIC, VirusCard.class));
        this.trasitions.add(new Trasition(Status.INFECTED, Status.HEALTHY, MedicineCard.class));
        this.trasitions.add(new Trasition(Status.PASSIVELY_IMMUNIZED, Status.HEALTHY, VirusCard.class));
        this.trasitions.add(new Trasition(Status.PASSIVELY_IMMUNIZED, Status.IMMUNIZED, MedicineCard.class));
    }

    Status next(Status from, Class<? extends ICard> card) {
        for (Trasition trasition : trasitions) {
            if (trasition.match(from, card)) return trasition.next();
        }
        throw new IllegalArgumentException();
    }

}
