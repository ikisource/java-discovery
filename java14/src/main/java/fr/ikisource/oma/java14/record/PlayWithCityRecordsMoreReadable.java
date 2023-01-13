package fr.ikisource.oma.java14.record;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
public class PlayWithCityRecordsMoreReadable {
    public static void main(String[] args) throws IOException {
        var main = new PlayWithCityRecordsMoreReadable();
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
        List<State> states = cities.stream()
                .map(City::state)
                .distinct()
                .toList();
        System.out.println("states.size = " + states.size());

        // Population by state
        Map<State, Population> populationByState = cities.stream()
                .collect(
                        Collectors.groupingBy(
                                City::state,
                                Population.counting()
                        )
                );
        System.out.println("populationByState = " + populationByState);

        // Most populated state
        PopulatedState mostPopulationByState = populationByState.entrySet().stream()
                .map(PopulatedState::new)
                .max(PopulatedState.maxByPopulation())
                .orElseThrow();
        System.out.println("mostPopulationByState = " + mostPopulationByState);
    }
    record City(int id, String name, State state, Population population, double surface) {
        public static City of(String line) {
            var elements = line.split(";");
            var id = Integer.parseInt(elements[0]);
            var name = elements[1];
            var state = new State(elements[2]);
            var population = new Population(Integer.parseInt(elements[3].replaceAll(" ", "")));
            var surface = Double.parseDouble(elements[4].replaceAll(" ", "").replaceAll(",", "."));
            return new City(id, name, state, population, surface);
        }
    }
    record State(String name) {
    }
    record Population(int amount) implements Comparable<Population> {
        static Collector<City, ?, Population> counting() {
            return Collectors.collectingAndThen(
                    Collectors.summingInt(city -> city.population().amount()),
                    Population::new
            );
        }
        @Override
        public int compareTo(Population other) {
            return Integer.compare(this.amount, other.amount);
        }
    }
    record PopulatedState(State state, Population population) {
        PopulatedState(Map.Entry<State, Population> entry) {
            this(entry.getKey(), entry.getValue());
        }
        public static Comparator<? super PopulatedState> maxByPopulation() {
            return Comparator.comparing(PopulatedState::population);
        }
    }
}
