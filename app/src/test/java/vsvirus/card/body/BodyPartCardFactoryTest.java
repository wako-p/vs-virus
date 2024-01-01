package vsvirus.card.body;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import vsvirus.card.Color;
import vsvirus.card.ICard;

class BodyPartCardFactoryTest {
    @Nested
    class CreateTest {
        @Test
        @DisplayName("BRULE/RED/GREEN/YELLOWカードがそれぞれ5枚とMULTIカードが1枚生成される")
        void success() {
            // Given:
            var factory = new BodyPartCardFactory();

            // When:
            var cards = factory.create();

            // Then:
            assertEquals(5, count(Color.BLUE, cards));
            assertEquals(5, count(Color.RED, cards));
            assertEquals(5, count(Color.GREEN, cards));
            assertEquals(5, count(Color.YELLOW, cards));
            assertEquals(1, count(Color.MULTI, cards));
        }

        int count(final Color color, final List<ICard> cards) {
            return (int)(cards
                .stream()
                .filter(card -> card.getColor() == color)
                .count());
        }

    }
}