package com.stoneburner.rut1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Getter @Setter @NoArgsConstructor
class Episode {
    private int numBeams;
    private List<Couple> couples = newArrayList();

    void addCouple(Person one, Person two) throws SamePersonException {
        couples.add(new Couple(one, two));
    }
}
