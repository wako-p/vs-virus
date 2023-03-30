package virus.card;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class BodyPartCardFactory {

    private Map<Color, Integer> rules = new LinkedHashMap<Color, Integer>() {{
        put(Color.BLUE, 5);
        put(Color.RED, 5);
        put(Color.GREEN, 5);
        put(Color.YELLOW, 5);
        put(Color.MULTI, 1);
    }};

    public LinkedList<ICard> create() {

        var cards = new LinkedList<ICard>();
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