package vsvirus.card;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ColorTest {

    @Nested
    class EqualsTest {

        static Stream<Arguments> colors1() {
            return Stream.of(
                Arguments.of(Color.BLUE, Color.BLUE),
                Arguments.of(Color.RED, Color.RED),
                Arguments.of(Color.GREEN, Color.GREEN),
                Arguments.of(Color.YELLOW, Color.YELLOW),
                Arguments.of(Color.MULTI, Color.MULTI)
            );
        }

        @ParameterizedTest
        @MethodSource("colors1")
        @DisplayName("同じ色の比較の場合はtrueを返す")
        void success1(Color color1, Color color2) {
            // When:
            var actual = color1.equals(color2);

            // Then:
            assertTrue(actual);
        }

        static Stream<Arguments> colors2() {
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
                Arguments.of(Color.YELLOW, Color.GREEN)
            );
        }

        @ParameterizedTest
        @MethodSource("colors2")
        @DisplayName("違う色の比較の場合はfalseを返す")
        void success2(Color color1, Color color2) {
            // When:
            var actual = color1.equals(color2);

            // Then:
            assertFalse(actual);
        }

        static Stream<Arguments> colors3() {
            return Stream.of(
                Arguments.of(Color.BLUE, Color.MULTI),
                Arguments.of(Color.RED, Color.MULTI),
                Arguments.of(Color.GREEN, Color.MULTI),
                Arguments.of(Color.YELLOW, Color.MULTI),
                Arguments.of(Color.MULTI, Color.BLUE),
                Arguments.of(Color.MULTI, Color.RED),
                Arguments.of(Color.MULTI, Color.GREEN),
                Arguments.of(Color.MULTI, Color.YELLOW)
            );
        }

        @ParameterizedTest
        @MethodSource("colors3")
        @DisplayName("マルチカラーは何色と比較してもtrueを返す")
        void success3(Color color1, Color color2) {
            // When:
            var actual = color1.equals(color2);

            // Then:
            assertTrue(actual);
        }

    }
}
