package helper;

import model.dao.Client;

import java.io.IOException;
import java.util.Scanner;

public class InputHelper {

    public static String getClientMessage(String message , Client client) throws IOException {
        if (message != null) {
            client.getDos().writeUTF(message);
        }
        return client.getDis().readUTF();
    }

    public static String getServerMessage(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(title);

        return scanner.nextLine();
    }


}
