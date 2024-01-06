package vsvirus.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import vsvirus.card.Color;
import vsvirus.card.body.BodyPartCard;
import vsvirus.card.body.Status;
import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.virus.VirusCard;

class BodyAreaTest {

    @Nested
    class CreateTest {
        @Test
        @DisplayName("からだパーツカードが0枚の状態で生成される")
        void success() {
            // When:
            var bodyArea = BodyArea.create();

            // Then:
            assertEquals(0, bodyArea.count());
        }
    }

    @Nested
    class PlaceTest {

        @Test
        @DisplayName("からだパーツカードをインデックス位置(0-3)に置くことができる")
        void success1() {
            // Given:
            var body1 = BodyPartCard.create(Color.BLUE);
            var body2 = BodyPartCard.create(Color.RED);
            var body3 = BodyPartCard.create(Color.GREEN);
            var body4 = BodyPartCard.create(Color.YELLOW);
            var bodyArea = BodyArea.create();

            // When:
            bodyArea.place(0, body1);
            bodyArea.place(1, body2);
            bodyArea.place(2, body3);
            bodyArea.place(3, body4);

            assertEquals(body1, bodyArea.getEvilCards().get(0).get());
            assertEquals(body2, bodyArea.getEvilCards().get(1).get());
            assertEquals(body3, bodyArea.getEvilCards().get(2).get());
            assertEquals(body4, bodyArea.getEvilCards().get(3).get());
        }

        @ParameterizedTest
        @ValueSource(ints = {-2, -1, 4, 5})
        @DisplayName("インデックス位置が0-3の範囲外の場合は例外がスローされる")
        void failure1(final int index) {
            // Given:
            var body = BodyPartCard.create(Color.BLUE);
            var bodyArea = BodyArea.create();

            // When/Then:
            assertThrows(IllegalArgumentException.class, () -> {
                bodyArea.place(index, body);
            });
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3})
        @DisplayName("既にからだパーツカードが置かれているインデックス位置(0-3)には置けない")
        void failure2(final int index) {
            // Given:
            var body1 = BodyPartCard.create(Color.BLUE);
            var body2 = BodyPartCard.create(Color.RED);
            var bodyArea = BodyArea.create();

            // When:
            bodyArea.place(index, body1);

            // Then:
            assertThrows(IllegalStateException.class, () -> {
                bodyArea.place(index, body2);
            });
        }

        static Stream<Arguments> failure3Pattern() {
            return Stream.of(
                Arguments.of(BodyPartCard.create(Color.BLUE), BodyPartCard.create(Color.BLUE)),
                Arguments.of(BodyPartCard.create(Color.RED), BodyPartCard.create(Color.RED)),
                Arguments.of(BodyPartCard.create(Color.GREEN), BodyPartCard.create(Color.GREEN)),
                Arguments.of(BodyPartCard.create(Color.YELLOW), BodyPartCard.create(Color.YELLOW))
            );
        }

        @ParameterizedTest
        @MethodSource("failure3Pattern")
        @DisplayName("からだエリアに同じ色のからだパーツカードが既に置かれている場合は例外がスローされる1")
        void failure3(BodyPartCard card1, BodyPartCard card2) {
            // Given:
            var bodyArea = BodyArea.create();
            bodyArea.place(0, card1);

            // When/Then:
            assertThrows(IllegalStateException.class, () -> {
                bodyArea.place(1, card2);
            });
        }

