package vsvirus.card.medicine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import vsvirus.card.Color;
import vsvirus.card.ICard;

class MedicineCardFactoryTest {
    @Nested
    class CreateTest {

        @Test
        @DisplayName("BRULE/RED/GREEN/YELLOW/MULTIカードがそれぞれ4枚ずつ生成される")
        void success() {
            // Given:
            var factory = new MedicineCardFactory();

            // When:
            var cards = factory.create();

            // Then:
            assertEquals(4, count(Color.BLUE, cards));
            assertEquals(4, count(Color.RED, cards));
            assertEquals(4, count(Color.GREEN, cards));
            assertEquals(4, count(Color.YELLOW, cards));
            assertEquals(4, count(Color.MULTI, cards));
        }

        int count(final Color color , final List<ICard> cards) {
            return (int)(cards
                .stream()
                .filter(card -> card.getColor() == color)
                .count());
        }

    }
}