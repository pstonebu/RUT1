import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        Episode episodeOne = new Episode();
        episodeOne.addPair(GUY.ASAF, GIRL.FRANCESCA);
        episodeOne.addPair(GUY.CAM, GIRL.VICTORIA);
        episodeOne.addPair(GUY.CAMERON, GIRL.MIKALA);
        episodeOne.addPair(GUY.GIOVANNI, GIRL.KAYLEN);
        episodeOne.addPair(GUY.JOHN, GIRL.EMMA);
        episodeOne.addPair(GUY.MORGAN, GIRL.JULIA);
        episodeOne.addPair(GUY.PROSPER, GIRL.CAMILLE);
        episodeOne.addPair(GUY.SAM, GIRL.ALYSSA);
        episodeOne.addPair(GUY.STEPHEN, GIRL.NICOLE);
        episodeOne.addPair(GUY.TYLER, GIRL.TORI);
        episodeOne.setNumBeams(3);

        Episode episodeTwo = new Episode();
        episodeTwo.addPair(GUY.ASAF, GIRL.CAMILLE);
        episodeTwo.addPair(GUY.CAM, GIRL.JULIA);
        episodeTwo.addPair(GUY.CAMERON, GIRL.MIKALA);
        episodeTwo.addPair(GUY.GIOVANNI, GIRL.KAYLEN);
        episodeTwo.addPair(GUY.JOHN, GIRL.NICOLE);
        episodeTwo.addPair(GUY.MORGAN, GIRL.ALYSSA);
        episodeTwo.addPair(GUY.PROSPER, GIRL.EMMA);
        episodeTwo.addPair(GUY.SAM, GIRL.FRANCESCA);
        episodeTwo.addPair(GUY.STEPHEN, GIRL.TORI);
        episodeTwo.addPair(GUY.TYLER, GIRL.VICTORIA);
        episodeTwo.setNumBeams(3);

        Episode episodeThree = new Episode();
        episodeThree.addPair(GUY.ASAF, GIRL.CAMILLE);
        episodeThree.addPair(GUY.CAM, GIRL.NICOLE);
        episodeThree.addPair(GUY.CAMERON, GIRL.MIKALA);
        episodeThree.addPair(GUY.GIOVANNI, GIRL.KAYLEN);
        episodeThree.addPair(GUY.JOHN, GIRL.VICTORIA);
        episodeThree.addPair(GUY.MORGAN, GIRL.FRANCESCA);
        episodeThree.addPair(GUY.PROSPER, GIRL.EMMA);
        episodeThree.addPair(GUY.SAM, GIRL.ALYSSA);
        episodeThree.addPair(GUY.STEPHEN, GIRL.TORI);
        episodeThree.addPair(GUY.TYLER, GIRL.JULIA);
        episodeThree.setNumBeams(4);

        Episode episodeFour = new Episode();
        episodeFour.addPair(GUY.ASAF, GIRL.CAMILLE);
        episodeFour.addPair(GUY.CAM, GIRL.EMMA);
        episodeFour.addPair(GUY.CAMERON, GIRL.MIKALA);
        episodeFour.addPair(GUY.GIOVANNI, GIRL.KAYLEN);
        episodeFour.addPair(GUY.JOHN, GIRL.VICTORIA);
        episodeFour.addPair(GUY.MORGAN, GIRL.TORI);
        episodeFour.addPair(GUY.PROSPER, GIRL.NICOLE);
        episodeFour.addPair(GUY.SAM, GIRL.ALYSSA);
        episodeFour.addPair(GUY.STEPHEN, GIRL.JULIA);
        episodeFour.addPair(GUY.TYLER, GIRL.FRANCESCA);
        episodeFour.setNumBeams(4);

        Episode episodeFive = new Episode();
        episodeFive.addPair(GUY.ASAF, GIRL.CAMILLE);
        episodeFive.addPair(GUY.CAM, GIRL.EMMA);
        episodeFive.addPair(GUY.CAMERON, GIRL.MIKALA);
        episodeFive.addPair(GUY.GIOVANNI, GIRL.FRANCESCA);
        episodeFive.addPair(GUY.JOHN, GIRL.TORI);
        episodeFive.addPair(GUY.MORGAN, GIRL.JULIA);
        episodeFive.addPair(GUY.PROSPER, GIRL.VICTORIA);
        episodeFive.addPair(GUY.SAM, GIRL.ALYSSA);
        episodeFive.addPair(GUY.STEPHEN, GIRL.NICOLE);
        episodeFive.addPair(GUY.TYLER, GIRL.KAYLEN);
        episodeFive.setNumBeams(4);

        Episode episodeSix = new Episode();
        episodeSix.addPair(GUY.ASAF, GIRL.CAMILLE);
        episodeSix.addPair(GUY.CAM, GIRL.VICTORIA);
        episodeSix.addPair(GUY.CAMERON, GIRL.MIKALA);
        episodeSix.addPair(GUY.GIOVANNI, GIRL.FRANCESCA);
        episodeSix.addPair(GUY.JOHN, GIRL.EMMA);
        episodeSix.addPair(GUY.MORGAN, GIRL.TORI);
        episodeSix.addPair(GUY.PROSPER, GIRL.KAYLEN);
        episodeSix.addPair(GUY.SAM, GIRL.ALYSSA);
        episodeSix.addPair(GUY.STEPHEN, GIRL.JULIA);
        episodeSix.addPair(GUY.TYLER, GIRL.NICOLE);
        episodeSix.setNumBeams(4);

        Episode episodeSeven = new Episode();
        episodeSeven.addPair(GUY.ASAF, GIRL.FRANCESCA);
        episodeSeven.addPair(GUY.CAM, GIRL.NICOLE);
        episodeSeven.addPair(GUY.CAMERON, GIRL.MIKALA);
        episodeSeven.addPair(GUY.GIOVANNI, GIRL.EMMA);
        episodeSeven.addPair(GUY.JOHN, GIRL.KAYLEN);
        episodeSeven.addPair(GUY.MORGAN, GIRL.TORI);
        episodeSeven.addPair(GUY.PROSPER, GIRL.VICTORIA);
        episodeSeven.addPair(GUY.SAM, GIRL.ALYSSA);
        episodeSeven.addPair(GUY.STEPHEN, GIRL.JULIA);
        episodeSeven.addPair(GUY.TYLER, GIRL.CAMILLE);
        episodeSeven.setNumBeams(4);

        List<Pair> confirmed = new ArrayList<Pair>();
        confirmed.add(new Pair<GUY,GIRL>(GUY.CAMERON, GIRL.MIKALA));
        confirmed.add(new Pair<GUY,GIRL>(GUY.SAM, GIRL.ALYSSA));

        List<Pair> disconfirmed = new ArrayList<Pair>();
        disconfirmed.add(new Pair<GUY,GIRL>(GUY.PROSPER, GIRL.TORI));
        disconfirmed.add(new Pair<GUY,GIRL>(GUY.JOHN, GIRL.JULIA));
        disconfirmed.add(new Pair<GUY,GIRL>(GUY.ASAF, GIRL.TORI));
        disconfirmed.add(new Pair<GUY,GIRL>(GUY.GIOVANNI, GIRL.KAYLEN));
        disconfirmed.add(new Pair<GUY,GIRL>(GUY.CAM, GIRL.VICTORIA));

        List<Episode> episodes = new ArrayList<Episode>();
        episodes.add(episodeOne);
        episodes.add(episodeTwo);
        episodes.add(episodeThree);
        episodes.add(episodeFour);
        episodes.add(episodeFive);
        episodes.add(episodeSix);
        episodes.add(episodeSeven);

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

                                                List<Pair> current = new ArrayList<Pair>();
                                                current.add(new Pair<GUY,GIRL>(GUY.values()[0], first));
                                                current.add(new Pair<GUY,GIRL>(GUY.values()[1], second));
                                                current.add(new Pair<GUY,GIRL>(GUY.values()[2], third));
                                                current.add(new Pair<GUY,GIRL>(GUY.values()[3], fourth));
                                                current.add(new Pair<GUY,GIRL>(GUY.values()[4], fifth));
                                                current.add(new Pair<GUY,GIRL>(GUY.values()[5], sixth));
                                                current.add(new Pair<GUY,GIRL>(GUY.values()[6], seventh));
                                                current.add(new Pair<GUY,GIRL>(GUY.values()[7], eighth));
                                                current.add(new Pair<GUY,GIRL>(GUY.values()[8], ninth));
                                                current.add(new Pair<GUY,GIRL>(GUY.values()[9], tenth));

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
        ASAF, CAM, CAMERON, GIOVANNI, JOHN, MORGAN, PROSPER, SAM, STEPHEN, TYLER
    }

    enum GIRL {
        ALYSSA, CAMILLE, EMMA, FRANCESCA, JULIA, KAYLEN, MIKALA, NICOLE, TORI, VICTORIA
    }
}
