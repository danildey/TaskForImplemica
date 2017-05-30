package Task2;

import java.util.List;

/*
 * Class realization of Dijkstra algorithm.
 * Dijkstra's algorithm is an algorithm for finding the shortest
 * paths between nodes in a graph, which represent minimum cost between cities in our case.
 */
public class Graph {
    private City[] cities = null;
    private TaskConfigurationLoader taskLoader;


    public Graph(TaskConfigurationLoader loader) {
        this.taskLoader = loader;
        cities = loader.getCities();
        for (Integer[] c : loader.getSourceDestinationList()) {
            calculateTheCheapestWay(c[0], c[1]);
        }

    }

    public void calculateTheCheapestWay(int fromCityId, int toCityId) {

        // City 0 as source
        this.cities[fromCityId].setFareBetweenCities(0);
        int nextCity = fromCityId;

        // visit every cities
        for (City city : this.cities) {
            // loop around the Neighbours of current city
            List<Neighbour> currentNeighbour = this.cities[nextCity].getNeighbours();

            for (Neighbour neighbour : currentNeighbour) {
                int neighbourIndex = neighbour.getNeighbourCityId(nextCity);

                // only if not visited
                if (!this.cities[neighbourIndex].isVisited()) {
                    int tentative = this.cities[nextCity].getFareBetweenCities()
                            + neighbour.getPrice();

                    if (tentative < cities[neighbourIndex].getFareBetweenCities()) {
                        cities[neighbourIndex].setFareBetweenCities(tentative);
                    }
                }
            }

            // all neighbours checked so city visited
            cities[nextCity].setVisited(true);
            nextCity = FindNextCity();

        }

        printResult(fromCityId, toCityId);
        reloadCities();
    }

    // next city must be with shortest distance
    private int FindNextCity() {
        int storedCityId = 0;
        int storedPrices = Integer.MAX_VALUE;

        for (int i = 0; i < this.cities.length; i++) {
            int currentPrices = this.cities[i].getFareBetweenCities();

            if (!this.cities[i].isVisited() && currentPrices < storedPrices) {
                storedPrices = currentPrices;
                storedCityId = i;
            }
        }

        return storedCityId;
    }

    public void printResult(int from, int to) {
        String output = "" + cities[to].getFareBetweenCities();
        System.out.println(output);
    }

    public void reloadCities() {
        this.cities = taskLoader.getCities();
    }

}




