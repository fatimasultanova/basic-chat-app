package service;

import helper.InputHelper;
import model.dao.Client;
import model.dao.PrivateChat;
import model.enums.Chat;
import service.inter.ChatServiceInter;
import service.inter.ClientServiceInter;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class ChatService implements ChatServiceInter {

    private final Map<Long,ArrayDeque<String>> chats = new HashMap<>();
    private final PrivateChat privateChat;

    public ChatService() {
        privateChat = new PrivateChat(Chat.PRIVATE_CHAT);
    }

    @Override
    public void publicChat(Client currentClient) throws IOException {
        currentClient.getDos().writeUTF("");

    }

    @Override
    public void privateChat(Client currentClient) throws IOException {

    }

    @Override
    public long getUserIdFromPrivateChat() {
        return privateChat.getTargetClientId();

    }

    @Override
    public void broadcast(String message) {

    }

    @Override
    public void rename(Client currentClient) throws IOException {
        String newUsername = InputHelper.getClientMessage("Enter new name: ", currentClient);
        if (isBack(newUsername)) {
            return;
        }
        ////broadcast();
        currentClient.setUsername(newUsername);

    }

    @Override
    public ArrayDeque<String> createOrGetChat(long id) {
        return null;
    }

    @Override
    public boolean isBack(String messsage) {
       return messsage.equalsIgnoreCase("/back");
    }

    private void loadMessages(Client currentClient , ArrayDeque<String> chat){
       //// ClientServiceInter currentClientService = g
    }

   // private ClientService getClientServiceById(long clientId) {
      //  ClientServiceInter clientService =
    //}

}
