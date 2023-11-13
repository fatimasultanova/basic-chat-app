package model.dao;

import static model.sequence.ClientSequence.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private final long id;
    private String username;

    private DataOutputStream dos;
    private DataInputStream dis;
    private final Socket socket;

    private final String hostAddressAndPort;

    public Client(Socket clientSocket) {
        this.id = nextVal();
        this.socket = clientSocket;
        try {
            setDis(new DataInputStream(getSocket().getInputStream()));
            setDos(new DataOutputStream(getSocket().getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.hostAddressAndPort = clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort() ;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getHostAddressAndPort() {
        return hostAddressAndPort;
    }

    public void shutDown(){
        try {
            getDis().close();
            getDis().close();
            if (!getSocket().isClosed()){
                getSocket().close();
            }
        } catch (IOException e) {
           //ignore
        }
    }

    @Override
    public String toString() {
        return getId() + ". "+ username;
    }
}
