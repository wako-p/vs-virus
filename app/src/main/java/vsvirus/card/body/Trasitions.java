package vsvirus.card.body;

import java.util.ArrayList;
import java.util.List;

import vsvirus.card.ICard;
import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.virus.VirusCard;

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

    Status next(final Status from, final Class<? extends ICard> type) {
        for (Trasition trasition : trasitions) {
            if (trasition.match(from, type)) return trasition.next();
        }
        throw new IllegalArgumentException();
    }

}
