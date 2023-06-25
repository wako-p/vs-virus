package vsvirus.field;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import vsvirus.card.Color;
import vsvirus.card.body.BodyPartCard;

class DiscardPileTest {

    @Nested
    class CreateTest {
        @Test
        void 捨て札のカードが0枚で生成される() {
            // When:
            var discardPile = DiscardPile.create();

            // Then:
            assertEquals(0, discardPile.getCount());
        }
    }

    @Nested
    class PlaceTest {
        @Test
        void 捨て札にカードを置くことができる() {
            // Given:
            var card1 = BodyPartCard.create(Color.BLUE);
            var card2 = BodyPartCard.create(Color.RED);
            var card3 = BodyPartCard.create(Color.GREEN);
            var card4 = BodyPartCard.create(Color.YELLOW);
            var discardPile = DiscardPile.create();

            // When:
            discardPile.place(card1);
            discardPile.place(card2);
            discardPile.place(card3);
            discardPile.place(card4);

            // Then:
            assertEquals(4, discardPile.getCount());
            assertEquals(Color.BLUE, discardPile.getEvilCards().get(0).getColor());
            assertEquals(Color.RED, discardPile.getEvilCards().get(1).getColor());
            assertEquals(Color.GREEN, discardPile.getEvilCards().get(2).getColor());
            assertEquals(Color.YELLOW, discardPile.getEvilCards().get(3).getColor());
        }
    }
}
