package com.stoneburner.rut1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.IntStream;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.IntStream.iterate;
import static java.util.stream.IntStream.range;

@Getter @Setter @NoArgsConstructor
class Ceremony {
    private int numBeams;
    private List<Couple> couples = newArrayList();

    public Ceremony(int numBeams, Person ... people) {
        this.numBeams = numBeams;
        iterate(0, i -> i + 2).limit(people.length/2).forEach(i -> {
            try {
                couples.add(new Couple(people[i], people[i+1]));
            } catch (SamePersonException e) { }
        });
    }

    void addCouple(Person one, Person two) throws SamePersonException {
        couples.add(new Couple(one, two));
    }
}
