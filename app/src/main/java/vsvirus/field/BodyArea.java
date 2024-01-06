package vsvirus.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import vsvirus.card.body.BodyPartCard;

/**
 * からだエリア
 */
public final class BodyArea {

    private static final int MIN = 0;
    private static final int MAX = 3;

    private final List<Optional<BodyPartCard>> bodies;

    private BodyArea(final List<Optional<BodyPartCard>> bodies) {
        this.bodies = bodies;
    }

    public static BodyArea create() {
        var bodies = new ArrayList<Optional<BodyPartCard>>(Collections.nCopies(4, Optional.empty()));
        return new BodyArea(bodies);
    }

    public long count() {
        return this.bodies
                .stream()
                .filter(Optional::isPresent)
                .count();
    }

    public List<Optional<BodyPartCard>> getEvilCards() {
        // 追加、変更、削除ができないようにして返す
        return Collections.unmodifiableList(this.bodies);
    }

    public void place(final int index, final BodyPartCard body) {

        if (!isValidIndex(index)) {
            throw new IllegalArgumentException();
        }

        if (isValidExist(index)) {
            throw new IllegalStateException();
        }

        // 同じ色のからだパーツカードを複数置くことはできない
        if (isValidDuplicate(body)) {
            throw new IllegalStateException();
        }

        this.bodies.add(index, Optional.of(body));
    }

    public Optional<BodyPartCard> get(final int index) {
        if (!isValidIndex(index)) {
            throw new IllegalArgumentException();
        }
        return this.bodies.get(index);
    }

    private boolean isValidIndex(final int index) {
        return MIN <= index && index <= MAX;
    }

    private boolean isValidExist(final int index) {
        return this.bodies.get(index).isPresent();
    } 

    private boolean isValidDuplicate(final BodyPartCard newBody) {
        // 同じ色のからだパーツカードでフィルタしてその数をカウントする
        var duplicateCount = this.bodies
                .stream()
                .filter(maybeCard -> maybeCard
                    .map(body -> body.getColor() == newBody.getColor())
                    .orElse(false))
                .count();

        // 重複してたら0にならない
        return duplicateCount != 0;
    }

    public List<Optional<BodyPartCard>> excludeSymptomatic() {

        var excludeBodies = new ArrayList<Optional<BodyPartCard>>();

        for (var index = MIN; index <= MAX; index++) {
            var body = this.bodies.get(index);
            if (body.map(BodyPartCard::symptomaticed).orElse(false)) {
                excludeBodies.add(body);
                this.bodies.set(index, Optional.empty());
            }
        }

        return excludeBodies;
    }

}
