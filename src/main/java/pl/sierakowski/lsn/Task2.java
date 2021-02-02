package pl.sierakowski.lsn;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2 {

    private final static int SUM = 13;

    public static void main(String[] args) {

        // numbers without pair
        final Set<Integer> oddNumbers = new HashSet<>();
        System.out.println(
            Stream.of(args).map(Integer::parseInt)
                .filter(number -> {
                    int numberTwo = SUM - number;
                    if(oddNumbers.remove(numberTwo)) {
                        return true;
                    }
                    oddNumbers.add(number);
                    return false;
                })
                .sorted()
                .map(number -> number + " " + (SUM - number))
                .collect(Collectors.joining(System.lineSeparator()))
        );

    }

}
