package vsvirus.card.medicine;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import vsvirus.card.Color;
import vsvirus.card.ICard;

public final class MedicineCardFactory {

    private final Map<Color, Integer> rules = new LinkedHashMap<Color, Integer>() {{
        put(Color.BLUE, 4);
        put(Color.RED, 4);
        put(Color.GREEN, 4);
        put(Color.YELLOW, 4);
        put(Color.MULTI, 4);
    }};

    public List<ICard> create() {
        return this.rules.entrySet().stream()
            .flatMap(rule -> Stream.generate(
                () -> MedicineCard.create(rule.getKey())).limit(rule.getValue()))
            .collect(Collectors.toList());
    }

}
