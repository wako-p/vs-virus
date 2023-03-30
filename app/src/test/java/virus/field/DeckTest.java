package virus.field;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import virus.card.Color;

class DeckTest {

    @Nested
    class CreateTest {
        @Test
        void カードが38の状態で生成される() {
            // When:
            var deck = Deck.create();

            // Then:
            assertEquals(38, deck.count());
            deck.evilCards().forEach(card -> System.out.println(card.color()));
        }
    }

    @Nested
    class ShuffleTest {
        @Test
        void 山札をシャッフルすることができる() {
            // Given:
            var deck = Deck.create();

            // When:
            deck.shuffle();

            // Then:
            deck.evilCards().forEach(card -> System.out.println(card.color()));
        }
    }

    @Nested
    class DrawTest {
        @Test
        void 山札からカードを1枚引くことができる() {
            // Given:
            var deck = Deck.create();

            // When:
            var card = deck.draw();

            // Then:
            assertEquals(37, deck.count());
            assertEquals(Color.MULTI, card.color());
        }
    }
}
