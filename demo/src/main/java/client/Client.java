package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Client <T> {

    BotResponse botResponse = new BotResponse();

    Integer userID;
    String name;
    static String currentRoom;
    static String currentMessagePath;
    static String currentRoomPath;
    static Client client;
    static String path = "http://localhost:8081/api/";

    public Client(Integer userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    static String[] rooms = {
            "room1",
            "room2",
            "room3"
    };

    static String[] roomMessagePaths = {
            "http://localhost:8081/api/room/room1/messages",
            "http://localhost:8081/api/room/room2/messages",
            "http://localhost:8081/api/room/room3/messages",
    };

    static String[] roomUserPaths = {
            "http://localhost:8081/api/room/room1/users",
            "http://localhost:8081/api/room/room2/users",
            "http://localhost:8081/api/room/room3/users",
    };

    static HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {

        runBot();

        while (true) {
            String keyboard = readFromTerminal();

            if (keyboard.startsWith("/join")) {
                joinRoom(keyboard);
            }
            else if (keyboard.startsWith("/rooms")) {
                viewRooms(keyboard);
            }
        }

    }

    private static void runBot() throws IOException, InterruptedException {

        // Registrer

        String userName = registrerUser();
        Integer userID = Integer.parseInt(getMyUserID(userName));
        client = new Client(userID, userName);

        // Join a room

        System.out.println("Join a room by typing /join \"roomname\".");
        System.out.println("Supported rooms: room1, room2, room3");
        System.out.println();
        Thread.sleep(2000);

        int rnd = (int) (Math.random() * ((roomMessagePaths.length - 1) + 1));

        System.out.println("> /join " + rooms[rnd]);
        joinRoomRequest(roomUserPaths[rnd], client.userID);
        currentRoom = rooms[rnd];
        currentMessagePath = "http://localhost:8081/api/room/"+currentRoom+"/messages";
        currentRoomPath = "http://localhost:8081/api/room/"+currentRoom+"/users";
        System.out.println();
        Thread.sleep(2000);

        // Get all messages

        System.out.println("Messages in the room:");
        client.viewMessages(roomMessagePaths[rnd]);
        System.out.println();
        Thread.sleep(2000);

        // Send a message
        Thread.sleep(2000);
        String botSentence = BotResponse.intro();
        System.out.println("> "+ botSentence);
        sendMessage(botSentence);

        // Try send a new message
        Thread.sleep(2000);
        String botSentence1 = BotResponse.intro();
        System.out.println("> "+ botSentence1);
        sendMessage(botSentence1);

        // Join another room
        Thread.sleep(2000);
        System.out.println("> /join another room");
        joinAnotherRoom();
        System.out.println();

        // Get messages
        System.out.println("Messages in the room:");
        client.viewMessages(currentMessagePath);

        // Send a message
        System.out.println();
        Thread.sleep(2000);
        String botSentence2 = BotResponse.intro();
        System.out.println("> "+ botSentence2);
        sendMessage(botSentence2);
    }

    private static void joinAnotherRoom() throws IOException, InterruptedException {
        String newRoom;

        while (true) {
            int rnd = (int) (Math.random() * ((roomMessagePaths.length - 1) + 1));
            newRoom = rooms[rnd];
            if (!newRoom.equals(currentRoom)) {
                break;
            }
        }

        currentRoom = newRoom;
        currentRoomPath = "http://localhost:8081/api/room/" + currentRoom + "/users";
        currentMessagePath = "http://localhost:8081/api/room/" + currentRoom + "/messages";

        joinRoomRequest(currentRoomPath, client.userID);
    }

    private static void sendMessage(String message) throws IOException, InterruptedException {
        if (currentRoom != null) {
            postMessage("http://localhost:8081/api/room/" + currentRoom + "/messages", client.userID, message);
        }
    }

    private static void viewRooms(String keyboard) throws IOException {

        String response = getRequest(path + "/rooms");
        JSONObject jsonObject = new JSONObject(response);
        System.out.println(jsonObject.get("rooms"));

    }

    private static void joinRoom(String keyboard) throws IOException, InterruptedException {
        String[] array = keyboard.split(" ");
        String roomPath = path + "room/" + array[1] + "/users";
        joinRoomRequest(roomPath, client.userID);

        client.viewMessages(path + "room/" + array[1] + "/messages");

    }

    private void viewMessages(String path) throws IOException {

        String json = getRequest(path);
        System.out.println(json);


        /*String[] test = json.split(",");
        System.out.println(Arrays.toString(test));

        for (String message : test) {
            message.replace("{", "");
            message.replace("}", "");

            String[] attributes = message.split(":");
            System.out.println(Arrays.toString(attributes));

            System.out.println(message);
        }
*/
    }

    private static String getMyUserID(String name) throws IOException {
        String user = getRequest("http://localhost:8081/api/username/" + name);
        return user;

    }

    private static String registrerUser() throws IOException, InterruptedException {
        System.out.println("Please registrer by typing <your name>");
        String keyboard = readFromTerminal();
        postRequest("http://localhost:8081/api/users", keyboard);

        return keyboard;
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

        // System.out.println(response.body());
    }

    private static void postMessage(String path, Integer senderID, String message) throws IOException, InterruptedException {
        String totalString = senderID + ";" + message;

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(totalString);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(path))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static void joinRoomRequest(String path, Integer senderID) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(senderID);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(path))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // System.out.println("From server: " + response.body());
    }

    private static String getRequest(String path) throws IOException {
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

        String out = inline.replace("[", "");
        String out2 = out.replace("]", "");

        if (out2.equals("")) {
            return "{}";
        }
        return out2;
    }
}

class BotResponse {

    public static String intro() {
        String[] botResponse = {
                "Do you also like to run?",
                "I love playing LOL, especially TFT!",
                "My favorite thing in my place, is the couch.",
                "I like to cook.",
        };
        return botResponse[(int) (Math.random() * ((botResponse.length - 1) + 1))];
    }
}

