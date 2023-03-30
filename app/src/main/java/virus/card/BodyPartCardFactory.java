package virus.card;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class BodyPartCardFactory {

    private final Map<Color, Integer> rules = new LinkedHashMap<Color, Integer>() {{
        put(Color.BLUE, 5);
        put(Color.RED, 5);
        put(Color.GREEN, 5);
        put(Color.YELLOW, 5);
        put(Color.MULTI, 1);
    }};

    public List<ICard> create() {

        var cards = new ArrayList<ICard>();
        for (Map.Entry<Color, Integer> rule : rules.entrySet()) {
            var max = rule.getValue();
            for (var count = 1; count <= max; count++) {
                var color = rule.getKey();
                var card = BodyPartCard.create(color);
                cards.add(card);
            }
        }

        return cards;
    }

}
