import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SeaBattle {
    public static void main(String[] args) {
        System.out.println("1. Connect to other player");
        System.out.println("2. Host");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()) {
            case 2: {

                try {
                    ServerSocket serverSocket = new ServerSocket(9999);
                    Socket clientSocket = serverSocket.accept();
                    //BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
