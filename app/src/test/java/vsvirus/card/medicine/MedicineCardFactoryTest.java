package vsvirus.card.medicine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import vsvirus.card.Color;
import vsvirus.card.ICard;

class MedicineCardFactoryTest {
    @Nested
    class CreateTest {

        @Test
        void 青赤緑黄色とマルチカラーのカードがそれぞれ4枚ずつ生成される() {
            // Given:
            var factory = new MedicineCardFactory();

            // When:
            var cards = factory.create();

            // Then:
            assertEquals(4, countBule(cards));
            assertEquals(4, countRed(cards));
            assertEquals(4, countGreen(cards));
            assertEquals(4, countYellow(cards));
            assertEquals(4, countMulti(cards));
        }

        int countBule(List<ICard> cards) {
            return (int)(cards
                .stream()
                .filter(card -> card.getColor() == Color.BLUE)
                .count());
        }

        int countRed(List<ICard> cards) {
            return (int)(cards
                .stream()
                .filter(card -> card.getColor() == Color.RED)
                .count());
        }

        int countGreen(List<ICard> cards) {
            return (int)(cards
                .stream()
                .filter(card -> card.getColor() == Color.GREEN)
                .count());
        }

        int countYellow(List<ICard> cards) {
            return (int)(cards
                .stream()
                .filter(card -> card.getColor() == Color.YELLOW)
                .count());
        }

        int countMulti(List<ICard> cards) {
            return (int)(cards
                .stream()
                .filter(card -> card.getColor() == Color.MULTI)
                .count());
        }

    }
}