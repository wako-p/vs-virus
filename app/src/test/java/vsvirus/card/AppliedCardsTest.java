package vsvirus.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import vsvirus.card.virus.VirusCard;

class AppliedCardsTest {

    @Nested
    class AddTest {
        @Test
        @DisplayName("最大枚数までカードを追加できる")
        void success() {
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
        @DisplayName("最大枚数を超えてカードを追加しようとすると例外がスローされる")
        void failure() {
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
