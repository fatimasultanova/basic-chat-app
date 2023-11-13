import service.ServerService;
import service.inter.ServerServiceInter;

public class Main {
    public static void main(String[] args) {
        ServerServiceInter serverService = new ServerService();
        serverService.run();

    }
}