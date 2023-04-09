package vsvirus.card.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import vsvirus.card.Color;
import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.virus.VirusCard;

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
        void からだパーツカードの色を取得することができる(Color color, Color expected) {
            // Given:
            var card = BodyPartCard.create(color);

            // When:
            var actual = card.color();

            // Then:
            assertEquals(expected, actual);
        }
    }

    @Nested
    class ApplyTest {

        @Test
        void からだパーツカードの状態が健康のときに同じ色のウィルスカードを適用すると状態が感染になる() {
            // Given:
            var virus = VirusCard.create(Color.BLUE);
            var body = BodyPartCard.create(Color.BLUE);

            // When:
            body.apply(virus);

            // Then:
            assertEquals(Status.INFECTED, body.status());
        }

        @Test
        void からだパーツカードの状態が感染のときに同じ色のウィルスカードを適用すると状態が発症になる() {
            // Given:
            var virus1 = VirusCard.create(Color.BLUE);
            var virus2 = VirusCard.create(Color.BLUE);

            var body = BodyPartCard.create(Color.BLUE);
            body.apply(virus1);

            // When:
            body.apply(virus2);

            // Then:
            assertEquals(Status.SYMPTOMATIC, body.status());
        }

        @Test
        void からだパーツカードの状態が仮免疫のときに同じ色の薬カードを適用すると状態が健康になる() {
            // Given:
            var medicine = MedicineCard.create(Color.BLUE);
            var virus = VirusCard.create(Color.BLUE);

            var body = BodyPartCard.create(Color.BLUE);
            body.apply(medicine);

            // When:
            body.apply(virus);

            // Then:
            assertEquals(Status.HEALTHY, body.status());
        }

        static Stream<Arguments> colorPattern1() {
            return Stream.of(
                Arguments.of(Color.RED),
                Arguments.of(Color.GREEN),
                Arguments.of(Color.YELLOW)
            );
        }

        static Stream<Arguments> virusCardApplicationPatterns() {
            return Stream.of(
                Arguments.of(BodyPartCard.create(Color.BLUE), VirusCard.create(Color.RED)),
                Arguments.of(BodyPartCard.create(Color.BLUE), VirusCard.create(Color.GREEN)),
                Arguments.of(BodyPartCard.create(Color.BLUE), VirusCard.create(Color.YELLOW)),
                Arguments.of(BodyPartCard.create(Color.RED), VirusCard.create(Color.GREEN)),
                Arguments.of(BodyPartCard.create(Color.RED), VirusCard.create(Color.YELLOW)),
                Arguments.of(BodyPartCard.create(Color.RED), VirusCard.create(Color.BLUE)),
                Arguments.of(BodyPartCard.create(Color.GREEN), VirusCard.create(Color.YELLOW)),
                Arguments.of(BodyPartCard.create(Color.GREEN), VirusCard.create(Color.BLUE)),
                Arguments.of(BodyPartCard.create(Color.GREEN), VirusCard.create(Color.RED)),
                Arguments.of(BodyPartCard.create(Color.YELLOW), VirusCard.create(Color.BLUE)),
                Arguments.of(BodyPartCard.create(Color.YELLOW), VirusCard.create(Color.RED)),
                Arguments.of(BodyPartCard.create(Color.YELLOW), VirusCard.create(Color.GREEN))
            );
        }

        @ParameterizedTest
        @MethodSource("virusCardApplicationPatterns")
        void からだパーツカードと違う色のウィルスカードを適用すると例外がスローされる(BodyPartCard body, VirusCard virus) {
            // When/Then:
            assertThrows(IllegalArgumentException.class, () -> {
                body.apply(virus);
            });
        }

        @Test
        void からだパーツカードの状態が健康のときに同じ色の薬カードを適用すると状態が仮免疫になる() {
            // Given:
            var medicine = MedicineCard.create(Color.BLUE);
            var body = BodyPartCard.create(Color.BLUE);

            // When:
            body.apply(medicine);

            // Given:
            assertEquals(Status.PASSIVELY_IMMUNIZED, body.status());
        }

        @Test
        void からだパーツカードの状態が仮免疫のときに同じ色の薬カードを適用すると状態が免疫になる() {
            // Given:
            var medicine1 = MedicineCard.create(Color.BLUE);
            var medicine2 = MedicineCard.create(Color.BLUE);

            var body = BodyPartCard.create(Color.BLUE);
            body.apply(medicine1);

            // When:
            body.apply(medicine2);

            // Given:
            assertEquals(Status.IMMUNIZED, body.status());
        }

        @Test
        void からだパーツカードの状態が感染のときに同じ色の薬カードを適用すると状態が健康になる() {
            // Given:
            var virus = VirusCard.create(Color.BLUE);
            var medicine = MedicineCard.create(Color.BLUE);

            var body = BodyPartCard.create(Color.BLUE);
            body.apply(virus);

            // When:
            body.apply(medicine);

            // Given:
            assertEquals(Status.HEALTHY, body.status());
        }

        static Stream<Arguments> medicneCardApplicationPatterns() {
            return Stream.of(
                Arguments.of(BodyPartCard.create(Color.BLUE), MedicineCard.create(Color.RED)),
                Arguments.of(BodyPartCard.create(Color.BLUE), MedicineCard.create(Color.GREEN)),
                Arguments.of(BodyPartCard.create(Color.BLUE), MedicineCard.create(Color.YELLOW)),
                Arguments.of(BodyPartCard.create(Color.RED), MedicineCard.create(Color.GREEN)),
                Arguments.of(BodyPartCard.create(Color.RED), MedicineCard.create(Color.YELLOW)),
                Arguments.of(BodyPartCard.create(Color.RED), MedicineCard.create(Color.BLUE)),
                Arguments.of(BodyPartCard.create(Color.GREEN), MedicineCard.create(Color.YELLOW)),
                Arguments.of(BodyPartCard.create(Color.GREEN), MedicineCard.create(Color.BLUE)),
                Arguments.of(BodyPartCard.create(Color.GREEN), MedicineCard.create(Color.RED)),
                Arguments.of(BodyPartCard.create(Color.YELLOW), MedicineCard.create(Color.BLUE)),
                Arguments.of(BodyPartCard.create(Color.YELLOW), MedicineCard.create(Color.RED)),
                Arguments.of(BodyPartCard.create(Color.YELLOW), MedicineCard.create(Color.GREEN))
            );
        }

        @ParameterizedTest
        @MethodSource("medicneCardApplicationPatterns")
        void からだパーツカードの色と違う薬カードを適用すると例外がスローされる(BodyPartCard body, MedicineCard medicine) {
            // When/Then:
            assertThrows(IllegalArgumentException.class, () -> {
                body.apply(medicine);
            });
        }

        static Stream<Arguments> bodyPartCards() {
            return Stream.of(
                Arguments.of(BodyPartCard.create(Color.BLUE)),
                Arguments.of(BodyPartCard.create(Color.RED)),
                Arguments.of(BodyPartCard.create(Color.GREEN)),
                Arguments.of(BodyPartCard.create(Color.YELLOW))
            );
        }

        @ParameterizedTest
        @MethodSource("bodyPartCards")
        void ウィルスカードの色がマルチカラーの場合は全ての色のからだパーツカードに適用できる(BodyPartCard body) {
            // Given:
            var virus = VirusCard.create(Color.MULTI);

            // When:
            body.apply(virus);

            // Then:
            assertEquals(Status.INFECTED, body.status());
        }

        @ParameterizedTest
        @MethodSource("bodyPartCards")
        void 薬カードの色がマルチカラーの場合は全ての色のからだパーツカードに適用できる(BodyPartCard body) {
            // Given:
            var medicine = MedicineCard.create(Color.MULTI);

            // When:
            body.apply(medicine);

            // Then:
            assertEquals(Status.PASSIVELY_IMMUNIZED, body.status());
        }

    }

}
