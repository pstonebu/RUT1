package com.stoneburner.rut1;

import java.util.*;

import static com.stoneburner.rut1.GUY.*;
import static com.stoneburner.rut1.GIRL.*;
import static java.util.Arrays.copyOfRange;

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
        episodeOne.addCouple(ANDREW, LAUREN);
        episodeOne.addCouple(BRETT, CALI);
        episodeOne.addCouple(CAM, KAYLA);
        episodeOne.addCouple(DANIEL, NUTSA);
        episodeOne.addCouple(KWASI, ASIA);
        episodeOne.addCouple(LEWIS, SAMANTHA);
        episodeOne.addCouple(MOE, JASMINE);
        episodeOne.addCouple(SHAMOY, MARIA);
        episodeOne.addCouple(TEVIN, KENYA);
        episodeOne.addCouple(TOMAS, MORGAN);
        episodeOne.addCouple(ZAK, BRIA);
        episodeOne.setNumBeams(3);

        episodes.add(episodeOne);

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
        availableGirls = copyOfRange(availableGirls, 0, availableGirlsCounter);
        availableGuys = copyOfRange(availableGuys, 0, availableGuysCounter);

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
            int index = treemap.keySet().toArray(new Integer[0])[i];
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
