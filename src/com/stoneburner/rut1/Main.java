package com.stoneburner.rut1;

import com.google.common.base.Stopwatch;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Stopwatch.createStarted;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Sets.newLinkedHashSet;
import static com.stoneburner.rut1.Person.*;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Collections.disjoint;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@SpringBootApplication
public class Main {
    private static Set<Ceremony> ceremonies = newHashSet();
    private static Set<Couple> perfectMatches = newHashSet();
    private static Set<Couple> noMatches = newHashSet();
    private static List<Set<Couple>> winningCouples = newArrayList();
    private static List<List<Integer>> matchupMath = newArrayList();

    public static void main(String[] args) throws Exception {
        //preload our math matrix with zeroes
        matchupMath = range(0, values().length)
                .mapToObj(i -> {
                    return range(0, values().length)
                            .mapToObj(j -> {return 0;})
                            .collect(toList());
                })
                .collect(toList());

        Ceremony one = new Ceremony();
        one.addCouple(AASHA, PAIGE);
        one.addCouple(AMBER, NOUR);
        one.addCouple(BASIT, JONATHAN);
        one.addCouple(BRANDON, REMY);
        one.addCouple(DANNY, KAI);
        one.addCouple(JASMINE, JENNA);
        one.addCouple(JUSTIN, MAX);
        one.addCouple(KARI, KYLIE);
        one.setNumBeams(2);

        Ceremony two = new Ceremony();
        two.addCouple(AASHA, BRANDON);
        two.addCouple(AMBER, NOUR);
        two.addCouple(BASIT, JONATHAN);
        two.addCouple(DANNY, REMY);
        two.addCouple(JASMINE, JUSTIN);
        two.addCouple(JENNA, KAI);
        two.addCouple(KARI, KYLIE);
        two.addCouple(MAX, PAIGE);
        two.setNumBeams(2);

        Ceremony three = new Ceremony();
        three.addCouple(AASHA, MAX);
        three.addCouple(AMBER, PAIGE);
        three.addCouple(BASIT, REMY);
        three.addCouple(BRANDON, JONATHAN);
        three.addCouple(DANNY, KAI);
        three.addCouple(JASMINE, NOUR);
        three.addCouple(JENNA, JUSTIN);
        three.addCouple(KARI, KYLIE);
        three.setNumBeams(2);

        Ceremony four = new Ceremony();
        four.addCouple(AASHA, REMY);
        four.addCouple(AMBER, NOUR);
        four.addCouple(BASIT, DANNY);
        four.addCouple(BRANDON, JASMINE);
        four.addCouple(JENNA, PAIGE);
        four.addCouple(JONATHAN, KYLIE);
        four.addCouple(JUSTIN, MAX);
        four.addCouple(KAI, KARI);
        four.setNumBeams(1);

        Ceremony six = new Ceremony();
        six.addCouple(AASHA, BRANDON);
        six.addCouple(AMBER, JENNA);
        six.addCouple(BASIT,JONATHAN);
        six.addCouple(DANNY, KAI);
        six.addCouple(JASMINE, KYLIE);
        six.addCouple(JUSTIN, MAX);
        six.addCouple(KARI, PAIGE);
        six.addCouple(NOUR, REMY);
        six.setNumBeams(3);

        Ceremony seven = new Ceremony();
        seven.addCouple(AASHA, BRANDON);
        seven.addCouple(AMBER, DANNY);
        seven.addCouple(BASIT,JONATHAN);
        seven.addCouple(JASMINE, KAI);
        seven.addCouple(JENNA, PAIGE);
        seven.addCouple(JUSTIN, MAX);
        seven.addCouple(KARI, REMY);
        seven.addCouple(KYLIE, NOUR);
        seven.setNumBeams(3);

        ceremonies.add(one);
        ceremonies.add(two);
        ceremonies.add(three);
        ceremonies.add(four);
        ceremonies.add(six);
        ceremonies.add(seven);

        //No match truth booths
        noMatches.add(new Couple(JUSTIN, NOUR));
        noMatches.add(new Couple(BRANDON, REMY));
        noMatches.add(new Couple(JENNA, KAI));
        noMatches.add(new Couple(JENNA, DANNY));
        noMatches.add(new Couple(KYLIE, KARI));
        //adding the blackout from ceremony five here to speed things up
        noMatches.add(new Couple(AASHA, KAI));
        noMatches.add(new Couple(AMBER, NOUR));
        noMatches.add(new Couple(BASIT, REMY));
        noMatches.add(new Couple(BRANDON, MAX));
        noMatches.add(new Couple(DANNY, KARI));
        noMatches.add(new Couple(JASMINE, PAIGE));
        noMatches.add(new Couple(JENNA, KYLIE));
        noMatches.add(new Couple(JONATHAN, JUSTIN));

        //Perfect match truth booths
        perfectMatches.add(new Couple(AASHA, BRANDON));

        // Remove any people in a proven couple from available people
        Set<Person> availablePeople = newHashSet(asList(values()));
        perfectMatches.parallelStream().forEach(c -> {
            availablePeople.remove(c.getOne());
            availablePeople.remove(c.getTwo());
        });
        Stopwatch stopwatch = createStarted();
        recurseCouples(availablePeople, newArrayList(perfectMatches));
        stopwatch.stop();
        System.out.println("Time to recurse: " + stopwatch.elapsed(MILLISECONDS)/1000.0 + " seconds.");
        System.out.println("Number of winning combinations: " + winningCouples.size());
        if (winningCouples.size() < 20) {
            for (Set<Couple> couples : winningCouples) {
                System.out.println("Winning combination: " + couples);
            }
        }
        
        System.out.println("\nMatchup math matrix: ");
        //math in strings
        String[][] mathOutput = new String[values().length+1][values().length+1];
        //add names
        mathOutput[0][0] = "";
        asList(values()).parallelStream().forEach(p -> {
            mathOutput[0][p.ordinal()+1] = p.name();
            mathOutput[p.ordinal()+1][0] = p.name();
        });

        DecimalFormat df = new DecimalFormat("##.##%");
        range(1, values().length+1)
                .forEach(i -> {
                    range(1, values().length+1)
                            .forEach(j -> {
                                int coupleCount = matchupMath.get(i-1).get(j-1);
                                mathOutput[i][j] = coupleCount == 0 ? "X" : df.format((double)coupleCount/winningCouples.size());
                            });
                });

        new Printer().print(mathOutput);
    }

    private static void recurseCouples(Set<Person> availablePeople, List<Couple> currentResults) throws SamePersonException {
        if (availablePeople.size() < 2) {
            if (disjoint(noMatches, currentResults)) {
                boolean match = true;
                for (Ceremony ceremony : ceremonies) {
                    Set<Couple> intersect = newHashSet(currentResults);
                    intersect.retainAll(ceremony.getCouples());
                    if (intersect.size() != ceremony.getNumBeams()) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    //add to our winning couples list
                    winningCouples.add(newHashSet(currentResults));

                    //add the matchups to the math matrix
                    currentResults.parallelStream().forEach(c -> {
                        int currentCount = matchupMath.get(c.getOne().ordinal()).get(c.getTwo().ordinal());
                        matchupMath.get(c.getOne().ordinal()).set(c.getTwo().ordinal(), ++currentCount);
                        matchupMath.get(c.getTwo().ordinal()).set(c.getOne().ordinal(), currentCount);
                    });
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
            if (!noMatches.contains(couple)) {
                currentResults.add(couple);
                recurseCouples(nextSet, currentResults);
                currentResults.remove(couple);
            }
        }
    }
}
