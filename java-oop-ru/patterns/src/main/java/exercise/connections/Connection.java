package exercise.connections;

public interface Connection {
    // BEGIN
    void connect();
    void write(String str);
    void disconnect();
    String getCurrentState();
    // END
}
