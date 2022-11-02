package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection{

    private TcpConnection connection;

    public Disconnected(TcpConnection tcpConnection) {
        this.connection = tcpConnection;
    }

    @Override
    public void connect() {
        connection.setState(new Connected(connection));
    }

    @Override
    public void write(String str) {
        System.out.println("Error: Try to write to disconnected connection");
    }

    @Override
    public void disconnect() {
        System.out.println("Error: Try to disconnect when connection disconnected");
    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }
}
// END
