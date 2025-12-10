import java.util.HashMap;
import java.util.Scanner;

public class User {
    private Scanner input = new Scanner(System.in);
    private String [] data ; // name , lastname , birthday , subscribe
    private String username;
    private String password;
    private DynamicQueue <String> friendsRequest;
    private LinkedList<String> friends;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.friendsRequest = new DynamicQueue<>();
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void addFriend() {
        friends.append(friendsRequest.dequeue());
    }
    public void rejectRequest() {
        friendsRequest.dequeue();
    }
    public void sendRequest(String message , String receiverUsername) {
        message+="\n"+data[0]+"\n"+username;

    }
    public void userInterface() {
        char choice ;
        boolean first = true;
        if (first) {
            System.out.print("welcome " + data[0] + " 😄\n");
            first = false;
        }
        while (true) {
            System.out.print("""
                    enter your choice :
                    1.post
                    2.friends
                    3.message
                    4.requests
                    5.@ for back to previous stage
                    =>
                    """);
            choice = input.next().charAt(0);
            switch (choice) {
                case '1':
                    decor("posts");
                    break;
                case '2':
                    decor("friends");
                    break;
                case '3':
                    decor("messages");
                    break;
                case '4':
                    decor("requests");
                    break;
                case '@':
                    return;
                default:
                    System.out.println("invalid choice");
            }
        }
    }
    public void friends() {
        System.out.print("""
                enter your choice :
                1.search
                2.friend list
                """);
        char choice;
        while (true) {
            choice = input.next().charAt(0);
            switch (choice) {
                case '1':

            }
        }
    }
    public void message() {

    }
    public void posts() {

    }
    public void requests() {

    }
    public void search() {

    }
    public void friendList(){

    }
    public void decor(String funcName){
        System.out.println("=================================================");
        System.out.println("\t\t"+funcName.toUpperCase());
        switch (funcName) {
            case "posts":
                posts();
                break;
            case "friends":
                friends();
                break;
            case "messages":
                message();
                break;
            case "requests":
                requests();
                break;
        }
        System.out.println("=================================================");
    }
}