        static Stream<Arguments> failure4Pattern() {
            return Stream.of(
                Arguments.of(BodyPartCard.create(Color.BLUE), BodyPartCard.create(Color.RED), BodyPartCard.create(Color.BLUE)),
                Arguments.of(BodyPartCard.create(Color.BLUE), BodyPartCard.create(Color.RED), BodyPartCard.create(Color.RED)),
                Arguments.of(BodyPartCard.create(Color.RED), BodyPartCard.create(Color.GREEN), BodyPartCard.create(Color.RED)),
                Arguments.of(BodyPartCard.create(Color.RED), BodyPartCard.create(Color.GREEN), BodyPartCard.create(Color.GREEN)),
                Arguments.of(BodyPartCard.create(Color.GREEN), BodyPartCard.create(Color.YELLOW), BodyPartCard.create(Color.GREEN)),
                Arguments.of(BodyPartCard.create(Color.GREEN), BodyPartCard.create(Color.YELLOW), BodyPartCard.create(Color.YELLOW)),
                Arguments.of(BodyPartCard.create(Color.YELLOW), BodyPartCard.create(Color.BLUE), BodyPartCard.create(Color.YELLOW)),
                Arguments.of(BodyPartCard.create(Color.YELLOW), BodyPartCard.create(Color.BLUE), BodyPartCard.create(Color.BLUE))
            );
        }

        @ParameterizedTest
        @MethodSource("failure4Pattern")
        @DisplayName("からだエリアに同じ色のからだパーツカードが既に置かれている場合は例外がスローされる2")
        void failure4(BodyPartCard card1, BodyPartCard card2, BodyPartCard card3) {
            // Given:
            var bodyArea = BodyArea.create();
            bodyArea.place(0, card1);
            bodyArea.place(1, card2);

            // When/Then:
            assertThrows(IllegalStateException.class, () -> {
                bodyArea.place(2, card3);
            });
        }

        static Stream<Arguments> failure5Pattern() {
            return Stream.of(
                Arguments.of(BodyPartCard.create(Color.BLUE), BodyPartCard.create(Color.RED), BodyPartCard.create(Color.GREEN), BodyPartCard.create(Color.BLUE)),
                Arguments.of(BodyPartCard.create(Color.BLUE), BodyPartCard.create(Color.RED), BodyPartCard.create(Color.GREEN), BodyPartCard.create(Color.RED)),
                Arguments.of(BodyPartCard.create(Color.BLUE), BodyPartCard.create(Color.RED), BodyPartCard.create(Color.GREEN), BodyPartCard.create(Color.GREEN)),
                Arguments.of(BodyPartCard.create(Color.RED), BodyPartCard.create(Color.GREEN), BodyPartCard.create(Color.YELLOW), BodyPartCard.create(Color.RED)),
                Arguments.of(BodyPartCard.create(Color.RED), BodyPartCard.create(Color.GREEN), BodyPartCard.create(Color.YELLOW), BodyPartCard.create(Color.GREEN)),
                Arguments.of(BodyPartCard.create(Color.RED), BodyPartCard.create(Color.GREEN), BodyPartCard.create(Color.YELLOW), BodyPartCard.create(Color.YELLOW)),
                Arguments.of(BodyPartCard.create(Color.GREEN), BodyPartCard.create(Color.YELLOW), BodyPartCard.create(Color.BLUE), BodyPartCard.create(Color.GREEN)),
                Arguments.of(BodyPartCard.create(Color.GREEN), BodyPartCard.create(Color.YELLOW), BodyPartCard.create(Color.BLUE), BodyPartCard.create(Color.YELLOW)),
                Arguments.of(BodyPartCard.create(Color.GREEN), BodyPartCard.create(Color.YELLOW), BodyPartCard.create(Color.BLUE), BodyPartCard.create(Color.BLUE)),
                Arguments.of(BodyPartCard.create(Color.YELLOW), BodyPartCard.create(Color.BLUE), BodyPartCard.create(Color.RED), BodyPartCard.create(Color.YELLOW)),
                Arguments.of(BodyPartCard.create(Color.YELLOW), BodyPartCard.create(Color.BLUE), BodyPartCard.create(Color.RED), BodyPartCard.create(Color.BLUE)),
                Arguments.of(BodyPartCard.create(Color.YELLOW), BodyPartCard.create(Color.BLUE), BodyPartCard.create(Color.RED), BodyPartCard.create(Color.RED))
            );
        }

