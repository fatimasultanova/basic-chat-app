package service.inter;

import model.dao.Client;
import model.enums.Chat;
import service.ChatService;

public interface ClientServiceInter extends Runnable{

    void sendMessage(String message);
    Client getClient();
    ChatService getChatService();
    Chat getCurrentChat();

    void shutDown();








}
