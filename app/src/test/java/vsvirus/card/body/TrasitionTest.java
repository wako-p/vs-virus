package vsvirus.card.body;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import vsvirus.card.Color;
import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.virus.VirusCard;

class TrasitionTest {

    @Nested
    class MatchTest {

        @Test
        @DisplayName("引数に指定したカードの型と遷移前の状態が一致する場合はtrueが返る")
        void success1() {
            // Given:
            var trasition = new Trasition(Status.HEALTHY, Status.INFECTED, VirusCard.class);

            // When:
            var card = VirusCard.create(Color.BLUE);
            var actual = trasition.match(Status.HEALTHY, card.getClass());

            // Then:
            assertEquals(true, actual);
        }

        @Test
        @DisplayName("引数に指定した遷移前の状態が一致しない場合はfalseが返る")
        void success2() {
            // Given:
            var trasition = new Trasition(Status.HEALTHY, Status.INFECTED, VirusCard.class);

            // When:
            var card = VirusCard.create(Color.BLUE);
            var actual = trasition.match(Status.INFECTED, card.getClass());

            // Then:
            assertEquals(false, actual);
        }

        @Test
        @DisplayName("引数に指定したカードの型が一致しない場合はfalseが返る")
        void success3() {
            // Given:
            var trasition = new Trasition(Status.HEALTHY, Status.INFECTED, VirusCard.class);

            // When:
            var card = MedicineCard.create(Color.BLUE);
            var actual = trasition.match(Status.HEALTHY, card.getClass());

            // Then:
            assertEquals(false, actual);
        }

    }

    @Nested
    class Next {

        static Stream<Arguments> status() {
            return Stream.of(
                Arguments.of(Status.HEALTHY, Status.HEALTHY),
                Arguments.of(Status.INFECTED, Status.INFECTED),
                Arguments.of(Status.SYMPTOMATIC, Status.SYMPTOMATIC),
                Arguments.of(Status.PASSIVELY_IMMUNIZED, Status.PASSIVELY_IMMUNIZED),
                Arguments.of(Status.IMMUNIZED, Status.IMMUNIZED)
            );
        }

        @ParameterizedTest
        @MethodSource("status")
        @DisplayName("生成時に指定した遷移後の状態が返される")
        void success(Status from, Status expected) {
            // Given:
            var trasition = new Trasition(Status.HEALTHY, from, VirusCard.class);

            // When:
            var actual = trasition.next();

            // Then:
            assertEquals(expected, actual);
        }

    }

}
