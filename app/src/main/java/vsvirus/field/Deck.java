package vsvirus.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vsvirus.card.ICard;
import vsvirus.card.body.BodyPartCardFactory;
import vsvirus.card.medicine.MedicineCardFactory;
import vsvirus.card.virus.VirusCardFactory;

/**
 * 山札
 */
public final class Deck {

    private List<ICard> cards; 

    private Deck(final List<ICard> cards) {
        this.cards = cards;
    } 

    public static Deck create() {

        var bodyPartCardFactory = new BodyPartCardFactory();
        var bodyPartCards = bodyPartCardFactory.create();

        var medicineCardFactory = new MedicineCardFactory();
        var medicineCards = medicineCardFactory.create();

        var virusCardFactory = new VirusCardFactory();
        var virusCards = virusCardFactory.create();

        var cards = new ArrayList<ICard>();
        cards.addAll(bodyPartCards);
        cards.addAll(medicineCards);
        cards.addAll(virusCards);

        return new Deck(cards);
    }

    public int count() {
        return this.cards.size();
    }

    public List<ICard> evilCards() {
        // 追加、変更、削除ができないようにして返す
        return Collections.unmodifiableList(this.cards);
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public ICard draw() {
        return this.cards.remove(this.cards.size() - 1);
    }

}
