package pl.sierakowski.lsn;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task1 {

    public static void main(String[] args) {
        // Sorted list of unique arguments (de facto SET of arguments with order)
        final List<Integer> argsSet;
        try {
            argsSet = Stream.of(args).map(Integer::parseInt).distinct().sorted().collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("Illegal input data");
            return;
        }
        // output
        System.out.println(argsSet.isEmpty() ? "-" : argsSet.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        System.out.println("count: " + args.length);
        System.out.println("distinct: " + argsSet.size());
        System.out.println("min: " + (argsSet.isEmpty() ? "-" : argsSet.get(0)));
        System.out.println("max: " + (argsSet.isEmpty() ? "-" : argsSet.get(argsSet.size()-1)));
    }

}
