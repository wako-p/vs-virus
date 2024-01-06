package vsvirus.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import vsvirus.card.ICard;
import vsvirus.card.body.BodyPartCard;
import vsvirus.card.medicine.MedicineCard;
import vsvirus.card.virus.VirusCard;

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

        if (outRange(index)) {
            throw new IllegalArgumentException();
        }

        if (exists(index)) {
            throw new IllegalStateException();
        }

        // 同じ色のからだパーツカードを複数置くことはできない
        if (isDuplicate(body)) {
            throw new IllegalStateException();
        }

        this.bodies.add(index, Optional.of(body));
    }

    Optional<BodyPartCard> get(final int index) {
        if (outRange(index)) {
            throw new IllegalArgumentException();
        }
        return this.bodies.get(index);
    }

    public List<Optional<ICard>> applyTo(final int index, final MedicineCard medicine) {

        if (outRange(index)) {
            throw new IllegalArgumentException();
        }

        if (notExists(index)) {
            throw new IllegalStateException();
        }

        var body = this.bodies.get(index).get();
        body.apply(medicine);

        if (body.canNotExclude()) {
            return new ArrayList<>();
        }

        var exclusions = body.exclude();
        return exclusions;
    }

    public List<Optional<ICard>> applyTo(final int index, final VirusCard virus) {

        if (outRange(index)) {
            throw new IllegalArgumentException();
        }

        if (notExists(index)) {
            throw new IllegalStateException();
        }

        var body = this.bodies.get(index).get();
        body.apply(virus);

        if (body.canNotExclude()) {
            return new ArrayList<>();
        }

        var exclusions = body.exclude();
        return exclusions;
    }


    private boolean inRange(final int index) {
        return MIN <= index && index <= MAX;
    }

    private boolean outRange(final int index) {
        return !inRange(index);
    }

    private boolean exists(final int index) {
        return this.bodies.get(index).isPresent();
    }

    private boolean notExists(final int index) {
        return !exists(index);
    } 

    private boolean isDuplicate(final BodyPartCard newBody) {
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

    @Override
    public String toString() {
        var result = this.bodies
            .stream()
            .filter(Optional::isPresent)
            .map(maybeBody -> maybeBody.map(body -> body.toString()).orElse(""))
            .collect(Collectors.toList());

        return String.join(System.getProperty("line.separator"), result);
    }

}
