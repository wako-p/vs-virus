package vsvirus.card.body;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import vsvirus.card.Color;
import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.rules.ColorRule;
import vsvirus.card.virus.VirusCard;

class ApplicationPolicyTest {

    @Nested
    class CompleteWithAllTest {

        static Stream<Arguments> test1Parameter() {
            return Stream.of(
                Arguments.of(new ColorRule(Color.BLUE), VirusCard.create(Color.BLUE)),
                Arguments.of(new ColorRule(Color.BLUE), VirusCard.create(Color.MULTI)),

                Arguments.of(new ColorRule(Color.RED), VirusCard.create(Color.RED)),
                Arguments.of(new ColorRule(Color.RED), VirusCard.create(Color.MULTI)),

                Arguments.of(new ColorRule(Color.GREEN), VirusCard.create(Color.GREEN)),
                Arguments.of(new ColorRule(Color.GREEN), VirusCard.create(Color.MULTI)),

                Arguments.of(new ColorRule(Color.YELLOW), VirusCard.create(Color.YELLOW)),
                Arguments.of(new ColorRule(Color.YELLOW), VirusCard.create(Color.MULTI)));
        }

        @ParameterizedTest
        @MethodSource("test1Parameter")
        @DisplayName("カードカラールールと同じ色(または、マルチカラー)のウィルスカードが引数に指定された場合はtrueを返す")
        void test1(final ColorRule colorRule, final VirusCard virus) {
            // Given:
            var policy = new ApplicationPolicy(colorRule);

            // When:
            var actual = policy.complyWithAll(virus);

            // Then:
            assertEquals(true, actual);
        }

        static Stream<Arguments> test2Parameter() {
            return Stream.of(
                Arguments.of(new ColorRule(Color.BLUE), VirusCard.create(Color.RED)),
                Arguments.of(new ColorRule(Color.BLUE), VirusCard.create(Color.GREEN)),
                Arguments.of(new ColorRule(Color.BLUE), VirusCard.create(Color.YELLOW)),

                Arguments.of(new ColorRule(Color.RED), VirusCard.create(Color.GREEN)),
                Arguments.of(new ColorRule(Color.RED), VirusCard.create(Color.YELLOW)),
                Arguments.of(new ColorRule(Color.RED), VirusCard.create(Color.BLUE)),

                Arguments.of(new ColorRule(Color.GREEN), VirusCard.create(Color.YELLOW)),
                Arguments.of(new ColorRule(Color.GREEN), VirusCard.create(Color.BLUE)),
                Arguments.of(new ColorRule(Color.GREEN), VirusCard.create(Color.RED)),

                Arguments.of(new ColorRule(Color.YELLOW), VirusCard.create(Color.BLUE)),
                Arguments.of(new ColorRule(Color.YELLOW), VirusCard.create(Color.RED)),
                Arguments.of(new ColorRule(Color.YELLOW), VirusCard.create(Color.GREEN)));
        }

        @ParameterizedTest
        @MethodSource("test2Parameter")
        @DisplayName("カードカラールールと異なる色のウィルスカードが引数に指定された場合はfalseを返す")
        void test2(final ColorRule colorRule, final VirusCard virus) {
            // Given:
            var policy = new ApplicationPolicy(colorRule);

            // When:
            var actual = policy.complyWithAll(virus);

            // Then:
            assertEquals(false, actual);
        }

        static Stream<Arguments> test3Parameter() {
            return Stream.of(
                Arguments.of(new ColorRule(Color.BLUE), MedicineCard.create(Color.BLUE)),
                Arguments.of(new ColorRule(Color.BLUE), MedicineCard.create(Color.MULTI)),

                Arguments.of(new ColorRule(Color.RED), MedicineCard.create(Color.RED)),
                Arguments.of(new ColorRule(Color.RED), MedicineCard.create(Color.MULTI)),

                Arguments.of(new ColorRule(Color.GREEN), MedicineCard.create(Color.GREEN)),
                Arguments.of(new ColorRule(Color.GREEN), MedicineCard.create(Color.MULTI)),

                Arguments.of(new ColorRule(Color.YELLOW), MedicineCard.create(Color.YELLOW)),
                Arguments.of(new ColorRule(Color.YELLOW), MedicineCard.create(Color.MULTI)));
        }

        @ParameterizedTest
        @MethodSource("test3Parameter")
        @DisplayName("カードカラールールと同じ色の薬カードが引数に指定された場合はtrueを返す")
        void test3(final ColorRule colorRule, final MedicineCard medicine) {
            // Given:
            var policy = new ApplicationPolicy(colorRule);

            // When:
            var actual = policy.complyWithAll(medicine);

            // Then:
            assertEquals(true, actual);
        }

