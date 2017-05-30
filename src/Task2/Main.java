package Task2;

public class Main {


    public static void main(String[] args) {

        TaskConfigurationLoader loader = new TaskConfigurationLoader();
        try {
            loader.loadConfigurationFromFile("src\\Task2\\input.txt");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        Graph g = new Graph(loader);


    }

}

