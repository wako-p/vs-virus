package virus.card.body;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import virus.card.Color;
import virus.card.ICard;

class BodyPartCardFactoryTest {
    @Nested
    class CreateTest {
        @Test
        void 青赤緑黄色のカードがそれぞれ5枚ずつとマルチカラーのカードが1枚生成される() {
            // Given:
            var factory = new BodyPartCardFactory();

            // When:
            var cards = factory.create();

            // Then:
            assertEquals(5, countBule(cards));
            assertEquals(5, countRed(cards));
            assertEquals(5, countGreen(cards));
            assertEquals(5, countYellow(cards));
            assertEquals(1, countMulti(cards));
        }

        int countBule(List<ICard> cards) {
            return (int)(cards
                .stream()
                .filter(card -> card.color() == Color.BLUE)
                .count());
        }

        int countRed(List<ICard> cards) {
            return (int)(cards
                .stream()
                .filter(card -> card.color() == Color.RED)
                .count());
        }

        int countGreen(List<ICard> cards) {
            return (int)(cards
                .stream()
                .filter(card -> card.color() == Color.GREEN)
                .count());
        }

        int countYellow(List<ICard> cards) {
            return (int)(cards
                .stream()
                .filter(card -> card.color() == Color.YELLOW)
                .count());
        }

        int countMulti(List<ICard> cards) {
            return (int)(cards
                .stream()
                .filter(card -> card.color() == Color.MULTI)
                .count());
        }

    }
}