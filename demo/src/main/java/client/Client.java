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

    static HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {
        registrerUser();
        addRoom();

        getAll(Data.api_userPath);
        getAll(Data.api_roomPath);
    }

    private static void addRoom() throws IOException, InterruptedException {
        System.out.println("Add a room by typing /add <roomname>");

        String[] array = readFromTerminal().split(" ");
        postRequest(Data.api_roomPath, new Room("12345", array[1], 10));
    }

    private static void registrerUser() throws IOException, InterruptedException {
        System.out.println("Please registrer by typing /reg <your name>");

        String[] array = readFromTerminal().split(" ");
        // postRequest(Data.api_userPath, new User(1, array[1], Data.availableRooms.get(0)));
    }

    private static String readFromTerminal() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String regString = bf.readLine();
        return regString;
    }

    private static void postRequest(String path, Object object) throws IOException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(path))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

//        // print status code
//        System.out.println("Status code: " + response.statusCode());
//
//        // print response body
//        System.out.println("Response body: " + response.body());

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
