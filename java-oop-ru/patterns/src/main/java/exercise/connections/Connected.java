package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection{

    private TcpConnection connection;

    public Connected(TcpConnection tcpConnection) {
        this.connection = tcpConnection;
    }

    @Override
    public void connect() {
        System.out.println("Error: Try to connect when connection already established");
    }

    @Override
    public void write(String str) {
        connection.addToBuffer(str);
        System.out.println("Value: " + str + " is added to buffer");
    }

    @Override
    public void disconnect() {
        connection.setState(new Disconnected(connection));
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }
}
// END
