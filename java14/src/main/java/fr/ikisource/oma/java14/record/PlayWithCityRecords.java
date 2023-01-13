package fr.ikisource.oma.java14.record;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayWithCityRecords {

    public static void main(String[] args) throws IOException {
        var main = new PlayWithCityRecords();
        main.run();
    }

    private List<City> readCities(Path path) throws IOException {
        try (var lines = Files.lines(path)) {
            return lines.skip(2)
                    .map(City::of)
                    .toList();
        }
    }

    private void run() throws IOException {
        var path = Path.of("files/cities.csv");

        var cities = readCities(path);
        System.out.println("cities.size() = " + cities.size());

        // read all states
        List<String> states = cities.stream()
                .map(City::state)
                .distinct()
                .toList();
        System.out.println("states.size = " + states.size());

        // Population by state
        Map<String, Integer> populationByState = cities.stream()
                .collect(
                        Collectors.groupingBy(
                                City::state,
                                Collectors.summingInt(City::population)));
        System.out.println("populationByState = " + populationByState);

        // Most populated state
        Map.Entry<String, Integer> mostPopulationByState = populationByState.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow();
        System.out.println("mostPopulationByState = " + mostPopulationByState);
    }

    record City(int id, String name, String state, int population, double surface) {

        public static City of(String line) {
            var elements = line.split(";");
            var id = Integer.parseInt(elements[0]);
            var name = elements[1];
            var state = elements[2];
            var population = Integer.parseInt(elements[3].replaceAll(" ", ""));
            var surface = Double.parseDouble(elements[4].replaceAll(" ", "").replaceAll(",", "."));
            return new City(id, name, state, population, surface);
        }
    }
}
