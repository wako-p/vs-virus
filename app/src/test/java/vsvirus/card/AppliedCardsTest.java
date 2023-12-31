package vsvirus.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import vsvirus.card.virus.VirusCard;

class AppliedCardsTest {

    @Nested
    class AddTest {
        @Test
        void 最大枚数が2枚のときにカードを1枚追加できる() {
            // Given:
            AppliedCards cards = new AppliedCards(2);
            var virus1 = VirusCard.create(Color.BLUE);

            // When:
            cards.add(virus1);

            // Then:
            assertEquals(virus1, cards.getEvilCards().get(0));
        }

        @Test
        void 最大枚数が2枚のときにカードを2枚追加できる() {
            // Given:
            AppliedCards cards = new AppliedCards(2);
            var virus1 = VirusCard.create(Color.BLUE);
            var virus2 = VirusCard.create(Color.BLUE);
            
            // When:
            cards.add(virus1);
            cards.add(virus2);

            // Then:
            assertEquals(virus1, cards.getEvilCards().get(0));
            assertEquals(virus2, cards.getEvilCards().get(1));
        }

        @Test
        void 最大枚数が2枚のときにカードを3枚追加すると例外がスローされる() {
            // Given:
            AppliedCards cards = new AppliedCards(2);
            var virus1 = VirusCard.create(Color.BLUE);
            var virus2 = VirusCard.create(Color.BLUE);
            cards.add(virus1);
            cards.add(virus2);
            
            // When/Then:
            assertThrows(IllegalStateException.class, () -> {
                var virus3 = VirusCard.create(Color.BLUE);
                cards.add(virus3);
            });
        }
    }
}
