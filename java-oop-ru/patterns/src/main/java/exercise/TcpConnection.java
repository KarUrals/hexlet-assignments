package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.ArrayList;
import java.util.List;

// BEGIN
public class TcpConnection {
    private final String ipAddress;
    private final int port;
    private Connection currentState;
    private final List<String> buffer = new ArrayList<>();

    public TcpConnection(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.currentState = new Disconnected(this);
    }

    public Connection getConnection() {
        return currentState;
    }

    public void setState(Connection newState) {
        this.currentState = newState;
    }
    public void connect() {
        this.getConnection().connect();
    }

    public void write(String str) {
        this.getConnection().write(str);
    }

    public void disconnect() {
        this.getConnection().disconnect();
    }

    public String getCurrentState() {
        return currentState.getCurrentState();
    }

    public void addToBuffer(String data) {
        buffer.add(data);
    }
}
// END
