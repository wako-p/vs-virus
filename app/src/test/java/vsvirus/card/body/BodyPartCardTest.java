package vsvirus.card.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import vsvirus.card.Color;
import vsvirus.card.ICard;
import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.virus.VirusCard;

class BodyPartCardTest {

    @Nested
    class CreateTest {

        static Stream<Arguments> successPattern() {
            return Stream.of(
                Arguments.of(Color.BLUE, Color.BLUE),
                Arguments.of(Color.RED, Color.RED),
                Arguments.of(Color.GREEN, Color.GREEN),
                Arguments.of(Color.YELLOW, Color.YELLOW),
                Arguments.of(Color.MULTI, Color.MULTI)
            );
        }

        @ParameterizedTest
        @MethodSource("successPattern")
        @DisplayName("引数に指定した色のからだパーツカードを生成できる。また、からだパーツカードの状態は健康となる。")
        void success(final Color color, final Color expected) {
            // When:
            var body = BodyPartCard.create(color);

            // Then:
            assertEquals(expected, body.getColor());
            assertEquals(Status.HEALTHY, body.getStatus());
        }

    }

    @Nested
    class ApplyTest {

        static Stream<Arguments> colors() {
            return Stream.of(
                Arguments.of(Color.BLUE),
                Arguments.of(Color.RED),
                Arguments.of(Color.GREEN),
                Arguments.of(Color.YELLOW));
        }

        @ParameterizedTest
        @MethodSource("colors")
        @DisplayName("からだパーツカードの状態が健康なときに同じ色のウィルスカードを適用すると状態が感染になる")
        void success1(final Color color) {
            // Given:
            var virus = VirusCard.create(color);
            var body = BodyPartCard.create(color);

            // When:
            body.apply(virus);

            // Then:
            assertEquals(Status.INFECTED, body.getStatus());
        }

        @ParameterizedTest
        @MethodSource("colors")
        @DisplayName("からだパーツカードの状態が感染のときに同じ色の薬カードを適用すると状態が健康になる")
        void success2(final Color color) {
            // Given:
            var virus = VirusCard.create(color);
            var medicine = MedicineCard.create(color);

            var body = BodyPartCard.create(color);
            body.apply(virus);

            // When:
            body.apply(medicine);

            // Given:
            assertEquals(Status.HEALTHY, body.getStatus());
        }

        @ParameterizedTest
        @MethodSource("colors")
        @DisplayName("からだパーツカードの状態が感染のときに同じ色のウィルスカードを適用すると状態が発症になる")
        void success3(final Color color) {
            // Given:
            var virus1 = VirusCard.create(color);
            var virus2 = VirusCard.create(color);

            var body = BodyPartCard.create(color);
            body.apply(virus1);

            // When:
            body.apply(virus2);

            // Then:
            assertEquals(Status.SYMPTOMATIC, body.getStatus());
        }

        @ParameterizedTest
        @MethodSource("colors")
        @DisplayName("からだパーツカードの状態が健康のときに同じ色の薬カードを適用すると状態が仮免疫になる")
        void success4(final Color color) {
            // Given:
            var medicine = MedicineCard.create(color);
            var body = BodyPartCard.create(color);

            // When:
            body.apply(medicine);

            // Given:
            assertEquals(Status.PASSIVELY_IMMUNIZED, body.getStatus());
        }

        @ParameterizedTest
        @MethodSource("colors")
        @DisplayName("からだパーツカードの状態が仮免疫のときに同じ色のウィルスカードを適用すると状態が健康になる")
        void success5(final Color color) {
            // Given:
            var medicine = MedicineCard.create(color);
            var virus = VirusCard.create(color);

            var body = BodyPartCard.create(color);
            body.apply(medicine);

            // When:
            body.apply(virus);

            // Then:
            assertEquals(Status.HEALTHY, body.getStatus());
        }

        @ParameterizedTest
        @MethodSource("colors")
        @DisplayName("からだパーツカードの状態が仮免疫のときに同じ色の薬カードを適用すると状態が免疫になる")
        void success6(final Color color) {
            // Given:
            var medicine1 = MedicineCard.create(color);
            var medicine2 = MedicineCard.create(color);

            var body = BodyPartCard.create(color);
            body.apply(medicine1);

            // When:
            body.apply(medicine2);

            // Given:
            assertEquals(Status.IMMUNIZED, body.getStatus());
        }

