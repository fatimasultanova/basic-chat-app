package service;

import helper.InputHelper;
import helper.Menu;
import model.dao.Client;
import model.enums.Chat;
import service.inter.ClientServiceInter;

import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;

import static service.ServerService.*;

public class ClientService implements ClientServiceInter {

    private final Client client;
    private final ChatService chatService;
    private Chat currentChat;

    public ClientService(Socket clientSocket) {
        this.client = new Client(clientSocket);
        this.chatService = new ChatService();
        this.currentChat = Chat.NONE;
    }


    @Override
    public void run() {
        introduction();

        while (!isShutDown) {
            try {
                int option = Integer.parseInt(Menu.clientMenu(client));

                switch (option) {
                    case 1:
                        currentChat = Chat.PUBLIC_CHAT;
                        chatService.publicChat(client);
                        break;
                    case 2:
                        currentChat = Chat.PRIVATE_CHAT;
                        chatService.privateChat(client);
                        break;
                    case 3:
                        chatService.rename(client);
                        break;
                    case 4:
                        client.shutDown();
                        clientsServices.get().remove(client.getId());
                        chatService.broadcast(client.getUsername() + " left the chat!\n");
                        break;
                }
                currentChat = Chat.NONE;

            } catch (IOException e) {
                client.shutDown();
            } catch (InputMismatchException | NumberFormatException ex) {
                System.out.println("Invalid option!");
            }
        }
    }

    @Override
    public void sendMessage(String message) {
        try {
            client.getDos().writeUTF(message);
        } catch (IOException e) {
            System.err.print("Failed to send message, because connection is weak, please try again!");
        }
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public ChatService getChatService() {
        return chatService;
    }

    @Override
    public Chat getCurrentChat() {
        return currentChat;
    }

    @Override
    public void shutDown() {
        client.shutDown();
    }

    private void introduction(){
        try {
            client.getDos().writeUTF("---------- B-107 ----------");
            String username = Menu.registerClient(client);
            client.setUsername(username);

            System.out.printf("%s connected! - %s\n", client.getUsername(), client.getHostAddressAndPort());

            chatService.broadcast(client.getUsername() + " joined the chat!");

        } catch (IOException e) {
            shutDown();
        }
    }
}
