package vsvirus.card.virus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import vsvirus.card.Color;
import vsvirus.card.ICard;

public final class VirusCardFactory {

    private final Map<Color, Integer> rules = new LinkedHashMap<Color, Integer>() {{
        put(Color.BLUE, 4);
        put(Color.RED, 4);
        put(Color.GREEN, 4);
        put(Color.YELLOW, 4);
        put(Color.MULTI, 1);
    }};

    public List<ICard> create() {

        var cards = new ArrayList<ICard>();
        for (Map.Entry<Color, Integer> rule : rules.entrySet()) {
            var max = rule.getValue();
            for (var count = 1; count <= max; count++) {
                var color = rule.getKey();
                var card = VirusCard.create(color);
                cards.add(card);
            }
        }

        return cards;
    }

}