        @ParameterizedTest
        @MethodSource("colors")
        @DisplayName("MULTIカラーのウィルスカードは全ての色のからだパーツカードに適用できる")
        void success7(final Color color) {
            // Given:
            var virus = VirusCard.create(Color.MULTI);
            var body = BodyPartCard.create(color);

            // When:
            body.apply(virus);

            // Then:
            assertEquals(Status.INFECTED, body.getStatus());
        }

        @ParameterizedTest
        @MethodSource("colors")
        @DisplayName("MULTIカラーの薬カードは全ての色のからだパーツカードに適用できる")
        void success8(final Color color) {
            // Given:
            var medicine = MedicineCard.create(Color.MULTI);
            var body = BodyPartCard.create(color);

            // When:
            body.apply(medicine);

            // Then:
            assertEquals(Status.PASSIVELY_IMMUNIZED, body.getStatus());
        }

        static Stream<Arguments> failure1Pattern() {
            return Stream.of(
                Arguments.of(Color.BLUE, Color.BLUE),
                Arguments.of(Color.BLUE, Color.RED),
                Arguments.of(Color.BLUE, Color.GREEN),
                Arguments.of(Color.BLUE, Color.YELLOW),
                Arguments.of(Color.RED, Color.BLUE),
                Arguments.of(Color.RED, Color.RED),
                Arguments.of(Color.RED, Color.GREEN),
                Arguments.of(Color.RED, Color.YELLOW),
                Arguments.of(Color.GREEN, Color.BLUE),
                Arguments.of(Color.GREEN, Color.RED),
                Arguments.of(Color.GREEN, Color.GREEN),
                Arguments.of(Color.GREEN, Color.YELLOW),
                Arguments.of(Color.YELLOW, Color.BLUE),
                Arguments.of(Color.YELLOW, Color.RED),
                Arguments.of(Color.YELLOW, Color.GREEN),
                Arguments.of(Color.YELLOW, Color.YELLOW));
        }

        @ParameterizedTest
        @MethodSource("failure1Pattern")
        @DisplayName("からだパーツカードを適用すると例外がスローされる")
        void failure1(final Color color1, final Color color2) {
            // Given:
            var body1 = BodyPartCard.create(color1);
            var body2 = BodyPartCard.create(color2);

            // When/Then:
            assertThrows(IllegalArgumentException.class, () -> {
                body1.apply(body2);
            });
        }

        static Stream<Arguments> failure2Pattern() {
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
                Arguments.of(BodyPartCard.create(Color.YELLOW), VirusCard.create(Color.GREEN)));
        }

        @ParameterizedTest
        @MethodSource("failure2Pattern")
        @DisplayName("からだパーツカードと異なる色のウィルスカードを適用すると例外がスローされる")
        void failure2(final BodyPartCard body, final VirusCard virus) {
            // When/Then:
            assertThrows(IllegalArgumentException.class, () -> {
                body.apply(virus);
            });
        }

        static Stream<Arguments> failure3Pattern() {
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
                Arguments.of(BodyPartCard.create(Color.YELLOW), MedicineCard.create(Color.GREEN)));
        }

        @ParameterizedTest
        @MethodSource("failure3Pattern")
        @DisplayName("からだパーツカードの色と異なる薬カードを適用すると例外がスローされる")
        void failure3(final BodyPartCard body, final MedicineCard medicine) {
            // When/Then:
            assertThrows(IllegalArgumentException.class, () -> {
                body.apply(medicine);
            });
        }

        static Stream<Arguments> failure4Pattern() {
            return Stream.of(
                Arguments.of(VirusCard.create(Color.BLUE), VirusCard.create(Color.BLUE), VirusCard.create(Color.BLUE)),
                Arguments.of(VirusCard.create(Color.BLUE), VirusCard.create(Color.BLUE), MedicineCard.create(Color.BLUE)),
                Arguments.of(MedicineCard.create(Color.BLUE), MedicineCard.create(Color.BLUE), MedicineCard.create(Color.BLUE)),
                Arguments.of(MedicineCard.create(Color.BLUE), MedicineCard.create(Color.BLUE), VirusCard.create(Color.BLUE))
            );
        }

        @ParameterizedTest
        @MethodSource("failure4Pattern")
        @DisplayName("3枚目のカードを適用すると例外がスローされる")
        void failure4(final ICard applyCard1, final ICard applyCard2, final ICard applyCard3) {
            // Given:
            var body = BodyPartCard.create(Color.BLUE);
            body.apply(applyCard1);
            body.apply(applyCard2);

            // When/Then:
            assertThrows(IllegalStateException.class, () -> {
                body.apply(applyCard3);
            });
        }

    }

}
