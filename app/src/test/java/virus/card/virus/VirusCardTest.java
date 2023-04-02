package virus.card.virus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import virus.card.Color;

class VirusCardTest {

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
        void 引数に指定した色のウィルスカードを生成できる(Color color, Color expected) {
            // When:
            var card = VirusCard.create(color);

            // Then:
            assertEquals(expected, card.color());
        }

    }

}
