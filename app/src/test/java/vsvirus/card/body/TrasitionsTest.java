package vsvirus.card.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import vsvirus.card.ICard;
import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.virus.VirusCard;

class TrasitionsTest {
    @Nested
    class NextTest {

        static Stream<Arguments> data() {
            return Stream.of(
                Arguments.of(Status.HEALTHY, VirusCard.class, Status.INFECTED),
                Arguments.of(Status.HEALTHY, MedicineCard.class, Status.PASSIVELY_IMMUNIZED),
                Arguments.of(Status.INFECTED, VirusCard.class, Status.SYMPTOMATIC),
                Arguments.of(Status.INFECTED, MedicineCard.class, Status.HEALTHY),
                Arguments.of(Status.PASSIVELY_IMMUNIZED, VirusCard.class, Status.HEALTHY),
                Arguments.of(Status.PASSIVELY_IMMUNIZED, MedicineCard.class, Status.IMMUNIZED)
            );
        }

        @ParameterizedTest
        @MethodSource("data")
        @DisplayName("引数に遷移前の状態とカードの型を指定すると遷移後の状態を取得できる")
        void success(Status from, Class<ICard> type, Status expected) {
            // Given:
            var trasitions = new Trasitions();

            // When:
            var actual = trasitions.next(from, type);

            // Then:
            assertEquals(expected, actual);
        }

        static Stream<Arguments> types() {
            return Stream.of(
                Arguments.of(VirusCard.class),
                Arguments.of(MedicineCard.class)
            );
        }

        @ParameterizedTest
        @MethodSource("types")
        @DisplayName("免疫からは遷移できない")
        void failure1(Class<? extends ICard> type) {
            // Given:
            var trasitions = new Trasitions();

            // When/Then:
            assertThrows(IllegalArgumentException.class, () -> {
                trasitions.next(Status.IMMUNIZED, type);
            });
        }

        @ParameterizedTest
        @MethodSource("types")
        @DisplayName("発症からは遷移できない")
        void failure2(Class<? extends ICard> type) {
            // Given:
            var trasitions = new Trasitions();

            // When/Then:
            assertThrows(IllegalArgumentException.class, () -> {
                trasitions.next(Status.SYMPTOMATIC, type);
            });
        }

    }
}
