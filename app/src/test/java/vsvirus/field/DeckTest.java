package vsvirus.field;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import vsvirus.card.Color;

class DeckTest {

    @Nested
    class CreateTest {
        @Test
        void 山札のカードが55枚で生成される() {
            // When:
            var deck = Deck.create();

            // Then:
            assertEquals(58, deck.getCount());
            deck.getEvilCards().forEach(card -> System.out.println(card.getColor()));
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
            deck.getEvilCards().forEach(card -> System.out.println(card.getColor()));
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
            assertEquals(57, deck.getCount());
            assertEquals(Color.MULTI, card.getColor());
        }
    }
}
