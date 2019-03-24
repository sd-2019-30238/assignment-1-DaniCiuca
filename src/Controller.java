import java.util.Scanner;

public class Controller {
    private DAOBook daoBook;
    private DAOUser daoUser;
    private DAOStaff daoStaff;
    private boolean exit = false;
    private boolean exitStaff = false;

    private Scanner scanner = new Scanner(System.in);

    public Controller() {
        ConnectionFactory dao = new ConnectionFactory();
        dao.createConnection();
        daoBook = new DAOBook();
        daoUser = new DAOUser();
        daoStaff = new DAOStaff();
    }

    public void run(){
        while(exit == false) {
            System.out.println("Enter a command: ");
            String command = scanner.next().trim();
            String username;
            String password;
            String paymentMethod;
            switch (command) {
                case "login":
                    System.out.print("Username: ");
                    username = scanner.next().trim();
                    System.out.print("Password: ");
                    password = scanner.next().trim();
                    if (daoUser.searchUser(username, password))
                        System.out.println("User connected!!");
                    else
                        System.out.println("User not find!!");
                    break;
                case "register":
                    System.out.print("Username: ");
                    username = scanner.next().trim();
                    System.out.print("Password: ");
                    password = scanner.next().trim();
                    System.out.print("Payment Plan: ");
                    paymentMethod = scanner.next().trim();
                    daoUser.insert(username, password, paymentMethod);
                    System.out.println("\nThe user was register!!!");
                    break;
                case "staff":
                    System.out.print("Username: ");
                    username = scanner.next().trim();
                    System.out.print("Password: ");
                    password = scanner.next().trim();
                    if (daoStaff.searchUser(username, password)) {
                        System.out.println("Staff connected!!");
                        exit = true;
                        staffConnected();
                    } else
                        System.out.println("Staff not find!!");
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }

    public void staffConnected()
    {
        while (!exitStaff) {
            System.out.println("<STAFF> Enter a command: ");
            String command = scanner.next().trim();
            String username;
            switch (command) {
                case "accept":
                    System.out.print("Username: ");
                    username = scanner.next().trim();
                    daoStaff.acceptUser(username);
                    break;
                case "exit":
                    exitStaff = true;
                    exit = false;
                    run();
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }
}
