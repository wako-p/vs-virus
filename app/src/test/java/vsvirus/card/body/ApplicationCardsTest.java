package vsvirus.card.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import vsvirus.card.Color;
import vsvirus.card.virus.VirusCard;

class ApplicationCardsTest {

    @Nested
    class AddTest {
        @Test
        @DisplayName("2枚までカードを追加できる")
        void success() {
            // Given:
            var applicationCards = new ApplicationCards();
            var virus1 = VirusCard.create(Color.BLUE);
            var virus2 = VirusCard.create(Color.BLUE);

            // When:
            applicationCards.add(virus1);
            applicationCards.add(virus2);

            // Then:
            assertEquals(virus1, applicationCards.getEvilCards().get(0));
            assertEquals(virus2, applicationCards.getEvilCards().get(1));
        }

        @Test
        @DisplayName("2枚を超えてカードを追加しようとすると例外がスローされる")
        void failure() {
            // Given:
            var applicationCards = new ApplicationCards();
            var virus1 = VirusCard.create(Color.BLUE);
            var virus2 = VirusCard.create(Color.BLUE);
            applicationCards.add(virus1);
            applicationCards.add(virus2);

            // When/Then:
            assertThrows(IllegalStateException.class, () -> {
                var virus3 = VirusCard.create(Color.BLUE);
                applicationCards.add(virus3);
            });
        }
    }

    @Nested
    class IsFullTest {

        @Test
        @DisplayName("カードが2枚の場合はtrueを返す")
        void success1() {
            // Given:
            var applicationCards = new ApplicationCards();
            var virus1 = VirusCard.create(Color.BLUE);
            var virus2 = VirusCard.create(Color.BLUE);
            applicationCards.add(virus1);
            applicationCards.add(virus2);

            // When:
            var actual = applicationCards.isFull();

            // Then:
            assertTrue(actual);
        }

        @Test
        @DisplayName("カードが1枚の場合はfalseを返す")
        void success2() {
            // Given:
            var applicationCards = new ApplicationCards();
            var virus1 = VirusCard.create(Color.BLUE);
            applicationCards.add(virus1);

            // When:
            var actual = applicationCards.isFull();

            // Then:
            assertFalse(actual);
        }

        @Test
        @DisplayName("カードが0枚の場合はfalseを返す")
        void success3() {
            // Given:
            var applicationCards = new ApplicationCards();

            // When:
            var actual = applicationCards.isFull();

            // Then:
            assertFalse(actual);
        }

    }

    @Nested
    class IsNotFullTest {

        @Test
        @DisplayName("カードが2枚の場合はfalseを返す")
        void success1() {
            // Given:
            var applicationCards = new ApplicationCards();
            var virus1 = VirusCard.create(Color.BLUE);
            var virus2 = VirusCard.create(Color.BLUE);
            applicationCards.add(virus1);
            applicationCards.add(virus2);

            // When:
            var actual = applicationCards.isNotFull();

            // Then:
            assertFalse(actual);
        }

        @Test
        @DisplayName("カードが1枚の場合はtrueを返す")
        void success2() {
            // Given:
            var applicationCards = new ApplicationCards();
            var virus1 = VirusCard.create(Color.BLUE);
            applicationCards.add(virus1);

            // When:
            var actual = applicationCards.isNotFull();

            // Then:
            assertTrue(actual);
        }

        @Test
        @DisplayName("カードが0枚の場合はtrueを返す")
        void success3() {
            // Given:
            var applicationCards = new ApplicationCards();

            // When:
            var actual = applicationCards.isNotFull();

            // Then:
            assertTrue(actual);
        }

    }

    @Nested
    class RemoveLastTest {

        @Test
        void success1() {
            // Given:
            var applicationCards = new ApplicationCards();
            var virus = VirusCard.create(Color.BLUE);
            applicationCards.add(virus);

            // When:
            var actual = applicationCards.removeLast();

            // Then:
            assertEquals(0, applicationCards.getEvilCards().size());
            assertEquals(virus, actual);
        }

        @Test
        void success2() {
            // Given:
            var applicationCards = new ApplicationCards();
            var virsu1 = VirusCard.create(Color.BLUE);
            var virsu2 = VirusCard.create(Color.BLUE);
            applicationCards.add(virsu1);
            applicationCards.add(virsu2);

            // When:
            var actual = applicationCards.removeLast();

            // Then:
            assertEquals(1, applicationCards.getEvilCards().size());
            assertEquals(virsu1, applicationCards.getEvilCards().get(0));
            assertEquals(virsu2, actual);
        }

        @Test
        void success3() {
            // Given:
            var applicationCards = new ApplicationCards();
            var virsu1 = VirusCard.create(Color.BLUE);
            var virsu2 = VirusCard.create(Color.BLUE);
            applicationCards.add(virsu1);
            applicationCards.add(virsu2);

            // When:
            var actual2 = applicationCards.removeLast();
            var actual1 = applicationCards.removeLast();

            // Then:
            assertEquals(0, applicationCards.getEvilCards().size());
            assertEquals(virsu1, actual1);
            assertEquals(virsu2, actual2);
        }

        @Test
        void failure1() {
            // Given:
            var applicationCards = new ApplicationCards();

            // When/Then:
            assertThrows(IllegalStateException.class, () -> {
                applicationCards.removeLast();
            });
        }

    }

    @Nested
    class RemoveAllTest {

        @Test
        void success1() {
            // Given:
            var applicationCards = new ApplicationCards();

            // When:
            var actual = applicationCards.removeAll();

            // Then:
            assertEquals(true, applicationCards.isEmpty());
            assertEquals(true, actual.isEmpty());
        }

        @Test
        void success2() {
            // Given:
            var applicationCards = new ApplicationCards();
            var virsu1 = VirusCard.create(Color.BLUE);
            applicationCards.add(virsu1);

            // When:
            var actual = applicationCards.removeAll();

            // Then:
            assertEquals(true, applicationCards.isEmpty());
            assertEquals(virsu1, actual.get(0));
        }

        @Test
        void success3() {
            // Given:
            var applicationCards = new ApplicationCards();
            var virsu1 = VirusCard.create(Color.BLUE);
            var virsu2 = VirusCard.create(Color.BLUE);
            applicationCards.add(virsu1);
            applicationCards.add(virsu2);

            // When:
            var actual = applicationCards.removeAll();

            // Then:
            assertEquals(true, applicationCards.isEmpty());
            assertEquals(virsu1, actual.get(0));
            assertEquals(virsu2, actual.get(1));
        }

    }

}