        static Stream<Arguments> test4Parameter() {
            return Stream.of(
                Arguments.of(new ColorRule(Color.BLUE), MedicineCard.create(Color.RED)),
                Arguments.of(new ColorRule(Color.BLUE), MedicineCard.create(Color.GREEN)),
                Arguments.of(new ColorRule(Color.BLUE), MedicineCard.create(Color.YELLOW)),

                Arguments.of(new ColorRule(Color.RED), MedicineCard.create(Color.GREEN)),
                Arguments.of(new ColorRule(Color.RED), MedicineCard.create(Color.YELLOW)),
                Arguments.of(new ColorRule(Color.RED), MedicineCard.create(Color.BLUE)),

                Arguments.of(new ColorRule(Color.GREEN), MedicineCard.create(Color.YELLOW)),
                Arguments.of(new ColorRule(Color.GREEN), MedicineCard.create(Color.BLUE)),
                Arguments.of(new ColorRule(Color.GREEN), MedicineCard.create(Color.RED)),

                Arguments.of(new ColorRule(Color.YELLOW), MedicineCard.create(Color.BLUE)),
                Arguments.of(new ColorRule(Color.YELLOW), MedicineCard.create(Color.RED)),
                Arguments.of(new ColorRule(Color.YELLOW), MedicineCard.create(Color.GREEN)));
        }

        @ParameterizedTest
        @MethodSource("test4Parameter")
        @DisplayName("カードカラールールと異なる色の薬カードが引数に指定された場合はtrueを返す")
        void test4(final ColorRule colorRule, final MedicineCard medicine) {
            // Given:
            var policy = new ApplicationPolicy(colorRule);

            // When:
            var actual = policy.complyWithAll(medicine);

            // Then:
            assertEquals(false, actual);
        }

        static Stream<Arguments> test5Parameter() {
            return Stream.of(
                Arguments.of(new ColorRule(Color.BLUE), BodyPartCard.create(Color.BLUE)),
                Arguments.of(new ColorRule(Color.BLUE), BodyPartCard.create(Color.RED)),
                Arguments.of(new ColorRule(Color.BLUE), BodyPartCard.create(Color.GREEN)),
                Arguments.of(new ColorRule(Color.BLUE), BodyPartCard.create(Color.YELLOW)),
                Arguments.of(new ColorRule(Color.BLUE), BodyPartCard.create(Color.MULTI)),
                
                Arguments.of(new ColorRule(Color.RED), BodyPartCard.create(Color.BLUE)),
                Arguments.of(new ColorRule(Color.RED), BodyPartCard.create(Color.RED)),
                Arguments.of(new ColorRule(Color.RED), BodyPartCard.create(Color.GREEN)),
                Arguments.of(new ColorRule(Color.RED), BodyPartCard.create(Color.YELLOW)),
                Arguments.of(new ColorRule(Color.RED), BodyPartCard.create(Color.MULTI)),

                Arguments.of(new ColorRule(Color.GREEN), BodyPartCard.create(Color.BLUE)),
                Arguments.of(new ColorRule(Color.GREEN), BodyPartCard.create(Color.RED)),
                Arguments.of(new ColorRule(Color.GREEN), BodyPartCard.create(Color.GREEN)),
                Arguments.of(new ColorRule(Color.GREEN), BodyPartCard.create(Color.YELLOW)),
                Arguments.of(new ColorRule(Color.GREEN), BodyPartCard.create(Color.MULTI)),

                Arguments.of(new ColorRule(Color.YELLOW), BodyPartCard.create(Color.BLUE)),
                Arguments.of(new ColorRule(Color.YELLOW), BodyPartCard.create(Color.RED)),
                Arguments.of(new ColorRule(Color.YELLOW), BodyPartCard.create(Color.GREEN)),
                Arguments.of(new ColorRule(Color.YELLOW), BodyPartCard.create(Color.YELLOW)),
                Arguments.of(new ColorRule(Color.YELLOW), BodyPartCard.create(Color.MULTI)));
        }

        @ParameterizedTest
        @MethodSource("test5Parameter")
        @DisplayName("からだパーツカードが引数に指定された場合はfalseを返す")
        void test5(final ColorRule colorRule, final BodyPartCard body) {
            // Given:
            var policy = new ApplicationPolicy(colorRule);

            // When:
            var actual = policy.complyWithAll(body);

            // Then:
            assertEquals(false, actual);
        }

    }
}
