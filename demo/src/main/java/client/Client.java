package client;

import com.example.restapi.Data;
import com.example.restapi.room.Room;
import com.example.restapi.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Client <T> {

    public static final String path1 = "http://localhost:8081/api/room/1/messages";
    public static final String path2 = "http://localhost:8081/api/room/2/messages";
    public static final String path3 = "http://localhost:8081/api/users";
    public static final String path4 = "http://localhost:8081/api/rooms";
    public static final String path5 = "http://localhost:8081/api/room/1/users";
    public static final String path6 = "http://localhost:8081/api/room/2/users";
    public static final String path7 = "http://localhost:8081/api/room/3/users";

    static HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {
        User user = registrerUser();
        connectToRoom(user);

        getAll(path1);
        getAll(path2);
        getAll(path3);
        getAll(path4);
    }

    private static void connectToRoom(User newUser) throws IOException, InterruptedException {
        System.out.println("Connect to a room by typing /connect <roomname>");
        System.out.println("Supported rooms: [1, 2, 3]");

        String[] array = readFromTerminal().split(" ");
        if (array[0].equals("1")) {
            postRequest(path5, newUser);
        } else if (array[1].equals("2")) {
            postRequest(path6, newUser);
        } else postRequest(path7, newUser);
    }

    private static User registrerUser() throws IOException, InterruptedException {
        System.out.println("Please registrer by typing /reg <your name>");

        String[] array = readFromTerminal().split(" ");

        User user = new User(1, array[1]);
        postRequest(path3, user);

        return user;
    }

    private static String readFromTerminal() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String regString = bf.readLine();
        return regString;
    }

    private static void postRequest(String path, Object object) throws IOException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);

        System.out.println(object);
        System.out.println(json);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(path))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());

    }

    private static void getAll(String path) throws IOException {
        URL url = new URL(path);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        String inline = "";
        Scanner scanner = new Scanner(url.openStream());

        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }

        scanner.close();
        System.out.println(inline);
    }
}
