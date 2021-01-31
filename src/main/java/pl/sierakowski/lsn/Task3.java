package pl.sierakowski.lsn;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Task3 {

    private static final String ERROR_PATH_DATA_FILE = "Please provide the path to the data file";

    private static int graphGenerator = 0;

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
            System.out.println(ERROR_PATH_DATA_FILE);
            return;
        }
        File dataFile = new File(args[0]);
        if( ! dataFile.canRead() ) {
            System.out.println(ERROR_PATH_DATA_FILE);
            return;
        }
        final Pattern lineFilter = Pattern.compile("-?\\d+ -?\\d+");
        final Map<Integer,Integer> graphs = new HashMap<>();
        try(Stream<String> stream = Files.lines(dataFile.toPath())) {
            stream
                .filter(line -> lineFilter.matcher(line).matches())
                .forEach(line -> {
                    String[] numbers = line.split(" ");
                    int number1 = Integer.parseInt(numbers[0]);
                    int number2 = Integer.parseInt(numbers[1]);
                    if( graphs.containsKey(number1) ) {
                        int masterGraph = graphs.get(number1);
                        if(graphs.containsKey(number2)) {
                            // change graph ID in all nodes of graph with number2
                            int slaveGraph = graphs.get(number2);
                            for (Map.Entry<Integer, Integer> entry : graphs.entrySet()) {
                                if (entry.getValue().equals(slaveGraph)) {
                                    entry.setValue(masterGraph);
                                }
                            }
                        } else {
                            graphs.put(number2,masterGraph);
                        }
                    } else {
                        if( graphs.containsKey(number2) ) {
                            graphs.put(number1,graphs.get(number2));
                        } else {
                            Integer id = graphGenerator++;
                            graphs.put(number1,id);
                            graphs.put(number2,id);
                        }
                    }
                });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if(graphs.isEmpty()) {
            System.out.println("-");
            return;
        }
        System.out.println(graphs.values().stream().distinct().count());
    }

}
