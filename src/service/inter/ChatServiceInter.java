package service.inter;

import model.dao.Client;

import java.io.IOException;
import java.util.ArrayDeque;

public interface ChatServiceInter {

    void publicChat(Client currentClient) throws IOException;
    void privateChat(Client currentClient) throws IOException;
    long getUserIdFromPrivateChat();
    void broadcast(String message);
    void rename(Client currentClient) throws IOException;

    ArrayDeque<String> createOrGetChat(long id);
    boolean isBack(String messsage);


}
