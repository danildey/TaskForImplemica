package Task2;

public class Main {


        public static void main(String[] args) {

            TaskConfigurationLoader loader = new TaskConfigurationLoader();
            loader.loadConfigurationFromFile("D:\\java\\Test\\src\\Task2\\input.txt");
            Graph g = new Graph(loader);


        }

    }
