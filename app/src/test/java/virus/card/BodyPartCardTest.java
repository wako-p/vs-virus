package virus.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BodyPartCardTest {

    @Nested
    class CreateTest {

        static Stream<Arguments> colors() {
            return Stream.of(
                Arguments.of(Color.BLUE, Color.BLUE),
                Arguments.of(Color.RED, Color.RED),
                Arguments.of(Color.GREEN, Color.GREEN),
                Arguments.of(Color.YELLOW, Color.YELLOW),
                Arguments.of(Color.MULTI, Color.MULTI)
            );
        }

        @ParameterizedTest
        @MethodSource("colors")
        void 引数に指定した色の健康なからだパーツカードを生成できる(Color color, Color expected) {
            // When:
            var card = BodyPartCard.create(color);

            // Then:
            assertEquals(expected, card.color());
            assertEquals(Status.HEALTHY, card.status());
        }

    }

    @Nested
    class ColorTest {

        static Stream<Arguments> colors() {
            return Stream.of(
                Arguments.of(Color.BLUE, Color.BLUE),
                Arguments.of(Color.RED, Color.RED),
                Arguments.of(Color.GREEN, Color.GREEN),
                Arguments.of(Color.YELLOW, Color.YELLOW),
                Arguments.of(Color.MULTI, Color.MULTI)
            );
        }

        @ParameterizedTest
        @MethodSource("colors")
        void 色を取得することができる(Color color, Color expected) {
            // Given:
            var card = BodyPartCard.create(color);

            // When:
            var actual = card.color();

            // Then:
            assertEquals(expected, actual);
        }
    }

    @Nested
    class InfectionTest {
        @Test
        void 健康から感染() {
            // Given:
            var virus = VirusCard.create(Color.BLUE);
            var body = BodyPartCard.create(Color.BLUE);

            // When:
            body.infection(virus);

            // Then:
            assertEquals(Status.INFECTED, body.status());
        }

        @Test
        void 感染から発症() {
            // Given:
            var virus1 = VirusCard.create(Color.BLUE);
            var virus2 = VirusCard.create(Color.BLUE);

            var body = BodyPartCard.create(Color.BLUE);
            body.infection(virus1);

            // When:
            body.infection(virus2);

            // Then:
            assertEquals(Status.SYMPTOMATIC, body.status());
        }

        @Test
        void 仮免疫から健康() {
            // Given:
            var medicine = MedicineCard.create(Color.BLUE);
            var virus = VirusCard.create(Color.BLUE);

            var body = BodyPartCard.create(Color.BLUE);
            body.care(medicine);

            // When:
            body.infection(virus);

            // Then:
            assertEquals(Status.HEALTHY, body.status());
        }

        static Stream<Arguments> colors1() {
            return Stream.of(
                Arguments.of(Color.RED),
                Arguments.of(Color.GREEN),
                Arguments.of(Color.YELLOW)
            );
        }

        @ParameterizedTest
        @MethodSource("colors1")
        void 引数に指定したウィルスカードの色がからだパーツカードと異なる場合は例外がスローされる(Color color) {
            // Given:
            var virus = VirusCard.create(color);
            var body = BodyPartCard.create(Color.BLUE);

            // When/Then:
            assertThrows(IllegalArgumentException.class, () -> {
                body.infection(virus);
            });
        }

        static Stream<Arguments> colors2() {
            return Stream.of(
                Arguments.of(Color.RED),
                Arguments.of(Color.GREEN),
                Arguments.of(Color.YELLOW)
            );
        }

        @ParameterizedTest
        @MethodSource("colors2")
        void ウィルスカードの色がマルチカードの場合は全ての色のからだパーツカードに感染できる(Color color) {
            // Given:
            var virus = VirusCard.create(Color.MULTI);
            var body = BodyPartCard.create(color);

            // When:
            body.infection(virus);

            // Then:
            assertEquals(Status.INFECTED, body.status());
        }

    }

    @Nested
    class CareTest {

        @Test
        void 健康から仮免疫() {
            // Given:
            var medicine = MedicineCard.create(Color.BLUE);
            var body = BodyPartCard.create(Color.BLUE);

            // When:
            body.care(medicine);

            // Given:
            assertEquals(Status.PASSIVELY_IMMUNIZED, body.status());
        }

        @Test
        void 仮免疫から免疫() {
            // Given:
            var medicine1 = MedicineCard.create(Color.BLUE);
            var medicine2 = MedicineCard.create(Color.BLUE);

            var body = BodyPartCard.create(Color.BLUE);
            body.care(medicine1);

            // When:
            body.care(medicine2);

            // Given:
            assertEquals(Status.IMMUNIZED, body.status());
        }

        @Test
        void 感染から健康() {
            // Given:
            var virus = VirusCard.create(Color.BLUE);
            var medicine = MedicineCard.create(Color.BLUE);

            var body = BodyPartCard.create(Color.BLUE);
            body.infection(virus);

            // When:
            body.care(medicine);

            // Given:
            assertEquals(Status.HEALTHY, body.status());
        }

        static Stream<Arguments> colors1() {
            return Stream.of(
                Arguments.of(Color.RED),
                Arguments.of(Color.GREEN),
                Arguments.of(Color.YELLOW)
            );
        }

        @ParameterizedTest
        @MethodSource("colors1")
        void 引数に指定した薬カードの色がからだパーツカードと異なる場合は例外がスローされる(Color color) {
            // Given:
            var medicine = MedicineCard.create(color);
            var body = BodyPartCard.create(Color.BLUE);

            // When/Then:
            assertThrows(IllegalArgumentException.class, () -> {
                body.care(medicine);
            });
        }

        static Stream<Arguments> colors2() {
            return Stream.of(
                Arguments.of(Color.RED),
                Arguments.of(Color.GREEN),
                Arguments.of(Color.YELLOW)
            );
        }

        @ParameterizedTest
        @MethodSource("colors2")
        void 薬カードの色がマルチカードの場合は全ての色のからだパーツカードに感染できる(Color color) {
            // Given:
            var medicine = MedicineCard.create(Color.MULTI);
            var body = BodyPartCard.create(color);

            // When:
            body.care(medicine);

            // Then:
            assertEquals(Status.PASSIVELY_IMMUNIZED, body.status());
        }

    }

}
