package pl.sierakowski.lsn;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Task3 {

    private static final String ERROR_NO_PATH_DATA_FILE = "Please provide the path to the data file";
    private static final String ERROR_INCORRECT_PATH_DATA_FILE = "Privided path to data file are incorrect";
    private static final String ERROR_NO_ACCESS_DATA_FILE = "Can not read file";

    public static void main(String[] args) {
        /*
         * The program should return only the number of disjoint graphs.
         * No visualization of these graphs is required. All we need to do is specify
         * the nodes of the graph and remember which graph they belong to.
         * When two graphs are connected, all we need to do is change the
         * identifier of the second graph to that of the first.
         *
         * It does not follow from the text of the task that the numbers will be entered
         * from the smallest to the largest
         */
        if(args.length != 1 || args[0].length() == 0) {
            System.out.println(ERROR_NO_PATH_DATA_FILE);
            return;
        }
        File dataFile = new File(args[0]);
        if( ! dataFile.exists() ) {
            System.out.println(ERROR_INCORRECT_PATH_DATA_FILE+": " + args[0]);
            return;
        }
        if( ! dataFile.canRead() ) {
            System.out.println(ERROR_NO_ACCESS_DATA_FILE+": " + args[0]);
            return;
        }

        final Pattern lineFilter = Pattern.compile("-?\\d+ -?\\d+");
        final List<Set<Integer>> graphs = new ArrayList<>();

        try(Stream<String> stream = Files.lines(dataFile.toPath())) {
            stream
                .filter(line -> lineFilter.matcher(line).matches())
                .forEach(line -> {
                    String[] numbers = line.split(" ");
                    int number1 = Integer.parseInt(numbers[0]);
                    int number2 = Integer.parseInt(numbers[1]);
                    int graphOfNumber1 = -1;
                    int graphOfNumber2 = -1;
                    for (int index = 0; index < graphs.size(); index++) {
                        Set<Integer> graph = graphs.get(index);
                        if (graphOfNumber1 == -1 && graph.contains(number1)) {
                            graphOfNumber1 = index;
                        }
                        if (graphOfNumber2 == -1 && graph.contains(number2)) {
                            graphOfNumber2 = index;
                        }
                        if (graphOfNumber1 != -1 && graphOfNumber2 != -1) {
                            break;
                        }
                    }
                    if(graphOfNumber1 == -1 && graphOfNumber2 == -1) {
                        graphs.add(new HashSet<>(Arrays.asList(number1,number2)));
                    } else if(graphOfNumber1 == -1) {
                        graphs.get(graphOfNumber2).add(number1);
                    } else if(graphOfNumber2 == -1) {
                        graphs.get(graphOfNumber1).add(number2);
                    } else {
                        graphs.get(graphOfNumber1).addAll(graphs.remove(graphOfNumber2));
                    }
                });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(graphs.size());
    }

}
