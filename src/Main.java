import java.util.*;

public class Main {
    private static List<Episode> episodes = new ArrayList<Episode>();;
    private static List<Couple> proven = new ArrayList<Couple>();
    private static List<Couple> disproven = new ArrayList<Couple>();;
    private static List<List<Couple>> winningCouples = new ArrayList<List<Couple>>();
    private static TreeMap<Integer,GIRL> treemap = new TreeMap<Integer,GIRL>();
    private static List<GIRL> girls = new ArrayList<GIRL>();
    private static GIRL[] availableGirls = new GIRL[GIRL.values().length];
    private static GUY[] availableGuys = new GUY[GIRL.values().length];

    public static void main(String[] args) {
        Episode episodeOne = new Episode();
        episodeOne.addCouple(GUY.ANDRE, GIRL.ALICIA);
        episodeOne.addCouple(GUY.DERRICK, GIRL.KATHRYN);
        episodeOne.addCouple(GUY.EDWARD, GIRL.KAM);
        episodeOne.addCouple(GUY.HAYDEN, GIRL.SHANNON);
        episodeOne.addCouple(GUY.JAYLAN, GIRL.CASANDRA);
        episodeOne.addCouple(GUY.JOEY, GIRL.CAROLINA);
        episodeOne.addCouple(GUY.MICHAEL, GIRL.HANNAH);
        episodeOne.addCouple(GUY.MIKE, GIRL.KARI);
        episodeOne.addCouple(GUY.OSVALDO, GIRL.TYRANNY);
        episodeOne.addCouple(GUY.OZZY, GIRL.GIANNA);
        episodeOne.addCouple(GUY.TYLER, GIRL.TAYLOR);
        episodeOne.setNumBeams(2);

        Episode episodeTwo = new Episode();
        episodeTwo.addCouple(GUY.ANDRE, GIRL.HANNAH);
        episodeTwo.addCouple(GUY.DERRICK, GIRL.ALICIA);
        episodeTwo.addCouple(GUY.EDWARD, GIRL.SHANNON);
        episodeTwo.addCouple(GUY.HAYDEN, GIRL.TAYLOR);
        episodeTwo.addCouple(GUY.JAYLAN, GIRL.KAM);
        episodeTwo.addCouple(GUY.JOEY, GIRL.CAROLINA);
        episodeTwo.addCouple(GUY.MICHAEL, GIRL.GIANNA);
        episodeTwo.addCouple(GUY.MIKE, GIRL.CASANDRA);
        episodeTwo.addCouple(GUY.OSVALDO, GIRL.KARI);
        episodeTwo.addCouple(GUY.OZZY, GIRL.KATHRYN);
        episodeTwo.addCouple(GUY.TYLER, GIRL.TYRANNY);
        episodeTwo.setNumBeams(0);

        Episode episodeThree = new Episode();
        episodeThree.addCouple(GUY.ANDRE, GIRL.KARI);
        episodeThree.addCouple(GUY.DERRICK, GIRL.HANNAH);
        episodeThree.addCouple(GUY.EDWARD, GIRL.KAM);
        episodeThree.addCouple(GUY.HAYDEN, GIRL.CAROLINA);
        episodeThree.addCouple(GUY.JAYLAN, GIRL.CASANDRA);
        episodeThree.addCouple(GUY.JOEY, GIRL.KATHRYN);
        episodeThree.addCouple(GUY.MICHAEL, GIRL.TAYLOR);
        episodeThree.addCouple(GUY.MIKE, GIRL.ALICIA);
        episodeThree.addCouple(GUY.OSVALDO, GIRL.TYRANNY);
        episodeThree.addCouple(GUY.OZZY, GIRL.GIANNA);
        episodeThree.addCouple(GUY.TYLER, GIRL.SHANNON);
        episodeThree.setNumBeams(4);

        Episode episodeFour = new Episode();
        episodeFour.addCouple(GUY.ANDRE, GIRL.CASANDRA);
        episodeFour.addCouple(GUY.DERRICK, GIRL.GIANNA);
        episodeFour.addCouple(GUY.EDWARD, GIRL.ALICIA);
        episodeFour.addCouple(GUY.HAYDEN, GIRL.CAROLINA);
        episodeFour.addCouple(GUY.JAYLAN, GIRL.TYRANNY);
        episodeFour.addCouple(GUY.JOEY, GIRL.KATHRYN);
        episodeFour.addCouple(GUY.MICHAEL, GIRL.KARI);
        episodeFour.addCouple(GUY.MIKE, GIRL.KAM);
        episodeFour.addCouple(GUY.OSVALDO, GIRL.TAYLOR);
        episodeFour.addCouple(GUY.OZZY, GIRL.HANNAH);
        episodeFour.addCouple(GUY.TYLER, GIRL.SHANNON);
        episodeFour.setNumBeams(4);

        Episode episodeFive = new Episode();
        episodeFive.addCouple(GUY.ANDRE, GIRL.TAYLOR);
        episodeFive.addCouple(GUY.DERRICK, GIRL.TYRANNY);
        episodeFive.addCouple(GUY.EDWARD, GIRL.KAM);
        episodeFive.addCouple(GUY.HAYDEN, GIRL.CAROLINA);
        episodeFive.addCouple(GUY.JAYLAN, GIRL.CASANDRA);
        episodeFive.addCouple(GUY.JOEY, GIRL.KATHRYN);
        episodeFive.addCouple(GUY.MICHAEL, GIRL.KARI);
        episodeFive.addCouple(GUY.MIKE, GIRL.ALICIA);
        episodeFive.addCouple(GUY.OSVALDO, GIRL.GIANNA);
        episodeFive.addCouple(GUY.OZZY, GIRL.HANNAH);
        episodeFive.addCouple(GUY.TYLER, GIRL.SHANNON);
        episodeFive.setNumBeams(4);

        Episode episodeSix = new Episode();
        episodeSix.addCouple(GUY.ANDRE, GIRL.TAYLOR);
        episodeSix.addCouple(GUY.DERRICK, GIRL.TYRANNY);
        episodeSix.addCouple(GUY.EDWARD, GIRL.KAM);
        episodeSix.addCouple(GUY.HAYDEN, GIRL.CAROLINA);
        episodeSix.addCouple(GUY.JAYLAN, GIRL.HANNAH);
        episodeSix.addCouple(GUY.JOEY, GIRL.KATHRYN);
        episodeSix.addCouple(GUY.MICHAEL, GIRL.KARI);
        episodeSix.addCouple(GUY.MIKE, GIRL.ALICIA);
        episodeSix.addCouple(GUY.OSVALDO, GIRL.GIANNA);
        episodeSix.addCouple(GUY.OZZY, GIRL.CASANDRA);
        episodeSix.addCouple(GUY.TYLER, GIRL.SHANNON);
        episodeSix.setNumBeams(4);

        Episode episodeSeven = new Episode();
        episodeSeven.addCouple(GUY.ANDRE, GIRL.CASANDRA);
        episodeSeven.addCouple(GUY.DERRICK, GIRL.KARI);
        episodeSeven.addCouple(GUY.EDWARD, GIRL.KAM);
        episodeSeven.addCouple(GUY.HAYDEN, GIRL.CAROLINA);
        episodeSeven.addCouple(GUY.JAYLAN, GIRL.TYRANNY);
        episodeSeven.addCouple(GUY.JOEY, GIRL.KATHRYN);
        episodeSeven.addCouple(GUY.MICHAEL, GIRL.HANNAH);
        episodeSeven.addCouple(GUY.MIKE, GIRL.GIANNA);
        episodeSeven.addCouple(GUY.OSVALDO, GIRL.TAYLOR);
        episodeSeven.addCouple(GUY.OZZY, GIRL.ALICIA);
        episodeSeven.addCouple(GUY.TYLER, GIRL.SHANNON);
        episodeSeven.setNumBeams(4);

        Episode episodeEight = new Episode();
        episodeEight.addCouple(GUY.ANDRE, GIRL.GIANNA);
        episodeEight.addCouple(GUY.DERRICK, GIRL.SHANNON);
        episodeEight.addCouple(GUY.EDWARD, GIRL.KAM);
        episodeEight.addCouple(GUY.HAYDEN, GIRL.CAROLINA);
        episodeEight.addCouple(GUY.JAYLAN, GIRL.TYRANNY);
        episodeEight.addCouple(GUY.JOEY, GIRL.CASANDRA);
        episodeEight.addCouple(GUY.MICHAEL, GIRL.KATHRYN);
        episodeEight.addCouple(GUY.MIKE, GIRL.KARI);
        episodeEight.addCouple(GUY.OSVALDO, GIRL.TAYLOR);
        episodeEight.addCouple(GUY.OZZY, GIRL.ALICIA);
        episodeEight.addCouple(GUY.TYLER, GIRL.HANNAH);
        episodeEight.setNumBeams(5);

        Episode episodeNine = new Episode();
        episodeNine.addCouple(GUY.ANDRE, GIRL.KATHRYN);
        episodeNine.addCouple(GUY.DERRICK, GIRL.CASANDRA);
        episodeNine.addCouple(GUY.EDWARD, GIRL.KAM);
        episodeNine.addCouple(GUY.HAYDEN, GIRL.CAROLINA);
        episodeNine.addCouple(GUY.JAYLAN, GIRL.TYRANNY);
        episodeNine.addCouple(GUY.JOEY, GIRL.HANNAH);
        episodeNine.addCouple(GUY.MICHAEL, GIRL.KARI);
        episodeNine.addCouple(GUY.MIKE, GIRL.ALICIA);
        episodeNine.addCouple(GUY.OSVALDO, GIRL.TAYLOR);
        episodeNine.addCouple(GUY.OZZY, GIRL.GIANNA);
        episodeNine.addCouple(GUY.TYLER, GIRL.SHANNON);
        episodeNine.setNumBeams(9);

        disproven.add(new Couple(GUY.HAYDEN, GIRL.GIANNA));
        disproven.add(new Couple(GUY.ANDRE, GIRL.ALICIA));
        disproven.add(new Couple(GUY.OZZY, GIRL.CAROLINA));
        disproven.add(new Couple(GUY.OSVALDO, GIRL.TYRANNY));
        proven.add(new Couple(GUY.EDWARD, GIRL.KAM));
        disproven.add(new Couple(GUY.OZZY, GIRL.HANNAH));
        disproven.add(new Couple(GUY.ANDRE, GIRL.TAYLOR));
        proven.add(new Couple(GUY.HAYDEN, GIRL.CAROLINA));

        episodes.add(episodeOne);
        episodes.add(episodeTwo);
        episodes.add(episodeThree);
        episodes.add(episodeFour);
        episodes.add(episodeFive);
        episodes.add(episodeSix);
        episodes.add(episodeSeven);
        episodes.add(episodeEight);
        episodes.add(episodeNine);

        int availableGirlsCounter = 0, availableGuysCounter = 0;
        for (int i = 0; i < GIRL.values().length; i++) {
            boolean girlAvailable = true;
            boolean guyAvailable = true;
            for (Couple couple : proven) {
                if (couple.getGirl() == GIRL.values()[i]) {
                    girlAvailable = false;
                }
                if (couple.getGuy() == GUY.values()[i]) {
                    guyAvailable = false;
                }
            }
            if (girlAvailable) {
                availableGirls[availableGirlsCounter++] = GIRL.values()[i];
            }
            if (guyAvailable) {
                availableGuys[availableGuysCounter++] = GUY.values()[i];
            }
        }
        availableGirls = Arrays.copyOfRange(availableGirls, 0, availableGirlsCounter);
        availableGuys = Arrays.copyOfRange(availableGuys, 0, availableGuysCounter);

        for (int i = 0; i < availableGirls.length; i++) {
            treemap.put(i, availableGirls[i]);
        }

        recurseCouples();

        System.out.println("Number of winning combinations: " + winningCouples.size());
        if (winningCouples.size() < 20 && winningCouples.size() > 0) {
            for (List<Couple> coupleList : winningCouples) {
                System.out.print("Winning combination: ");
                StringBuilder couplesString = new StringBuilder();
                for (Couple couple : coupleList) {
                    if (couplesString.length() != 0) {
                        couplesString.append(", ");
                    }
                    couplesString.append(couple.getGuy()).append(" & ").append(couple.getGirl());
                }
                System.out.println(couplesString.toString());
            }
        }

    }

    private static void recurseCouples() {
        for (int i = 0; i < treemap.size(); i++) {
            int index = (Integer)treemap.keySet().toArray()[i];
            GIRL girl = treemap.remove(index);
            girls.add(girl);

            if (treemap.isEmpty()) {
                List<Couple> current = new ArrayList<Couple>();
                for (int j = 0; j < girls.size(); j++) {
                    Couple couple = new Couple(availableGuys[j], girls.get(j));
                    if (disproven.contains(couple)) {
                        break;
                    } else {
                        current.add(couple);
                    }
                }
                if (current.size() == girls.size()) {

                    current.addAll(proven);

                    if (current.containsAll(proven) && Collections.disjoint(disproven, current)) {
                        boolean match = true;
                        for (Episode episode : episodes) {
                            List<Couple> intersect = new ArrayList<Couple>(current);
                            intersect.retainAll(episode.getCouples());
                            if (intersect.size() != episode.getNumBeams()) {
                                match = false;
                                break;
                            }
                        }

                        if (match) {
                            winningCouples.add(current);
                        }
                    }
                }
            } else {
                recurseCouples();
            }
            girls.remove(girl);
            treemap.put(index, girl);
        }
    }
}
