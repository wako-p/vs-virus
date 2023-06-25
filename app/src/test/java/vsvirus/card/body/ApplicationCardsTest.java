package vsvirus.card.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import vsvirus.card.Color;
import vsvirus.card.virus.VirusCard;

class ApplicationCardsTest {
    @Nested
    class AddTest {
        @Test
        void カードを1枚追加できる() {
            // Given:
            ApplicationCards cards = new ApplicationCards();
            
            // When:
            var virus1 = VirusCard.create(Color.BLUE);
            cards.add(virus1);

            // Then:
            assertEquals(virus1, cards.getEvilCards().get(0));
        }

        @Test
        void カードを2枚追加できる() {
            // Given:
            ApplicationCards cards = new ApplicationCards();
            var virus1 = VirusCard.create(Color.BLUE);
            cards.add(virus1);

            // When:
            var virus2 = VirusCard.create(Color.BLUE);
            cards.add(virus2);

            // Then:
            assertEquals(virus1, cards.getEvilCards().get(0));
            assertEquals(virus2, cards.getEvilCards().get(1));
        }

        @Test
        void カードを3枚追加すると例外がスローされる() {
            // Given:
            ApplicationCards cards = new ApplicationCards();
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
