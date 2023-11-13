package helper;

import model.dao.Client;

import java.io.IOException;
import java.util.List;

public class Menu {

    public static String registerClient(Client client) throws IOException {
        return InputHelper.getClientMessage("Enter username: ",client);
    }

    public static String clientMenu (Client client) throws IOException {
        String menu = "[1]. Broadcast\n" +
                "[2]. Contacts\n" +
                "[3]. Rename\n" +
                "[4]. Quit server\n" +
                "\nChoose option: ";

        return InputHelper.getClientMessage(menu,client);
    }

    public static String getClientIdAllContact(Client currentClient , List<Client> clients) throws IOException {
        clients.forEach(client -> {
            try {
                currentClient.getDos().writeUTF(client.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return InputHelper.getClientMessage("Choose user: " , currentClient);
    }


}
