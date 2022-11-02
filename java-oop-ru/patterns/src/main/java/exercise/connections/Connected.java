package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection{

    private TcpConnection tcpConnection;

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void connect() {
        System.out.println("Error: Try to connect when connection already established");
    }

    @Override
    public void write(String str) {
        System.out.println("Value: " + str + " is added to buffer");
    }

    @Override
    public void disconnect() {
        TcpConnection tcpConnection = this.tcpConnection;
        tcpConnection.setState(new Disconnected(tcpConnection));
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }
}
// END
