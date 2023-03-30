package virus.field;

import java.util.Collections;
import java.util.List;

import virus.card.BodyPartCardFactory;
import virus.card.ICard;

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
        var cards = bodyPartCardFactory.create();

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
