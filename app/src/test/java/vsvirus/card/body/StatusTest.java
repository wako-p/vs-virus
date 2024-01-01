package vsvirus.card.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import vsvirus.card.Color;
import vsvirus.card.ICard;
import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.virus.VirusCard;

class StatusTest {
    @Nested
    class NextTest {
        @Test
        @DisplayName("健康から感染に遷移できる")
        void success1() {
            // Given:
            var card = VirusCard.create(Color.BLUE);
            var status = Status.HEALTHY;

            // When:
            var actual = status.next(card);

            // Then:
            assertEquals(Status.INFECTED, actual);
        }

        @Test
        @DisplayName("健康から仮免疫に遷移できる")
        void success2() {
            // Given:
            var card = MedicineCard.create(Color.BLUE);
            var status = Status.HEALTHY;

            // When:
            var actual = status.next(card);

            // Then:
            assertEquals(Status.PASSIVELY_IMMUNIZED, actual);
        }

        @Test
        @DisplayName("感染から発症に遷移できる")
        void success3() {
            // Given:
            var card = VirusCard.create(Color.BLUE);
            var status = Status.INFECTED;

            // When:
            var actual = status.next(card);

            // Then:
            assertEquals(Status.SYMPTOMATIC, actual);
        }

        @Test
        @DisplayName("感染から健康に遷移できる")
        void success4() {
            // Given:
            var card = MedicineCard.create(Color.BLUE);
            var status = Status.INFECTED;

            // When:
            var actual = status.next(card);

            // Then:
            assertEquals(Status.HEALTHY, actual);
        }

        @Test
        @DisplayName("仮免疫から健康に遷移できる")
        void success5() {
            // Given:
            var card = VirusCard.create(Color.BLUE);
            var status = Status.PASSIVELY_IMMUNIZED;

            // When:
            var actual = status.next(card);

            // Then:
            assertEquals(Status.HEALTHY, actual);
        }

        @Test
        @DisplayName("仮免疫から免疫に遷移できる")
        void success6() {
            // Given:
            var card = MedicineCard.create(Color.BLUE);
            var status = Status.PASSIVELY_IMMUNIZED;

            // When:
            var actual = status.next(card);

            // Then:
            assertEquals(Status.IMMUNIZED, actual);
        }

        static Stream<Arguments> cards() {
            return Stream.of(
                Arguments.of(VirusCard.create(Color.BLUE)),
                Arguments.of(MedicineCard.create(Color.BLUE))
            );
        }

        @ParameterizedTest
        @MethodSource("cards")
        @DisplayName("免疫からは遷移できない")
        void failure1(ICard card) {
            // Given:
            var status = Status.IMMUNIZED;

            // When/Then:
            assertThrows(IllegalArgumentException.class, () -> {
                status.next(card);
            });
        }

        @ParameterizedTest
        @MethodSource("cards")
        @DisplayName("発症からは遷移できない")
        void failrue2(ICard card) {
            // Given:
            var status = Status.SYMPTOMATIC;

            // When/Then:
            assertThrows(IllegalArgumentException.class, () -> {
                status.next(card);
            });
        }
    }
}
