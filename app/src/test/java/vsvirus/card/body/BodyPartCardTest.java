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

        static Stream<Arguments> successColorPattern() {
            return Stream.of(
                Arguments.of(Color.BLUE, Color.BLUE),
                Arguments.of(Color.BLUE, Color.MULTI),
                Arguments.of(Color.RED, Color.RED),
                Arguments.of(Color.RED, Color.MULTI),
                Arguments.of(Color.GREEN, Color.GREEN),
                Arguments.of(Color.GREEN, Color.MULTI),
                Arguments.of(Color.YELLOW, Color.YELLOW),
                Arguments.of(Color.YELLOW, Color.MULTI)
                );
        }

        @ParameterizedTest
        @MethodSource("successColorPattern")
        @DisplayName("からだパーツカードの状態が健康なときに同じ色(またはマルチカラー)のウィルスカードを適用すると状態が感染になる")
        void success1(final Color color1, final Color color2) {
            // Given:
            var virus = VirusCard.create(color2);
            var body = BodyPartCard.create(color1);

            // When:
            body.apply(virus);

            // Then:
            assertEquals(Status.INFECTED, body.getStatus());
        }

        @ParameterizedTest
        @MethodSource("successColorPattern")
        @DisplayName("からだパーツカードの状態が感染のときに同じ色(またはマルチカラー)の薬カードを適用すると状態が健康になる")
        void success2(final Color color1, final Color color2) {
            // Given:
            var virus = VirusCard.create(color2);
            var medicine = MedicineCard.create(color2);

            var body = BodyPartCard.create(color1);
            body.apply(virus);

            // When:
            body.apply(medicine);

            // Given:
            assertEquals(Status.HEALTHY, body.getStatus());
        }

        @ParameterizedTest
        @MethodSource("successColorPattern")
        @DisplayName("からだパーツカードの状態が感染のときに同じ色(またはマルチカラー)のウィルスカードを適用すると状態が発症になる")
        void success3(final Color color1, final Color color2) {
            // Given:
            var virus1 = VirusCard.create(color2);
            var virus2 = VirusCard.create(color2);

            var body = BodyPartCard.create(color1);
            body.apply(virus1);

            // When:
            body.apply(virus2);

            // Then:
            assertEquals(Status.SYMPTOMATIC, body.getStatus());
        }

        @ParameterizedTest
        @MethodSource("successColorPattern")
        @DisplayName("からだパーツカードの状態が健康のときに同じ色(またはマルチカラー)の薬カードを適用すると状態が仮免疫になる")
        void success4(final Color color1, final Color color2) {
            // Given:
            var medicine = MedicineCard.create(color2);
            var body = BodyPartCard.create(color1);

            // When:
            body.apply(medicine);

            // Given:
            assertEquals(Status.PASSIVELY_IMMUNIZED, body.getStatus());
        }

        @ParameterizedTest
        @MethodSource("successColorPattern")
        @DisplayName("からだパーツカードの状態が仮免疫のときに同じ色(またはマルチカラー)のウィルスカードを適用すると状態が健康になる")
        void success5(final Color color1, final Color color2) {
            // Given:
            var medicine = MedicineCard.create(color2);
            var virus = VirusCard.create(color2);

            var body = BodyPartCard.create(color1);
            body.apply(medicine);

            // When:
            body.apply(virus);

            // Then:
            assertEquals(Status.HEALTHY, body.getStatus());
        }

        @ParameterizedTest
        @MethodSource("successColorPattern")
        @DisplayName("からだパーツカードの状態が仮免疫のときに同じ色(またはマルチカラー)の薬カードを適用すると状態が免疫になる")
        void success6(final Color color1, final Color color2) {
            // Given:
            var medicine1 = MedicineCard.create(color2);
            var medicine2 = MedicineCard.create(color2);

            var body = BodyPartCard.create(color1);
            body.apply(medicine1);

            // When:
            body.apply(medicine2);

            // Given:
            assertEquals(Status.IMMUNIZED, body.getStatus());
        }

        static Stream<Arguments> failureColorPattern() {
            return Stream.of(
                Arguments.of(Color.BLUE, Color.RED),
                Arguments.of(Color.BLUE, Color.GREEN),
                Arguments.of(Color.BLUE, Color.YELLOW),
                Arguments.of(Color.RED, Color.GREEN),
                Arguments.of(Color.RED, Color.YELLOW),
                Arguments.of(Color.RED, Color.BLUE),
                Arguments.of(Color.GREEN, Color.YELLOW),
                Arguments.of(Color.GREEN, Color.BLUE),
                Arguments.of(Color.GREEN, Color.RED),
                Arguments.of(Color.YELLOW, Color.BLUE),
                Arguments.of(Color.YELLOW, Color.RED),
                Arguments.of(Color.YELLOW, Color.GREEN));
        }

        @ParameterizedTest
        @MethodSource("failureColorPattern")
        @DisplayName("からだパーツカードと異なる色のウィルスカードを適用すると例外がスローされる")
        void failure1(final Color color1, final Color color2) {
            // Given:
            var body = BodyPartCard.create(color1);
            var medicine = MedicineCard.create(color2);

            // When/Then:
            assertThrows(IllegalArgumentException.class, () -> {
                body.apply(medicine);
            });
        }

        @ParameterizedTest
        @MethodSource("failureColorPattern")
        @DisplayName("からだパーツカードの色と異なる薬カードを適用すると例外がスローされる")
        void failure2(final Color color1, final Color color2) {
            // Given:
            var body = BodyPartCard.create(color1);
            var medicine = MedicineCard.create(color2);

            // When/Then:
            assertThrows(IllegalArgumentException.class, () -> {
                body.apply(medicine);
            });
        }

        @Test
        @DisplayName("3枚目のカードを適用すると例外がスローされる")
        void failure3() {
            // Given:
            var body = BodyPartCard.create(Color.BLUE);
            body.apply(VirusCard.create(Color.BLUE));
            body.apply(VirusCard.create(Color.BLUE));

            // When/Then:
            assertThrows(IllegalStateException.class, () -> {
                body.apply(VirusCard.create(Color.BLUE));
            });
            assertThrows(IllegalStateException.class, () -> {
                body.apply(MedicineCard.create(Color.BLUE));
            });
        }

    }

    @Nested
    class ExcludeTest {

        @Test
        @DisplayName("薬カードとウィルスカードが適用されている場合は除外できる")
        void success1() {
            // Given:
            var virus = VirusCard.create(Color.BLUE);
            var medicine = MedicineCard.create(Color.BLUE);
            var body = BodyPartCard.create(Color.BLUE);
            body.apply(virus);
            body.apply(medicine);

            // When:
            var actuals = body.exclude();

            // Then:
            assertEquals(Status.HEALTHY, body.getStatus());
            assertEquals(virus, actuals.get(0).get());
            assertEquals(medicine, actuals.get(1).get());
        }

        @Test
        @DisplayName("ウィルスカードと薬カードが適用されている場合は除外できる")
        void success2() {
            // Given:
            var medicine = MedicineCard.create(Color.BLUE);
            var virus = VirusCard.create(Color.BLUE);
            var body = BodyPartCard.create(Color.BLUE);
            body.apply(medicine);
            body.apply(virus);

            // When:
            var actuals = body.exclude();

            // Then:
            assertEquals(Status.HEALTHY, body.getStatus());
            assertEquals(medicine, actuals.get(0).get());
            assertEquals(virus, actuals.get(1).get());
        }

        @Test
        @DisplayName("ウィルスカードが2枚適用されている場合は除外できる")
        void success3() {
            // Given:
            var virus1 = VirusCard.create(Color.BLUE);
            var virus2 = VirusCard.create(Color.BLUE);
            var body = BodyPartCard.create(Color.BLUE);
            body.apply(virus1);
            body.apply(virus2);

            // When:
            var actuals = body.exclude();

            // Then:
            assertEquals(Status.SYMPTOMATIC, body.getStatus());
            assertEquals(virus1, actuals.get(0).get());
            assertEquals(virus2, actuals.get(1).get());
        }

        @Test
        @DisplayName("適用されたカードが0枚の場合は例外がスローされる")
        void failure1() {
            // Given:
            var body = BodyPartCard.create(Color.BLUE);

            // When/Then:
            assertThrows(IllegalStateException.class, () -> {
                body.exclude();
            });
        }

        @Test
        @DisplayName("適用されたカードが1枚の場合は例外がスローされる")
        void failure2() {
            // Given:
            var virus = VirusCard.create(Color.BLUE);
            var body = BodyPartCard.create(Color.BLUE);
            body.apply(virus);

            // When/Then:
            assertThrows(IllegalStateException.class, () -> {
                body.exclude();
            });
        }

        @Test
        @DisplayName("薬カードが2枚適用されている場合は例外がスローされる")
        void failure3() {
            // Given:
            var medicine1 = MedicineCard.create(Color.BLUE);
            var medicine2 = MedicineCard.create(Color.BLUE);
            var body = BodyPartCard.create(Color.BLUE);
            body.apply(medicine1);
            body.apply(medicine2);

            // When/Then:
            assertThrows(IllegalStateException.class, () -> {
                body.exclude();
            });
        }

    }

}
