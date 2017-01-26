import java.util.ArrayList;
import java.util.List;

class Episode {

    private int numBeams;
    private List<Couple> couples = new ArrayList<Couple>();

    Episode() {
    }

    int getNumBeams() {
        return numBeams;
    }

    void setNumBeams(int numBeams) {
        this.numBeams = numBeams;
    }

    List<Couple> getCouples() {
        return couples;
    }

    void addCouple(GUY guy, GIRL girl) {
        couples.add(new Couple(guy, girl));
    }
}
