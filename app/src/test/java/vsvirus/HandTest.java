package vsvirus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import vsvirus.card.Color;
import vsvirus.card.body.BodyPartCard;

class HandTest {

    @Nested
    class CreateTest {
        @Test
        void 手札のカードが0枚で生成される() {
            // When:
            var hand = Hand.create();

            // Then:
            assertEquals(0, hand.getCount());
        }
    }

    @Nested
    class AddTest {

        @Test
        void 手札にカードを3枚まで追加することができる() {
            // Given:
            var card1 = BodyPartCard.create(Color.BLUE);
            var card2 = BodyPartCard.create(Color.RED);
            var card3 = BodyPartCard.create(Color.GREEN);
            var hand = Hand.create();
            
            // When:
            hand.add(card1);
            hand.add(card2);
            hand.add(card3);

            // Then:
            assertEquals(3, hand.getCount());
        }

        @Test
        void 手札が3枚のときにカードを追加すると例外がスローされる() {
            // Given:
            var card1 = BodyPartCard.create(Color.BLUE);
            var card2 = BodyPartCard.create(Color.RED);
            var card3 = BodyPartCard.create(Color.GREEN);
            var card4 = BodyPartCard.create(Color.YELLOW);

            var hand = Hand.create();
            hand.add(card1);
            hand.add(card2);
            hand.add(card3);

            // When/Then:
            assertThrows(RuntimeException.class, () -> {
                hand.add(card4);
            });
        }

    }

}
