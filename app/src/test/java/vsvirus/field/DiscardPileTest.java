package vsvirus.field;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import vsvirus.card.Color;
import vsvirus.card.body.BodyPartCard;
import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.virus.VirusCard;

class DiscardPileTest {

    @Nested
    class CreateTest {
        @Test
        @DisplayName("捨て札のカードが0枚で生成される")
        void success() {
            // When:
            var discardPile = DiscardPile.create();

            // Then:
            assertEquals(0, discardPile.getCount());
        }
    }

    @Nested
    class PlaceTest {
        @Test
        @DisplayName("捨て札にカードを置くことができる")
        void success() {
            // Given:
            var card1 = BodyPartCard.create(Color.BLUE);
            var card2 = MedicineCard.create(Color.RED);
            var card3 = VirusCard.create(Color.GREEN);
            var discardPile = DiscardPile.create();

            // When:
            discardPile.place(card1);
            discardPile.place(card2);
            discardPile.place(card3);

            // Then:
            assertEquals(3, discardPile.getCount());
            assertEquals(card1, discardPile.getEvilCards().get(0));
            assertEquals(card2, discardPile.getEvilCards().get(1));
            assertEquals(card3, discardPile.getEvilCards().get(2));
        }
    }
}
