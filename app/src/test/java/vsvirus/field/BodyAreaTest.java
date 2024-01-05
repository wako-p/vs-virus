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

}
