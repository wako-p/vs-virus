package virus.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import virus.card.Color;
import virus.card.body.BodyPartCard;

class BodyAreaTest {

    @Nested
    class CreateTest {
        @Test
        void からだパーツカードが0枚の状態で生成される() {
            // When:
            var bodyArea = BodyArea.create();

            // Then:
            assertEquals(0, bodyArea.count());
        }
    }

    @Nested
    class PlaceTest {
        @Test
        void からだエリアにからだパーツカードを4枚まで置くことができる() {
            // Given:
            var card1 = BodyPartCard.create(Color.BLUE);
            var card2 = BodyPartCard.create(Color.RED);
            var card3 = BodyPartCard.create(Color.GREEN);
            var card4 = BodyPartCard.create(Color.YELLOW);
            var bodyArea = BodyArea.create();

            // When:
            bodyArea.place(card1);
            bodyArea.place(card2);
            bodyArea.place(card3);
            bodyArea.place(card4);

            // Then:
            assertEquals(card1.color(), bodyArea.evilCards().get(0).color());
            assertEquals(card2.color(), bodyArea.evilCards().get(1).color());
            assertEquals(card3.color(), bodyArea.evilCards().get(2).color());
            assertEquals(card4.color(), bodyArea.evilCards().get(3).color());
        }

        @Test
        void からだエリアが4枚のときにからだパーツカードを置くと例外がスローされる() {
            // Given:
            var card1 = BodyPartCard.create(Color.BLUE);
            var card2 = BodyPartCard.create(Color.RED);
            var card3 = BodyPartCard.create(Color.GREEN);
            var card4 = BodyPartCard.create(Color.YELLOW);
            var card5 = BodyPartCard.create(Color.MULTI);

            var bodyArea = BodyArea.create();
            bodyArea.place(card1);
            bodyArea.place(card2);
            bodyArea.place(card3);
            bodyArea.place(card4);

            // When/Then:
            assertThrows(RuntimeException.class, () -> {
                bodyArea.place(card5);
            });
        }

        static Stream<Arguments> duplicateCardCombinations1() {
            return Stream.of(
                    Arguments.of(BodyPartCard.create(Color.BLUE), BodyPartCard.create(Color.BLUE)),
                    Arguments.of(BodyPartCard.create(Color.RED), BodyPartCard.create(Color.RED)),
                    Arguments.of(BodyPartCard.create(Color.GREEN), BodyPartCard.create(Color.GREEN)),
                    Arguments.of(BodyPartCard.create(Color.YELLOW), BodyPartCard.create(Color.YELLOW))
            );
        }

        static Stream<Arguments> duplicateCardCombinations2() {
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

        static Stream<Arguments> duplicateCardCombinations3() {
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
        @MethodSource("duplicateCardCombinations1")
        void からだエリアに同じ色のからだパーツカードが既に置かれている場合は例外がスローされる1(BodyPartCard card1, BodyPartCard card2) {
            // Given:
            var bodyArea = BodyArea.create();
            bodyArea.place(card1);

            // When/Then:
            assertThrows(RuntimeException.class, () -> {
                bodyArea.place(card2);
            });
        }

        @ParameterizedTest
        @MethodSource("duplicateCardCombinations2")
        void からだエリアに同じ色のからだパーツカードが既に置かれている場合は例外がスローされる2(BodyPartCard card1, BodyPartCard card2, BodyPartCard card3) {
            // Given:
            var bodyArea = BodyArea.create();
            bodyArea.place(card1);
            bodyArea.place(card2);

            // When/Then:
            assertThrows(RuntimeException.class, () -> {
                bodyArea.place(card3);
            });
        }

        @ParameterizedTest
        @MethodSource("duplicateCardCombinations3")
        void からだエリアに同じ色のからだパーツカードが既に置かれている場合は例外がスローされる3(BodyPartCard card1, BodyPartCard card2, BodyPartCard card3, BodyPartCard card4) {
            // Given:
            var bodyArea = BodyArea.create();
            bodyArea.place(card1);
            bodyArea.place(card2);
            bodyArea.place(card3);

            // When/Then:
            assertThrows(RuntimeException.class, () -> {
                bodyArea.place(card4);
            });
        }

    }
}
