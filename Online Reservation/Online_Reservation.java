import java.util.HashMap;
        import java.util.Map;
        import java.util.Scanner;
//Hi! this FARHAN ASHRAF and this is my first task online reservation
public class Online_Reservation {
    private final Map<String, String> users; // For storing username and passwords
    private final Map<String, String> reservations; // For storing reservation data

    public Online_Reservation() {
        users = new HashMap<>();
        reservations = new HashMap<>();
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            //Home page
            System.out.println("------Welcome to Train Reservation------");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Please Enter: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            //choices for each option
            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    register(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }

            System.out.println();
        }
    }

    //for login if user already have id and password
    private void login(Scanner scanner) {
        System.out.println();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println();
            System.out.println("Logged in successfully...");
            System.out.println();
            reservationMenu(scanner, username);
        } else {
            System.out.println();
            System.out.println("Invalid username or password...");
        }
    }

    //if user want to create an account
    private void register(Scanner scanner) {
        System.out.println();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println();
            System.out.println("Username already exists. Try again.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        users.put(username, password);
        System.out.println();
        System.out.println("Registration successful... \nYou can now log in to the system...");
    }

    //after login screen
    private void reservationMenu(Scanner scanner, String username) {
        while (true) {
            System.out.println("-----Welcome "+username+"-----");
            System.out.println("1. Make a reservation");
            System.out.println("2. Cancel a reservation");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            //choice action
            switch (choice) {
                case 1:
                    makeReservation(scanner, username);
                    break;
                case 2:
                    cancelReservation(scanner, username);
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }

            System.out.println();
        }
    }

    //for reservation
    private void makeReservation(Scanner scanner, String username) {
        System.out.println();
        System.out.print("Enter reservation details: ");
        String reservationDetails = scanner.nextLine();

        if (reservations.containsKey(username)) {
            System.out.println("You already have a reservation. Cancel it first to make a new one.");
            return;
        }

        reservations.put(username, reservationDetails);
        System.out.println("Reservation created successfully...");
    }

    //to cancel reservation
    private void cancelReservation(Scanner scanner, String username) {
        if (reservations.containsKey(username)) {
            System.out.println();
            System.out.println("Your current reservation: " + reservations.get(username));
            System.out.print("Do you want to cancel this reservation? (Y/N): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("Y")) {
                reservations.remove(username);
                System.out.println();
                System.out.println("Reservation canceled successfully...");
            } else {
                System.out.println("Reservation not canceled.");
            }
        } else {
            System.out.println("You don't have any reservations.");
        }
    }

    public static void main(String[] args) {
        Online_Reservation reservationSystem = new Online_Reservation();
        reservationSystem.execute();
    }
}

