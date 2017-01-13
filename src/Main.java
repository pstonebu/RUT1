import com.sun.tools.javac.util.Pair;

import java.util.*;

public class Main {
    private static List<Episode> episodes = new ArrayList<Episode>();;
    private static List<Pair> confirmed = new ArrayList<Pair>();
    private static List<Pair> disconfirmed = new ArrayList<Pair>();;
    private static List<List<Pair>> winningPairs = new ArrayList<List<Pair>>();
    private static TreeMap<Integer,GIRL> treemap = new TreeMap<Integer,GIRL>();
    private static List<GIRL> girls = new ArrayList<GIRL>();
    private static GIRL[] availableGirls = new GIRL[GIRL.values().length];
    private static GUY[] availableGuys = new GUY[GIRL.values().length];

    public static void main(String[] args) {
        Episode episodeOne = new Episode();
        episodeOne.addPair(GUY.ANDRE, GIRL.ALICIA);
        episodeOne.addPair(GUY.DERRICK, GIRL.KATHRYN);
        episodeOne.addPair(GUY.EDWARD, GIRL.KAM);
        episodeOne.addPair(GUY.HAYDEN, GIRL.SHANNON);
        episodeOne.addPair(GUY.JAYLAN, GIRL.CASANDRA);
        episodeOne.addPair(GUY.JOEY, GIRL.CAROLINA);
        episodeOne.addPair(GUY.MICHAEL, GIRL.HANNAH);
        episodeOne.addPair(GUY.MIKE, GIRL.KARI);
        episodeOne.addPair(GUY.OSVALDO, GIRL.TYRANNY);
        episodeOne.addPair(GUY.OZZY, GIRL.GIANNA);
        episodeOne.addPair(GUY.TYLER, GIRL.TAYLOR);
        episodeOne.setNumBeams(2);

        disconfirmed.add(new Pair<GUY,GIRL>(GUY.HAYDEN, GIRL.GIANNA));

        episodes.add(episodeOne);

        int availableGirlsCounter = 0;
        for (int i = 0; i < GIRL.values().length; i++) {
            boolean available = true;
            for (Pair<GUY,GIRL> pair : confirmed) {
                if (pair.snd == GIRL.values()[i]) {
                    available = false;
                }
            }
            if (available) {
                availableGirls[availableGirlsCounter++] = GIRL.values()[i];
            }
        }
        availableGirls = Arrays.copyOfRange(availableGirls, 0, availableGirlsCounter);

        int availableGuysCounter = 0;
        for (int i = 0; i < GUY.values().length; i++) {
            boolean available = true;
            for (Pair<GUY,GIRL> pair : confirmed) {
                if (pair.fst == GUY.values()[i]) {
                    available = false;
                }
            }
            if (available) {
                availableGuys[availableGuysCounter++] = GUY.values()[i];
            }
        }
        availableGuys = Arrays.copyOfRange(availableGuys, 0, availableGuysCounter);

        for (int i = 0; i < availableGirls.length; i++) {
            treemap.put(i, availableGirls[i]);
        }

        recursePairs();

        System.out.println("Number of winning combinations: " + winningPairs.size());
        if (winningPairs.size() < 20 && winningPairs.size() > 0) {
            for (List<Pair> pairList : winningPairs) {
                System.out.print("Winning combination: ");
                for (Pair pair : pairList) {
                    System.out.print(pair.fst + " & " + pair.snd + ", ");
                }
                System.out.println();
            }
        }

    }

    private static void recursePairs() {
        for (int i = 0; i < treemap.size(); i++) {
            int index = (Integer)treemap.keySet().toArray()[i];
            GIRL girl = treemap.remove(index);
            girls.add(girl);

            if (treemap.isEmpty()) {
                List<Pair> current = new ArrayList<Pair>();
                for (int j = 0; j < girls.size(); j++) {
                    Pair pair = new Pair<GUY, GIRL>(availableGuys[j], girls.get(j));
                    if (disconfirmed.contains(pair)) {
                        break;
                    } else {
                        current.add(pair);
                    }
                }
                if (current.size() == girls.size()) {

                    current.addAll(confirmed);

                    if (current.containsAll(confirmed) && Collections.disjoint(disconfirmed, current)) {
                        boolean match = true;
                        for (Episode episode : episodes) {
                            List<Pair> intersect = new ArrayList<Pair>(current);
                            intersect.retainAll(episode.getPairs());
                            if (intersect.size() != episode.getNumBeams()) {
                                match = false;
                                break;
                            }
                        }

                        if (match) {
                            winningPairs.add(current);
                        }
                    }
                }
            } else {
                recursePairs();
            }
            girls.remove(girl);
            treemap.put(index, girl);
        }
    }

    enum GUY {
        ANDRE, DERRICK, EDWARD, HAYDEN, JAYLAN, JOEY, MICHAEL, MIKE, OSVALDO, OZZY, TYLER
    }

    enum GIRL {
        ALICIA, CAROLINA, CASANDRA, GIANNA, HANNAH, KAM, KARI, KATHRYN, SHANNON, TAYLOR, TYRANNY
    }
}
