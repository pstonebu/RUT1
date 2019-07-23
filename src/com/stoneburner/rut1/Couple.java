package com.stoneburner.rut1;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Couple {
    private Person one;
    private Person two;

    public Couple(Person one, Person two) throws SamePersonException {
        //not the same person twice, and then persist ordinal order
        if (one.equals(two)) {
            throw new SamePersonException();
        } else if (one.ordinal() < two.ordinal()) {
            this.one = one;
            this.two = two;
        } else {
            this.one = two;
            this.two = one;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Couple couple = (Couple) o;
        return one == couple.one && two == couple.two;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(one, two);
    }

    @Override
    public String toString() {
        return one + " & " + two;
    }
}
