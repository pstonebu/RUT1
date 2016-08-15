import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.List;

class Episode {

    private int numBeams;
    private List<Pair> pairs = new ArrayList<Pair>();

    Episode() {
    }

    int getNumBeams() {
        return numBeams;
    }

    void setNumBeams(int numBeams) {
        this.numBeams = numBeams;
    }

    List<Pair> getPairs() {
        return pairs;
    }

    void addPair(Main.GUY guy, Main.GIRL girl) {
        pairs.add(new Pair<Main.GUY, Main.GIRL>(guy, girl));
    }
}
