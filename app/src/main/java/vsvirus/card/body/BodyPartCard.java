package vsvirus.card.body;

import java.util.List;
import java.util.Optional;

import lombok.Getter;
import vsvirus.card.Color;
import vsvirus.card.ICard;
import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.virus.VirusCard;

/**
 * からだパーツカード
 */
public final class BodyPartCard implements ICard {

    @Getter
    private final Color color;

    @Getter
    private Status status;
    private final ApplicationCards applicationCards;

    private BodyPartCard(final Color color, final Status status, final ApplicationCards applicationCards) {
        this.color = color;
        this.status = status;
        this.applicationCards = applicationCards;
    }

    public static BodyPartCard create(final Color color) {
        return new BodyPartCard(color, Status.HEALTHY, new ApplicationCards());
    }

    public List<Optional<ICard>> getApplicationCards() {
        return this.applicationCards.getEvilCards();
    }

    public void apply(final MedicineCard medicine) {

        if (!isValidColor(medicine.getColor())) {
            throw new IllegalArgumentException();
        }

        this.applicationCards.add(medicine);
        this.status = this.status.next(medicine);
    }

    public void apply(final VirusCard virus) {

        if (!isValidColor(virus.getColor())) {
            throw new IllegalArgumentException();
        }

        this.applicationCards.add(virus);
        this.status = this.status.next(virus);
    }

    private boolean isValidColor(final Color color) {
        return this.color.equals(color);
    }

    public List<Optional<ICard>> dispatchAll() {

        if (this.status == Status.HEALTHY &&
            this.applicationCards.isFull()) {
                return this.applicationCards.removeAll();
        }

        throw new IllegalStateException();
    }

}
