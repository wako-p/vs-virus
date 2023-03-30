package virus.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BodyPartCardTest {

    @Nested
    class CreateTest {
        @Test
        void 引数に指定した色の健康なからだパーツカードを生成できる() {
            // When:
            var card = BodyPartCard.create(Color.BLUE);

            // Then:
            assertEquals(Color.BLUE, card.color);
            assertEquals(Status.HEALTHY, card.status);
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
}
