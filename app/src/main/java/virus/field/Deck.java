package virus.field;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import virus.card.BodyPartCard;
import virus.card.Color;
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
        var cards = new LinkedList<ICard>(){{
            add(BodyPartCard.create(Color.BLUE));
            add(BodyPartCard.create(Color.BLUE));
            add(BodyPartCard.create(Color.BLUE));
            add(BodyPartCard.create(Color.BLUE));
            add(BodyPartCard.create(Color.BLUE));
            add(BodyPartCard.create(Color.RED));
            add(BodyPartCard.create(Color.RED));
            add(BodyPartCard.create(Color.RED));
            add(BodyPartCard.create(Color.RED));
            add(BodyPartCard.create(Color.RED));
            add(BodyPartCard.create(Color.GREEN));
            add(BodyPartCard.create(Color.GREEN));
            add(BodyPartCard.create(Color.GREEN));
            add(BodyPartCard.create(Color.GREEN));
            add(BodyPartCard.create(Color.GREEN));
            add(BodyPartCard.create(Color.YELLOW));
            add(BodyPartCard.create(Color.YELLOW));
            add(BodyPartCard.create(Color.YELLOW));
            add(BodyPartCard.create(Color.YELLOW));
            add(BodyPartCard.create(Color.YELLOW));
            add(BodyPartCard.create(Color.MULTI));
        }};
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
