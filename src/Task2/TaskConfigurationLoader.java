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

    public void loadConfigurationFromFile(String fileName) throws ConfigurationException {
        try (Scanner in = new Scanner(new FileReader(fileName))) {
            // First reads number of tests. If correct continue.
            numberOfTest = in.nextInt();
            System.out.println(numberOfTest);
            if (numberOfTest > 10 || numberOfTest <= 0)
                throw new ConfigurationException("Incorrect size of tests.");

            // Reads number of cities.
            numberOfCities = in.nextInt();
            System.out.println(numberOfCities);
            if (numberOfCities > 10000 || numberOfCities < 0)
                throw new ConfigurationException("Incorrect size of cities.");

            cities = new City[numberOfCities];


            // Reads cities and their neighbours.
            for (int i = 0; i < numberOfCities; i++) {
                City c;
                String cityName = in.next();
                System.out.println(cityName);

                if (cityName.length() > 10 || cityName.length() < 0)
                    throw new ConfigurationException("Incorrect size of cities.");
                citiesNames.add(cityName);

                numberOfNeighbours = in.nextInt();
                System.out.println(numberOfNeighbours);
                c = new City(cityName.toLowerCase(), i + 1);

                for (int j = 0; j < numberOfNeighbours; j++) {
                    int to = in.nextInt();
                    int cost = in.nextInt();
                    System.out.println(to + " " + cost);
                    c.addNewNeighbour(new Neighbour(i, to - 1, cost));
                }

                cities[i] = c;
            }
            // Reads source and destination
            int sourceDest = in.nextInt();
            for (int i = 0; i < sourceDest; i++) {
                String source = in.next(); // source
                String destination = in.next(); // destination
                System.out.println(source + " " + destination);
                setSourceDestinationList(new Integer[]{
                        citiesNames.indexOf(source),
                        citiesNames.indexOf(destination)
                });


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


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

    public void setSourceDestinationList(Integer[] sourceDestination) {
        this.sourceDestinationList.add(sourceDestination);
    }

    public City[] getCities() {
        return copyOfCities();
    }

    private City[] copyOfCities() {
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
