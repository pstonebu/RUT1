package com.stoneburner.rut1;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Couple {
    private Person one;
    private Person two;

    public Couple(Person one, Person two) throws SamePersonException {
        if (one.equals(two)) {
            throw new SamePersonException();
        }
        this.one = one;
        this.two = two;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Couple couple = (Couple) o;

        return (one == couple.one && two == couple.two) || (one == couple.two && two == couple.one);
    }

    @Override
    public int hashCode() {
        int result = one != null ? one.hashCode() : 0;
        result = 31 * result + (two != null ? two.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return one + " & " + two;
    }
}
