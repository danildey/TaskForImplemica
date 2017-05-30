package Task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
* This Class is Node of Graph, with edges to another Nodes(Cities)
*/
public class City implements Cloneable {
    private String cityName;
    private int id;
    private boolean isVisited;
    private int FareBetweenCities = Integer.MAX_VALUE;
    private List<Neighbour> neighbours = new ArrayList<>();

    //    Deep clone.
    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            City clonedCity = (City) super.clone();
            ArrayList<Neighbour> clonedNeighbours = new ArrayList<>();
            for (Neighbour n : neighbours) {
                clonedNeighbours.add((Neighbour) n.clone());
            }
            clonedCity.setNeighbours(clonedNeighbours);
            return clonedCity;

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }

    }

    public int getFareBetweenCities() {
        return FareBetweenCities;
    }

    public void setFareBetweenCities(int fareBetweenCities) {
        FareBetweenCities = fareBetweenCities;
    }

    public City(String name, int id) {
        setName(name);
        setId(id);
    }

    public City(int id, String cityName, ArrayList<Neighbour> neighbours) {
        this.id = id;
        this.cityName = cityName;
        this.neighbours = neighbours;
    }

    public List<Neighbour> getNeighbours() {
        if (neighbours.isEmpty()) {
            return Collections.emptyList();
        } else {
            return neighbours;
        }
    }

    public void setNeighbours(ArrayList<Neighbour> neighbours) {
        this.neighbours = neighbours;
    }

    public void addNewNeighbour(Neighbour n) {
        this.neighbours.add(n);
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        this.isVisited = visited;
    }

    @Override
    public String toString() {
        return getName() + " id = " + getId();
    }

    public String getName() {
        return cityName;
    }

    public void setName(String cityName) {
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 10000) throw new IllegalArgumentException("Wrong id.");
        this.id = id;
    }
}
