package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection {
    private final String ipAddress;
    private final int port;
    private Connection currentState;

    public TcpConnection(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.currentState = new Disconnected(this);
    }

    public String getCurrentState() {
        return currentState.getCurrentState();
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
}
// END
