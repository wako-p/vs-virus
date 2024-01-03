package vsvirus.card;

import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.virus.VirusCard;

public interface ICard {
    Color getColor();
    void apply(final MedicineCard medicine);
    void apply(final VirusCard virus);
}
