package pl.sierakowski.lsn;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Task2 {

    private final static int SUM = 13;
    private final static String LINE_SEPARATOR = System.lineSeparator();

    private static class Pair implements Comparable<Pair> {
        private final int number1;
        private int repeat;
        public Pair(int number1) {
            this.number1 = number1;
            this.repeat = 1;
        }
        @Override
        public String toString() {
            final String string = number1 + " " + (SUM - number1);
            if(repeat == 1) {
                return string;
            }
            final StringBuilder stringBuilder = new StringBuilder(string);
            for(int i=1; i<repeat; i++) {
                stringBuilder.append(LINE_SEPARATOR).append(string);
            }
            return stringBuilder.toString();
        }
        @Override
        public int compareTo(Pair o) {
            return Integer.compare(number1,o.number1);
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return number1 == pair.number1;
        }
        @Override
        public int hashCode() {
            return number1;
        }
        public void repeat() {
            this.repeat++;
        }
    }

    public static void main(String[] args) {
        // numbers without pair
        final Set<Integer> oddNumbers = new HashSet<>();
        // list of pairs
        final List<Pair> pairs = new LinkedList<>();
        for(String arg: args) {
            int number;
            try {
                number = Integer.parseInt(arg);
            } catch (NumberFormatException e) {
                System.out.println("Illegal input data: " + arg);
                continue;
            }
            int pairNumber = SUM - number;
            if(oddNumbers.remove(pairNumber)) {
                Pair pair = pairNumber > number
                    ? new Pair(number)
                    : new Pair(pairNumber);
                int index = pairs.indexOf(pair);
                if (index < 0) {
                    pairs.add(pair);
                } else {
                    pairs.get(index).repeat();
                }
                continue;
            }
            oddNumbers.add(number);
        }
        System.out.println(
            pairs.isEmpty()
                ? "-"
                : pairs.stream()
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining(LINE_SEPARATOR)));
        if( ! oddNumbers.isEmpty() ) {
            System.out.println(
                "Warrning: Number" + (oddNumbers.size() > 1 ? "s" : "") +
                " without pair: " +
                oddNumbers.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "))
            );
        }
    }

}
