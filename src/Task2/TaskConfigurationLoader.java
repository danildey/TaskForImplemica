package Task2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/*
 * This class serving for load file with source data and for perform the tests.
 */
public class TaskConfigurationLoader {

    private City[] cities = null;
    private List<Integer[]> sourceDestinationList = new ArrayList<>();
    private List<String> citiesNames = new ArrayList<>();
    private int numberOfCities;
    private int numberOfTest;
    private int numberOfNeighbours;

    public void loadConfigurationFromFile(String fileName)
            throws ConfigurationException {
        try (Scanner in = new Scanner(new FileReader(fileName))) {
            readNumberOfTest(in);
            readNumberOfCities(in);
            readCitiesAndTheirNeighbours(in);
            readSourceAndDestination(in);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readNumberOfTest(Scanner scanner)
            throws ConfigurationException {
        numberOfTest = scanner.nextInt();
        System.out.println(numberOfTest);
        if (numberOfTest > 10 || numberOfTest <= 0)
            throw new ConfigurationException("Incorrect size of tests.");
    }

    private void readNumberOfCities(Scanner scanner)
            throws ConfigurationException {
        numberOfCities = scanner.nextInt();
        System.out.println(numberOfCities);
        if (numberOfCities > 10000 || numberOfCities < 0)
            throw new ConfigurationException("Incorrect size of cities.");
    }

    private void readCitiesAndTheirNeighbours(Scanner scanner)
            throws ConfigurationException {
        cities = new City[numberOfCities];
        for (int i = 0; i < numberOfCities; i++) {
            City c;
            String cityName = scanner.next();
            System.out.println(cityName);

            if (cityName.length() > 10 || cityName.length() < 0)
                throw new ConfigurationException("Incorrect size of cities.");
            citiesNames.add(cityName);

            numberOfNeighbours = scanner.nextInt();
            System.out.println(numberOfNeighbours);
            c = new City(cityName.toLowerCase(), i + 1);

            for (int j = 0; j < numberOfNeighbours; j++) {
                int to = scanner.nextInt();
                int cost = scanner.nextInt();
                System.out.println(to + " " + cost);
                c.addNewNeighbour(new Neighbour(i, to - 1, cost));
            }

            cities[i] = c;
        }
    }

    private void readSourceAndDestination(Scanner scanner) {
        int sourceDest = scanner.nextInt();
        for (int i = 0; i < sourceDest; i++) {
            String source = scanner.next(); // source
            String destination = scanner.next(); // destination
            System.out.println(source + " " + destination);

            setSourceDestinationList(new Integer[]{
                    citiesNames.indexOf(source),
                    citiesNames.indexOf(destination)
            });
        }
    }

    public void setSourceDestinationList(Integer[] sourceDestination) {
        this.sourceDestinationList.add(sourceDestination);
    }

    public int getNumberOfCities() {
        return numberOfCities;
    }

    public int getNumberOfNeighbours() {
        return numberOfNeighbours;
    }

    public List<String> getCitiesNames() {
        if (citiesNames.isEmpty()) {
            return Collections.emptyList();
        } else {
        return citiesNames;
        }
    }

    public List<Integer[]> getSourceDestinationList() {
        if (sourceDestinationList.isEmpty()) {
            return Collections.emptyList();
        } else {
        return sourceDestinationList;
        }
    }

    public City[] getCities() {
        return copyOfCities();
    }

    private City[] copyOfCities() {
        if (cities == null)
            throw new NullPointerException("The cities array is null");
        if (cities.length == 0)
            throw new IllegalArgumentException("The lenght of cities array is 0");
        City[] copy = new City[cities.length];
        for (int i = 0; i < cities.length; i++)
            try {
                copy[i] = (City) cities[i].clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        return copy;
    }


}
