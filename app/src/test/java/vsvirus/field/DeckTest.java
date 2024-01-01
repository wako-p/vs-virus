package vsvirus.field;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Nested
    class CreateTest {
        @Test
        @DisplayName("山札のカードが58枚で生成される")
        void success() {
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
        @DisplayName("山札をシャッフルすることができる")
        void success() {
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
        @DisplayName("山札からカードを1枚引くことができる")
        void success() {
            // Given:
            var deck = Deck.create();

            // When:
            deck.draw();

            // Then:
            assertEquals(57, deck.getCount());
        }
    }
}
