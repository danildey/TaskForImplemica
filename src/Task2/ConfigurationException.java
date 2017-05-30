package Task2;

public class ConfigurationException extends Exception {

    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException() {
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
