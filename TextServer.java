import java.io.*;
import java.net.*;

class TextServer {
    public static void main(String args[]) throws Exception {

        // requirements
        // Log the TextClient activity on the Serverside
        // this means showing the user's choice and
        // the output that the user would see

        // specifications
        // case '0': signal that the server is running
        //           display their login credentials as well as their access-status
        // case '1': log the choice
        //           log the action (what the case is doing)
        //           log the user list, usernames only
        // case '2': log the choice
        //           log the action and let the user know if message is recieved or not
        // case '3': log the choice
        //           log the action
        //           **Possibly make this conditional, if message failed then this can't return anything
        // case '4': log the choice
        //           log the action

        String userInput="", userMessage="", recipient="", sender="";
        String input="";
        int i;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        boolean connected = false;
        System.out.println("SERVER IS RUNNING... ");
        Socket socketConnection = welcomeSocket.accept();
        BufferedReader clientInput = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
        DataOutputStream serverOutput = new DataOutputStream(socketConnection.getOutputStream());

        //System.out.println("Test from Server");
        String[] user= {"Alice","Bob"};
        String[] password= {"1234","5678"};
        int usersInSession=2;
        int userIndex=2;

        while (true) {
            input = clientInput.readLine();
            switch (input) {
                case "0":
                    while (connected == false) {
                        userInput = clientInput.readLine();
                        for (i = 0; i < usersInSession; i++) {
                            if (userInput.equalsIgnoreCase(user[i])) {
                                userIndex = i;
                                break;
                            }
                            userIndex=i;
                        }
                        System.out.println("Username = " + user[userIndex]);
                        userInput = clientInput.readLine();
                        if (userInput.equalsIgnoreCase(password[userIndex])) {
                            serverOutput.writeBytes("1\n");
                            connected = true;
                            System.out.println("Password = " + userInput);
                            System.out.println("Access Granted");
                            break;
                        } else {
                            System.out.println("Access Denied");
                            serverOutput.writeBytes("0\n");
                        }
                    }
                    System.out.println("===========================");
                    break;
                case "1":
                    System.out.println("User's choice is "+ input);
                    for (i = 0; i < usersInSession; i++) {
                        serverOutput.writeBytes(user[i] + '\n');
                    }
                    serverOutput.writeBytes("");
                    System.out.println("===========================");
                    break;
                case "2":
                    System.out.println("User's choice is "+ input);
                    sender = user[userIndex];
                    userInput = clientInput.readLine();
                    recipient = userInput;
                    userInput = clientInput.readLine();
                    userMessage = userInput;
                    if (recipient != "") {
                        System.out.println("Recieved a message for " + recipient);
                    } else {
                        System.out.println("Error with request, please try again..");
                    }
                    System.out.println("===========================");
                    break;
                case "3":
                    System.out.println("User's choice is "+ input);
                    if (user[userIndex].equalsIgnoreCase(recipient)) {
                        serverOutput.writeBytes(sender + '\n');
                        serverOutput.writeBytes(userMessage + '\n');
                    }
                    System.out.println("Returning messages for " + user[userIndex]);
                    System.out.println("===========================");
                    break;
                case "4":
                    System.out.println("User's choice is "+ input);
                    break;
            }
            if (input.equals("4")) {
                System.out.println(user[userIndex] + " is logged out");
                userInput="";
                System.out.println("===========================");
                connected = false;
            }
            userInput="";
        }
    }
}
