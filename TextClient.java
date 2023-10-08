import java.io.*;
import java.net.*;

class TextClient {
    static void showMenu(){
        System.out.println("0. Connect to the server");
        System.out.println("1. Get the user list");
        System.out.println("2. Send a message");
        System.out.println("3. Get my message");
        System.out.println("4. Exit");
        System.out.print("Please enter your choice: ");
    }
    public static void main(String args[]) throws Exception {

        // Requirements
        // Promp the user for a Username
        // Prompt the user for a Password
        // let the user know if they get access or not
        // Present 5 options in a drop down text
        // Cases 0-4
        // Connect to server, get users list, send message, get message, exit (Respectively)

        // Specifications
        // case 0: Let the user know the server is running
        //         Prompt user for a username, let them input a String
        //         Prompt user for a password, let them enter a String
        // case 1: returns usernames
        // case 2: prompt user for username to send message to
        //         let the user write a message after prompt telling them to do so
        // case 3: return the message sent to the aforementioned user
        // case 4: Exit the program

        String sentence, response;
        String input=""; 
        boolean connected=false;
        int usersInSession=2;
        BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
        Socket socketConnection = new Socket("127.0.0.1", 6789);
        DataOutputStream clientOutput = new DataOutputStream(socketConnection.getOutputStream());
        BufferedReader serverInput = new BufferedReader(new InputStreamReader( socketConnection.getInputStream()));

        while (true) {
            showMenu();
            input = clientInput.readLine();
            clientOutput.writeBytes(input + "\n");

            switch (input) {
                case "0":
                    while (connected == false) {
                        System.out.println("Please input user name: ");
                        sentence = clientInput.readLine();
                        clientOutput.writeBytes(sentence + '\n'); // name
                        System.out.println("Password:");
                        sentence = clientInput.readLine(); // password
                        clientOutput.writeBytes(sentence + '\n');
                        response = serverInput.readLine();
                        if (response.equals("1")) {
                            System.out.println("\nAccess Granted");
                            connected = true;
                        } else {
                            System.out.println("Access Denied-Username/Password Incorrect");
                        }
                    }
                    System.out.println("===========================");
                    break;
                case "1":
                    System.out.println("Getting list of users...");
                    for (int i = 0; i < usersInSession; i++) {
                        response = serverInput.readLine();
                        System.out.println(response + '\n');
                    }
                    System.out.println("===========================");
                    break;
                case "2":
                    System.out.println("Enter a username you want to send a message to: ");
                    sentence = clientInput.readLine();
                    clientOutput.writeBytes(sentence + '\n');
                    System.out.println("Enter the message you want to send: ");
                    sentence = clientInput.readLine();
                    clientOutput.writeBytes(sentence + '\n');
                    System.out.println("===========================");
                    break;
                case "3":
                    System.out.println("You have got a message from:");
                    response = serverInput.readLine();
                    System.out.println(response + '\n');
                    System.out.println("The message is:");
                    response = serverInput.readLine();
                    System.out.println(response + '\n');
                    System.out.println("===========================");
                    break;
                case "4":
                    sentence = "";
                    response = "";
                    connected = false;
                    System.out.println("===========================");
                    break;
            }
            input = "";
        }
    }
}