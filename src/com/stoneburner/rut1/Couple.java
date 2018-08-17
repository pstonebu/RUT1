package com.stoneburner.rut1;

public class Couple {
    private GUY guy;
    private GIRL girl;

    public Couple(GUY guy, GIRL girl) {
        this.guy = guy;
        this.girl = girl;
    }

    public GUY getGuy() {
        return guy;
    }

    public void setGuy(GUY guy) {
        this.guy = guy;
    }

    public GIRL getGirl() {
        return girl;
    }

    public void setGirl(GIRL girl) {
        this.girl = girl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Couple couple = (Couple) o;

        if (guy != couple.guy) return false;
        if (girl != couple.girl) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = guy != null ? guy.hashCode() : 0;
        result = 31 * result + (girl != null ? girl.hashCode() : 0);
        return result;
    }
}
