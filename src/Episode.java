import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Episode {

    private int numBeams;
    private List<Pair> pairs = new ArrayList<Pair>();

    public Episode() {
    }

    public int getNumBeams() {
        return numBeams;
    }

    public void setNumBeams(int numBeams) {
        this.numBeams = numBeams;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public void setPairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public void addPair(Main.GUY guy, Main.GIRL girl) {
        pairs.add(new Pair(guy, girl));
    }
}
