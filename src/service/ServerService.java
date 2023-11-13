package service;

import static model.constants.ServerConstants.*;

import service.inter.ClientServiceInter;
import service.inter.ServerServiceInter;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class ServerService implements ServerServiceInter {

    public static AtomicReference<Map<Long,ClientService>> clientsServices = new AtomicReference<>();
    public static ServerSocket serverSocket;
    public static boolean isShutDown;
    private ExecutorService threadPool;

    public ServerService() {
        clientsServices.set(new HashMap<>());
        isShutDown = false;
        threadPool = Executors.newCachedThreadPool();
    }

    @Override
    public void shutdown() {
       try {
           isShutDown = true;
           shutdownAllClients();
           if (!serverSocket.isClosed()){
               try {
                   serverSocket.close();
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
           }

       }catch (Exception ex){
           //ignore
       }
    }


    private void shutdownAllClients() {
        clientsServices.get().entrySet()
                .parallelStream()
                .filter(Objects::nonNull)
                .forEach(value -> value.getValue().shutDown());
    }

    @Override
    public void run() {
        System.out.println("|------------ B-107 CHAT ------------|");
        try {
            serverSocket = new ServerSocket(Port);
            while (!isShutDown) {
                Socket clientSocket = serverSocket.accept();
                ClientService clientService = new ClientService(clientSocket);
                clientsServices.get().put(clientService.getClient().getId(), clientService);
                threadPool.execute(clientService);
            }
        } catch (IOException e) {
           shutdown();
        }
    }
}
