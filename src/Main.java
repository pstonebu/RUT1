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
        int girlsLength = GIRL.values().length;
        for (int i = 0; i < girlsLength; i++) {
            treemap.put(i, GIRL.values()[i]);
        }

        for (int a = 0; a < girlsLength; a++) {
            int firstInt = (Integer)treemap.keySet().toArray()[a];
            GIRL first = treemap.remove(firstInt);

            for (int b = 0; b < girlsLength-1; b++) {
                int secondInt = (Integer)treemap.keySet().toArray()[b];
                GIRL second = treemap.remove(secondInt);

                for (int c = 0; c < girlsLength-2; c++) {
                    int thirdInt = (Integer)treemap.keySet().toArray()[c];
                    GIRL third = treemap.remove(thirdInt);

                    for (int d = 0; d < girlsLength-3; d++) {
                        int fourthInt = (Integer)treemap.keySet().toArray()[d];
                        GIRL fourth = treemap.remove(fourthInt);

                        for (int e = 0; e < girlsLength-4; e++) {
                            int fifthInt = (Integer)treemap.keySet().toArray()[e];
                            GIRL fifth = treemap.remove(fifthInt);

                            for (int f = 0; f < girlsLength-5; f++) {
                                int sixthInt = (Integer)treemap.keySet().toArray()[f];
                                GIRL sixth = treemap.remove(sixthInt);

                                for (int g = 0; g < girlsLength-6; g++) {
                                    int seventhInt = (Integer)treemap.keySet().toArray()[g];
                                    GIRL seventh = treemap.remove(seventhInt);

                                    for (int h = 0; h < girlsLength-7; h++) {
                                        int eighthInt = (Integer)treemap.keySet().toArray()[h];
                                        GIRL eighth = treemap.remove(eighthInt);

                                        for (int i = 0; i < girlsLength-8; i++) {
                                            int ninthInt = (Integer)treemap.keySet().toArray()[i];
                                            GIRL ninth = treemap.remove(ninthInt);

                                            for (int j = 0; j < girlsLength-9; j++) {
                                                int tenthInt = (Integer)treemap.keySet().toArray()[j];
                                                GIRL tenth = treemap.remove(tenthInt);

                                                for (int k = 0; k < girlsLength-10; k++) {
                                                    int eleventhInt = (Integer) treemap.keySet().toArray()[k];
                                                    GIRL eleventh = treemap.remove(eleventhInt);
                                                    List<Pair> current = new ArrayList<Pair>();
                                                    current.add(new Pair<GUY, GIRL>(GUY.values()[0], first));
                                                    current.add(new Pair<GUY, GIRL>(GUY.values()[1], second));
                                                    current.add(new Pair<GUY, GIRL>(GUY.values()[2], third));
                                                    current.add(new Pair<GUY, GIRL>(GUY.values()[3], fourth));
                                                    current.add(new Pair<GUY, GIRL>(GUY.values()[4], fifth));
                                                    current.add(new Pair<GUY, GIRL>(GUY.values()[5], sixth));
                                                    current.add(new Pair<GUY, GIRL>(GUY.values()[6], seventh));
                                                    current.add(new Pair<GUY, GIRL>(GUY.values()[7], eighth));
                                                    current.add(new Pair<GUY, GIRL>(GUY.values()[8], ninth));
                                                    current.add(new Pair<GUY, GIRL>(GUY.values()[9], tenth));
                                                    current.add(new Pair<GUY, GIRL>(GUY.values()[10], eleventh));

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
                                                    treemap.put(eleventhInt, eleventh);
                                                }
                                                treemap.put(tenthInt, tenth);
                                            }
                                            treemap.put(ninthInt, ninth);
                                        }
                                        treemap.put(eighthInt, eighth);
                                    }
                                    treemap.put(seventhInt, seventh);
                                }
                                treemap.put(sixthInt, sixth);
                            }
                            treemap.put(fifthInt, fifth);
                        }
                        treemap.put(fourthInt, fourth);
                    }
                    treemap.put(thirdInt, third);
                }
                treemap.put(secondInt, second);
            }
            treemap.put(firstInt, first);
        }


        return winningPairs;
    }

    enum GUY {
        ANDRE, DERRICK, EDWARD, HAYDEN, JAYLAN, JOEY, MICHAEL, MIKE, OSVALDO, OZZY, TYLER
    }

    enum GIRL {
        ALICIA, CAROLINA, CASANDRA, GIANNA, HANNAH, KAM, KARI, KATHRYN, SHANNON, TAYLOR, TYRANNY
    }
}
