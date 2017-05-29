package Task2;

import java.util.ArrayList;

/*
 * Class realization of Dijkstra algorithm.
 */
public class Graph {
    private City[] cities=null;
    private TaskConfigurationLoader taskLoader;


    public Graph(TaskConfigurationLoader loader) {
        this.taskLoader = loader;
        cities=loader.getCities();
        for(Integer[] c: loader.getSourceDestinationList()){
            calculateTheCheapestWay(c[0],c[1]);
        }

    }

    public void calculateTheCheapestWay(int fromCityId,int toCityId) {

        // City 0 as source
        this.cities[fromCityId].setFareBetweenCities(0);
        int nextCity = fromCityId;

        // visit every cities
        for (int i = 0; i < this.cities.length; i++) {
            // loop around the Neighbours of current city
            ArrayList<Neighbour> currentNeighbour = this.cities[nextCity].getNeighbours();

            for (int joinedNeighbour = 0; joinedNeighbour < currentNeighbour.size(); joinedNeighbour++) {
                int neighbourIndex = currentNeighbour.get(joinedNeighbour).getNeighbourCityId(nextCity);

                // only if not visited
                if (!this.cities[neighbourIndex].isVisited()) {
                    int tentative = this.cities[nextCity].getFareBetweenCities()
                            + currentNeighbour.get(joinedNeighbour).getPrice();

                    if (tentative < cities[neighbourIndex].getFareBetweenCities()) {
                        cities[neighbourIndex].setFareBetweenCities(tentative);
                    }
                }
            }

            // all neighbours checked so city visited
            cities[nextCity].setVisited(true);

            // next city must be with shortest distance
            nextCity = FindNextCity();

        }

        printResult(fromCityId,toCityId);
        reloadCities();
    }

        private int FindNextCity(){
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
           // display result
        public void printResult(int from,int to) {
            String output = ""+cities[to].getFareBetweenCities() ;
            System.out.println(output);
        }
        public void reloadCities() {
            this.cities=taskLoader.getCities();
    }

}