        @ParameterizedTest
        @MethodSource("failure5Pattern")
        @DisplayName("からだエリアに同じ色のからだパーツカードが既に置かれている場合は例外がスローされる3")
        void failure5(BodyPartCard card1, BodyPartCard card2, BodyPartCard card3, BodyPartCard card4) {
            // Given:
            var bodyArea = BodyArea.create();
            bodyArea.place(0, card1);
            bodyArea.place(1, card2);
            bodyArea.place(2, card3);

            // When/Then:
            assertThrows(IllegalStateException.class, () -> {
                bodyArea.place(3, card4);
            });
        }

    }

    @Nested
    class GetTest {

        @Test
        @DisplayName("からだエリアに置かれたからだパーツカードを取得することができる")
        void success1() {
            // Given:
            var body1 = BodyPartCard.create(Color.BLUE);
            var body2 = BodyPartCard.create(Color.RED);
            var body3 = BodyPartCard.create(Color.GREEN);
            var body4 = BodyPartCard.create(Color.YELLOW);
            var bodyArea = BodyArea.create();
            bodyArea.place(0, body1);
            bodyArea.place(1, body2);
            bodyArea.place(2, body3);
            bodyArea.place(3, body4);

            // When:
            var actual1 = bodyArea.get(0);
            var actual2 = bodyArea.get(1);
            var actual3 = bodyArea.get(2);
            var actual4 = bodyArea.get(3);

            // Then:
            assertEquals(body1, actual1.get());
            assertEquals(body2, actual2.get());
            assertEquals(body3, actual3.get());
            assertEquals(body4, actual4.get());
        }

        @Test
        @DisplayName("からだパーツカードが置かれていない位置からは取得できない")
        void success2() {
            // Given:
            var body1 = BodyPartCard.create(Color.BLUE);
            var body2 = BodyPartCard.create(Color.RED);
            var bodyArea = BodyArea.create();
            bodyArea.place(0, body1);
            bodyArea.place(1, body2);

            // When:
            var actual3 = bodyArea.get(2);
            var actual4 = bodyArea.get(3);

            // Then:
            assertEquals(true, actual3.isEmpty());
            assertEquals(true, actual4.isEmpty());
        }

    }

    @Nested
    class ApplyToTest {

        @Test
        void success1() {
            // Given:
            var bodyB = BodyPartCard.create(Color.BLUE);
            var bodyArea = BodyArea.create();
            bodyArea.place(0, bodyB);

            // When:
            var actuals = bodyArea.applyTo(0, MedicineCard.create(Color.BLUE));

            // Then:
            assertEquals(0, actuals.size());
            assertEquals(Status.PASSIVELY_IMMUNIZED, bodyArea.get(0).get().getStatus());
        }

        @Test
        void success2() {
            // Given:
            var bodyB = BodyPartCard.create(Color.BLUE);
            var bodyArea = BodyArea.create();
            bodyArea.place(0, bodyB);

            var medicine = MedicineCard.create(Color.BLUE);
            var virus = VirusCard.create(Color.BLUE);

            // When:
            var actuals1 = bodyArea.applyTo(0, medicine);
            var actuals2 = bodyArea.applyTo(0, virus);

            // Then:
            assertEquals(0, actuals1.size());
            assertEquals(2, actuals2.size());
            assertEquals(medicine, actuals2.get(0).get());
            assertEquals(virus, actuals2.get(1).get());
            assertEquals(Status.HEALTHY, bodyArea.get(0).get().getStatus());
        }

        @Test
        void success3() {
            // Given:
            var bodyB = BodyPartCard.create(Color.BLUE);
            var bodyArea = BodyArea.create();
            bodyArea.place(0, bodyB);

            var medicine1 = MedicineCard.create(Color.BLUE);
            var medicine2 = MedicineCard.create(Color.BLUE);

            // When:
            var actuals1 = bodyArea.applyTo(0, medicine1);
            var actuals2 = bodyArea.applyTo(0, medicine2);

            // Then:
            assertEquals(0, actuals1.size());
            assertEquals(0, actuals2.size());
            assertEquals(Status.IMMUNIZED, bodyArea.get(0).get().getStatus());
        }

        @Test
        void success4() {
            // Given:
            var bodyB = BodyPartCard.create(Color.BLUE);
            var bodyArea = BodyArea.create();
            bodyArea.place(0, bodyB);

            // When:
            var actuals = bodyArea.applyTo(0, VirusCard.create(Color.BLUE));

            // Then:
            assertEquals(0, actuals.size());
            assertEquals(Status.INFECTED, bodyArea.get(0).get().getStatus());
        }

        @Test
        void success5() {
            // Given:
            var bodyB = BodyPartCard.create(Color.BLUE);
            var bodyArea = BodyArea.create();
            bodyArea.place(0, bodyB);

            var virus = VirusCard.create(Color.BLUE);
            var medicine = MedicineCard.create(Color.BLUE);

            // When:
            var actuals1 = bodyArea.applyTo(0, virus);
            var actuals2 = bodyArea.applyTo(0, medicine);

            // Then:
            assertEquals(0, actuals1.size());
            assertEquals(2, actuals2.size());
            assertEquals(virus, actuals2.get(0).get());
            assertEquals(medicine, actuals2.get(1).get());
            assertEquals(Status.HEALTHY, bodyArea.get(0).get().getStatus());
        }

        @Test
        void success6() {
            // Given:
            var bodyB = BodyPartCard.create(Color.BLUE);
            var bodyArea = BodyArea.create();
            bodyArea.place(0, bodyB);

            var virus1 = VirusCard.create(Color.BLUE);
            var virus2 = VirusCard.create(Color.BLUE);

            // When:
            var actuals1 = bodyArea.applyTo(0, virus1);
            var actuals2 = bodyArea.applyTo(0, virus2);

            // Then:
            assertEquals(0, actuals1.size());
            assertEquals(2, actuals2.size());
            assertEquals(virus1, actuals2.get(0).get());
            assertEquals(virus2, actuals2.get(1).get());
            assertEquals(Status.SYMPTOMATIC, bodyArea.get(0).get().getStatus());
        }

    }

    @Nested
    class ExcludeSymptomaticTest {

        @Test
        @DisplayName("発症したからだパーツカードを除外できる1")
        void success1() {
            // Given:
            var bodyB = BodyPartCard.create(Color.BLUE);
            var bodyR = BodyPartCard.create(Color.RED);
            var bodyG = BodyPartCard.create(Color.GREEN);
            var bodyY = BodyPartCard.create(Color.YELLOW);

            var bodyArea = BodyArea.create();
            bodyArea.place(0, bodyB);
            bodyArea.place(1, bodyR);
            bodyArea.place(2, bodyG);
            bodyArea.place(3, bodyY);

            bodyArea.get(0).ifPresent(card -> card.apply(VirusCard.create(Color.BLUE)));
            bodyArea.get(0).ifPresent(card -> card.apply(VirusCard.create(Color.BLUE)));

            // When:
            var actuals = bodyArea.excludeSymptomatic();

            // Then:
            assertEquals(3, bodyArea.count());
            assertEquals(bodyB, actuals.get(0).get());
        }

        @Test
        @DisplayName("発症したからだパーツカードを除外できる2")
        void success2() {
            // Given:
            var bodyB = BodyPartCard.create(Color.BLUE);
            var bodyR = BodyPartCard.create(Color.RED);
            var bodyG = BodyPartCard.create(Color.GREEN);
            var bodyY = BodyPartCard.create(Color.YELLOW);

            var bodyArea = BodyArea.create();
            bodyArea.place(0, bodyB);
            bodyArea.place(1, bodyR);
            bodyArea.place(2, bodyG);
            bodyArea.place(3, bodyY);

            bodyArea.get(0).ifPresent(card -> card.apply(VirusCard.create(Color.BLUE)));
            bodyArea.get(0).ifPresent(card -> card.apply(VirusCard.create(Color.BLUE)));
            bodyArea.get(1).ifPresent(card -> card.apply(VirusCard.create(Color.RED)));
            bodyArea.get(1).ifPresent(card -> card.apply(VirusCard.create(Color.RED)));

            // When:
            var actuals = bodyArea.excludeSymptomatic();

            // Then:
            assertEquals(2, bodyArea.count());
            assertEquals(bodyB, actuals.get(0).get());
            assertEquals(bodyR, actuals.get(1).get());
        }

        @Test
        @DisplayName("発症したからだパーツカードを除外できる3")
        void success3() {
            // Given:
            var bodyB = BodyPartCard.create(Color.BLUE);
            var bodyR = BodyPartCard.create(Color.RED);
            var bodyG = BodyPartCard.create(Color.GREEN);
            var bodyY = BodyPartCard.create(Color.YELLOW);

            var bodyArea = BodyArea.create();
            bodyArea.place(0, bodyB);
            bodyArea.place(1, bodyR);
            bodyArea.place(2, bodyG);
            bodyArea.place(3, bodyY);

            bodyArea.get(0).ifPresent(card -> card.apply(VirusCard.create(Color.BLUE)));
            bodyArea.get(0).ifPresent(card -> card.apply(VirusCard.create(Color.BLUE)));
            bodyArea.get(1).ifPresent(card -> card.apply(VirusCard.create(Color.RED)));
            bodyArea.get(1).ifPresent(card -> card.apply(VirusCard.create(Color.RED)));
            bodyArea.get(2).ifPresent(card -> card.apply(VirusCard.create(Color.GREEN)));
            bodyArea.get(2).ifPresent(card -> card.apply(VirusCard.create(Color.GREEN)));

            // When:
            var actuals = bodyArea.excludeSymptomatic();

            // Then:
            assertEquals(1, bodyArea.count());
            assertEquals(bodyB, actuals.get(0).get());
            assertEquals(bodyR, actuals.get(1).get());
            assertEquals(bodyG, actuals.get(2).get());
        }

        @Test
        @DisplayName("発症したからだパーツカードを除外できる4")
        void success4() {
            // Given:
            var bodyB = BodyPartCard.create(Color.BLUE);
            var bodyR = BodyPartCard.create(Color.RED);
            var bodyG = BodyPartCard.create(Color.GREEN);
            var bodyY = BodyPartCard.create(Color.YELLOW);

            var bodyArea = BodyArea.create();
            bodyArea.place(0, bodyB);
            bodyArea.place(1, bodyR);
            bodyArea.place(2, bodyG);
            bodyArea.place(3, bodyY);

            bodyArea.get(0).ifPresent(card -> card.apply(VirusCard.create(Color.BLUE)));
            bodyArea.get(0).ifPresent(card -> card.apply(VirusCard.create(Color.BLUE)));
            bodyArea.get(1).ifPresent(card -> card.apply(VirusCard.create(Color.RED)));
            bodyArea.get(1).ifPresent(card -> card.apply(VirusCard.create(Color.RED)));
            bodyArea.get(2).ifPresent(card -> card.apply(VirusCard.create(Color.GREEN)));
            bodyArea.get(2).ifPresent(card -> card.apply(VirusCard.create(Color.GREEN)));
            bodyArea.get(3).ifPresent(card -> card.apply(VirusCard.create(Color.YELLOW)));
            bodyArea.get(3).ifPresent(card -> card.apply(VirusCard.create(Color.YELLOW)));

            // When:
            var actuals = bodyArea.excludeSymptomatic();

            // Then:
            assertEquals(0, bodyArea.count());
            assertEquals(bodyB, actuals.get(0).get());
            assertEquals(bodyR, actuals.get(1).get());
            assertEquals(bodyG, actuals.get(2).get());
            assertEquals(bodyY, actuals.get(3).get());
        }

    }

    @Nested
    class ToStringTest {

        @Test
        void success1() {
            // Given:
            var bodyArea = BodyArea.create();

            // When:
            var actual = bodyArea.toString();

            // Then:
            assertEquals("", actual);
        }

        @Test
        void success2() {
            // Given:
            var bodyArea = BodyArea.create();
            bodyArea.place(0, BodyPartCard.create(Color.BLUE));

            // When:
            var actual = bodyArea.toString();

            // Then:
            assertEquals("[BB]", actual);
        }

        @Test
        void success3() {
            // Given:
            var bodyArea = BodyArea.create();
            bodyArea.place(0, BodyPartCard.create(Color.BLUE));
            bodyArea.place(1, BodyPartCard.create(Color.RED));

            // When:
            var actual = bodyArea.toString();

            // Then:
            assertEquals("""
                [BB]
                [BR]""", actual);
        }

        @Test
        void success4() {
            // Given:
            var bodyArea = BodyArea.create();
            bodyArea.place(0, BodyPartCard.create(Color.BLUE));
            bodyArea.place(1, BodyPartCard.create(Color.RED));
            bodyArea.place(2, BodyPartCard.create(Color.GREEN));

            // When:
            var actual = bodyArea.toString();

            // Then:
            assertEquals("""
                [BB]
                [BR]
                [BG]""", actual);
        }

        @Test
        void success5() {
            // Given:
            var bodyArea = BodyArea.create();
            bodyArea.place(0, BodyPartCard.create(Color.BLUE));
            bodyArea.place(1, BodyPartCard.create(Color.RED));
            bodyArea.place(2, BodyPartCard.create(Color.GREEN));
            bodyArea.place(3, BodyPartCard.create(Color.YELLOW));

            // When:
            var actual = bodyArea.toString();

            // Then:
            assertEquals("""
                [BB]
                [BR]
                [BG]
                [BY]""", actual);
        }

        @Test
        void success6() {
            // Given:
            var bodyArea = BodyArea.create();
            bodyArea.place(0, BodyPartCard.create(Color.BLUE));
            bodyArea.place(1, BodyPartCard.create(Color.RED));
            bodyArea.place(2, BodyPartCard.create(Color.GREEN));
            bodyArea.place(3, BodyPartCard.create(Color.YELLOW));

            // When:
            bodyArea.applyTo(0, VirusCard.create(Color.BLUE));
            var actual = bodyArea.toString();

            // Then:
            assertEquals("""
                [BB][VB]
                [BR]
                [BG]
                [BY]""", actual);
        }

        @Test
        void success7() {
            // Given:
            var bodyArea = BodyArea.create();
            bodyArea.place(0, BodyPartCard.create(Color.BLUE));
            bodyArea.place(1, BodyPartCard.create(Color.RED));
            bodyArea.place(2, BodyPartCard.create(Color.GREEN));
            bodyArea.place(3, BodyPartCard.create(Color.YELLOW));

            // When:
            bodyArea.applyTo(0, VirusCard.create(Color.BLUE));
            bodyArea.applyTo(1, VirusCard.create(Color.RED));
            var actual = bodyArea.toString();

            // Then:
            assertEquals("""
                [BB][VB]
                [BR][VR]
                [BG]
                [BY]""", actual);
        }

        @Test
        void success8() {
            // Given:
            var bodyArea = BodyArea.create();
            bodyArea.place(0, BodyPartCard.create(Color.BLUE));
            bodyArea.place(1, BodyPartCard.create(Color.RED));
            bodyArea.place(2, BodyPartCard.create(Color.GREEN));
            bodyArea.place(3, BodyPartCard.create(Color.YELLOW));

            // When:
            bodyArea.applyTo(0, MedicineCard.create(Color.BLUE));
            bodyArea.applyTo(0, MedicineCard.create(Color.BLUE));
            var actual = bodyArea.toString();

            // Then:
            assertEquals("""
                [BB][MB][MB]
                [BR]
                [BG]
                [BY]""", actual);
        }

    }

}
