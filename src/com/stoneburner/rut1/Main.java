package com.stoneburner.rut1;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Sets.newLinkedHashSet;
import static com.stoneburner.rut1.Person.*;
import static com.sun.deploy.util.StringUtils.join;
import static java.util.Arrays.stream;
import static java.util.Collections.disjoint;
import static java.util.stream.Collectors.toSet;

@SpringBootApplication
public class Main {
    private static Set<Episode> episodes = newHashSet();
    private static Set<Couple> perfectMatches = newHashSet();
    private static Set<Couple> noMatches = newHashSet();
    private static List<Set<Couple>> winningCouples = newArrayList();

    public static void main(String[] args) throws Exception {
        Episode one = new Episode();
        one.addCouple(AASHA, PAIGE);
        one.addCouple(AMBER, NOUR);
        one.addCouple(BASIT, JONATHAN);
        one.addCouple(BRANDON, REMY);
        one.addCouple(DANNY, KAI);
        one.addCouple(JASMINE, JENNA);
        one.addCouple(JUSTIN, MAX);
        one.addCouple(KARI, KYLIE);
        one.setNumBeams(2);

        Episode two = new Episode();
        two.addCouple(AASHA, BRANDON);
        two.addCouple(AMBER, NOUR);
        two.addCouple(BASIT, JONATHAN);
        two.addCouple(DANNY, REMY);
        two.addCouple(JASMINE, JUSTIN);
        two.addCouple(JENNA, KAI);
        two.addCouple(KARI, KYLIE);
        two.addCouple(MAX, PAIGE);
        two.setNumBeams(2);

        Episode three = new Episode();
        three.addCouple(AASHA, MAX);
        three.addCouple(AMBER, PAIGE);
        three.addCouple(BASIT, REMY);
        three.addCouple(BRANDON, JONATHAN);
        three.addCouple(DANNY, KAI);
        three.addCouple(JASMINE, NOUR);
        three.addCouple(JENNA, JUSTIN);
        three.addCouple(KARI, KYLIE);
        three.setNumBeams(2);

        Episode four = new Episode();
        four.addCouple(AASHA, REMY);
        four.addCouple(AMBER, NOUR);
        four.addCouple(BASIT, DANNY);
        four.addCouple(BRANDON, JASMINE);
        four.addCouple(JENNA, PAIGE);
        four.addCouple(JONATHAN, KYLIE);
        four.addCouple(JUSTIN, MAX);
        four.addCouple(KAI, KARI);
        four.setNumBeams(1);

        episodes.add(one);
        episodes.add(two);
        episodes.add(three);
        episodes.add(four);

        //No match truth booths
        noMatches.add(new Couple(JUSTIN, NOUR));
        noMatches.add(new Couple(BRANDON, REMY));
        noMatches.add(new Couple(JENNA, KAI));
        noMatches.add(new Couple(JENNA, DANNY));

        //Perfect match truth booths

        // Add any people that aren't in a proven couple to available people
        Set<Person> availablePeople = stream(Person.values())
                .filter(p -> perfectMatches.stream()
                        .noneMatch(c -> c.getOne() != p || c.getTwo() != p))
                .collect(toSet());

        recurseCouples(availablePeople, newArrayList());
        System.out.println("Number of winning combinations: " + winningCouples.size());
        if (winningCouples.size() < 20) {
            for (Set<Couple> couples : winningCouples) {
                System.out.println("Winning combination: " + join(couples, ", "));
            }
        }
    }

    private static void recurseCouples(Set<Person> availablePeople, List<Couple> currentResults) throws SamePersonException {
        if (availablePeople.size() < 2) {
            List<Couple> listToTest = newArrayList(currentResults);
            listToTest.addAll(perfectMatches);
            if (disjoint(noMatches, listToTest)) {
                boolean match = true;
                for (Episode episode : episodes) {
                    Set<Couple> intersect = newHashSet(listToTest);
                    intersect.retainAll(episode.getCouples());
                    if (intersect.size() != episode.getNumBeams()) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    winningCouples.add(newHashSet(listToTest));
                }
            }
            return;
        }
        
        List<Person> list = newArrayList(availablePeople);
        Person first = list.remove(0);
        for (int i = 0; i < list.size(); i++) {
            Person second = list.get(i);
            Set<Person> nextSet = newLinkedHashSet(list);
            nextSet.remove(second);

            Couple couple = new Couple(first, second);
            currentResults.add(couple);
            recurseCouples(nextSet, currentResults);
            currentResults.remove(couple);
        }
    }
}
