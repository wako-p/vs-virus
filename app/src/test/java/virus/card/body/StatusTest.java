package virus.card.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import virus.card.Color;
import virus.card.ICard;
import virus.card.medicine.MedicineCard;
import virus.card.virus.VirusCard;

class StatusTest {
    @Nested
    class NextTest {
        @Test
        void 健康から感染に遷移できる() {
            // Given:
            var card = VirusCard.create(Color.BLUE);
            var status = Status.HEALTHY;

            // When:
            var actual = status.next(card);

            // Then:
            assertEquals(Status.INFECTED, actual);
        }

        @Test
        void 健康から仮免疫に遷移できる() {
            // Given:
            var card = MedicineCard.create(Color.BLUE);
            var status = Status.HEALTHY;

            // When:
            var actual = status.next(card);

            // Then:
            assertEquals(Status.PASSIVELY_IMMUNIZED, actual);
        }

        @Test
        void 感染から発症に遷移できる() {
            // Given:
            var card = VirusCard.create(Color.BLUE);
            var status = Status.INFECTED;

            // When:
            var actual = status.next(card);

            // Then:
            assertEquals(Status.SYMPTOMATIC, actual);
        }

        @Test
        void 感染から健康に遷移できる() {
            // Given:
            var card = MedicineCard.create(Color.BLUE);
            var status = Status.INFECTED;

            // When:
            var actual = status.next(card);

            // Then:
            assertEquals(Status.HEALTHY, actual);
        }

        @Test
        void 仮免疫から健康に遷移できる() {
            // Given:
            var card = VirusCard.create(Color.BLUE);
            var status = Status.PASSIVELY_IMMUNIZED;

            // When:
            var actual = status.next(card);

            // Then:
            assertEquals(Status.HEALTHY, actual);
        }

        @Test
        void 仮免疫から免疫に遷移できる() {
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
        void 免疫からは遷移できない(ICard card) {
            // Given:
            var status = Status.IMMUNIZED;

            // When/Then:
            assertThrows(IllegalArgumentException.class, () -> {
                status.next(card);
            });
        }

        @ParameterizedTest
        @MethodSource("cards")
        void 発症からは遷移できない(ICard card) {
            // Given:
            var status = Status.SYMPTOMATIC;

            // When/Then:
            assertThrows(IllegalArgumentException.class, () -> {
                status.next(card);
            });
        }
    }
}
