package vsvirus.player;

import java.util.UUID;

/**
 * プレイヤー
 */
public final class Player {

    public final UUID id;
    public final String name;

    public final Hand hand;

    private Player(final UUID id, final String name, final Hand hand) {
        this.id = id;
        this.name = name;
        this.hand = hand;
    }

    public static Player create(final String name) {
        var hand = Hand.create();
        return new Player(UUID.randomUUID(), name, hand);
    }

}
