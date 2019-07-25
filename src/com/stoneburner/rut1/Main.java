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
import static com.sun.deploy.util.StringUtils.join;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Collections.disjoint;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;

@SpringBootApplication
public class Main {
    private static Set<Episode> episodes = newHashSet();
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
        noMatches.add(new Couple(KYLIE, KARI));
        //adding the blackout from episode five here to speed things up
        noMatches.add(new Couple(AASHA, KAI));
        noMatches.add(new Couple(AMBER, NOUR));
        noMatches.add(new Couple(BASIT, REMY));
        noMatches.add(new Couple(BRANDON, MAX));
        noMatches.add(new Couple(DANNY, KARI));
        noMatches.add(new Couple(JASMINE, PAIGE));
        noMatches.add(new Couple(JENNA, KYLIE));
        noMatches.add(new Couple(JONATHAN, JUSTIN));

        //Perfect match truth booths

        // Add any people that aren't in a proven couple to available people
        Set<Person> availablePeople = stream(values())
                .filter(p -> perfectMatches.stream()
                        .noneMatch(c -> c.getOne() != p || c.getTwo() != p))
                .collect(toSet());
        Stopwatch stopwatch = createStarted();
        recurseCouples(availablePeople, newArrayList(perfectMatches));
        stopwatch.stop();
        System.out.println("Time to recurse: " + stopwatch.elapsed(SECONDS) + " seconds.");
        System.out.println("Number of winning combinations: " + winningCouples.size());
        if (winningCouples.size() < 20) {
            for (Set<Couple> couples : winningCouples) {
                System.out.println("Winning combination: " + join(couples, ", "));
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
                for (Episode episode : episodes) {
                    Set<Couple> intersect = newHashSet(currentResults);
                    intersect.retainAll(episode.getCouples());
                    if (intersect.size() != episode.getNumBeams()) {
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
