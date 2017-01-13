import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class Main {

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


        List<Pair> confirmed = new ArrayList<Pair>();

        List<Pair> disconfirmed = new ArrayList<Pair>();
        disconfirmed.add(new Pair<GUY,GIRL>(GUY.HAYDEN, GIRL.GIANNA));

        List<Episode> episodes = new ArrayList<Episode>();
        episodes.add(episodeOne);

        List<List<Pair>> winningPairs = findWinningPairs(episodes, confirmed, disconfirmed);

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

    private static List<List<Pair>> findWinningPairs(List<Episode> episodes, List<Pair> confirmed, List<Pair> disconfirmed) {
        List<List<Pair>> winningPairs = new ArrayList<List<Pair>>();
        TreeMap<Integer,GIRL> treemap = new TreeMap<Integer,GIRL>();
        for (int i = 0; i < GIRL.values().length; i++) {
            treemap.put(i, GIRL.values()[i]);
        }

        recursePairs(episodes, confirmed, disconfirmed, winningPairs, treemap, new ArrayList<GIRL>());

        return winningPairs;
    }

    private static void recursePairs(List<Episode> episodes, List<Pair> confirmed, List<Pair> disconfirmed,
                                     List<List<Pair>> winningPairs, TreeMap<Integer,GIRL> treemap, List<GIRL> girls) {
        for (int i = 0; i < treemap.size() - confirmed.size(); i++) {
            int index = (Integer)treemap.keySet().toArray()[i];
            GIRL girl = treemap.remove(index);
            girls.add(girl);

            if (treemap.isEmpty()) {
                List<Pair> current = new ArrayList<Pair>();
                for (int j = 0; j < girls.size(); j++) {
                    Pair pair = new Pair<GUY, GIRL>(GUY.values()[j], girls.get(j));
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
                recursePairs(episodes, confirmed, disconfirmed, winningPairs, treemap, girls);
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
