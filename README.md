# Socket Project
The goal of this project is to write simple client and server programs for text messaging with socket programming.

The program involves a server managing client accounts with usernames and passwords. Clients interact via a menu, offering actions like connecting to the server, retrieving user lists, sending and receiving messages, and exiting.

Clients can log in (Option 0) by validating credentials against the server's stored data. Usernames are retrieved (Option 1) from the server's list. Clients can send messages (Option 2) to others, which the server stores. Clients fetch their messages (Option 3) left by others from the server. Terminating the client (Option 4) closes the TCP connection and exits.
