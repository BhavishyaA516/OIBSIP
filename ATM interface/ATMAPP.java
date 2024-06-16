import java.util.ArrayList;
import java.util.Scanner;

// ATM Class
class ATM {
    private ArrayList<User> users;
    private User currentUser;
    private Scanner scanner;

    public ATM() {
        users = new ArrayList<>();
        scanner = new Scanner(System.in);
        // For demo purposes, adding a sample user
        users.add(new User("name", "0827"));
    }

    public void start() {
        System.out.println("Welcome to the ATM");

        while (true) {
            System.out.print("Enter user ID: ");
            String userID = scanner.nextLine();
            System.out.print("Enter user PIN: ");
            String userPIN = scanner.nextLine();

            currentUser = authenticateUser(userID, userPIN);

            if (currentUser != null) {
                showMenu();
            } else {
                System.out.println("Invalid user ID or PIN. Please try again.");
            }
        }
    }

    private User authenticateUser(String userID, String userPIN) {
        for (User user : users) {
            if (user.getUserID().equals(userID) && user.validatePIN(userPIN)) {
                return user;
            }
        }
        return null;
    }

    private void showMenu() {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    currentUser.printTransactionHistory();
                    break;
                case 2:
                    performWithdraw();
                    break;
                case 3:
                    performDeposit();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void performWithdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (currentUser.withdraw(amount)) {
            System.out.println("Withdraw successful.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private void performDeposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        currentUser.deposit(amount);
        System.out.println("Deposit successful.");
    }

    private void performTransfer() {
        System.out.print("Enter recipient user ID: ");
        String recipientID = scanner.nextLine();
        User recipient = findUserByID(recipientID);
        if (recipient == null) {
            System.out.println("Recipient user ID not found.");
            return;
        }
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        if (currentUser.transfer(amount, recipient)) {
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private User findUserByID(String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }
}

// User Class
class User {
    private String userID;
    private String userPIN;
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public User(String userID, String userPIN) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserID() {
        return userID;
    }

    public boolean validatePIN(String userPIN) {
        return this.userPIN.equals(userPIN);
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        transactionHistory.add(new Transaction("Withdraw", amount));
        return true;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
    }

    public boolean transfer(double amount, User recipient) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        recipient.deposit(amount);
        transactionHistory.add(new Transaction("Transfer to " + recipient.getUserID(), amount));
        return true;
    }
}

// Transaction Class
class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + ": " + amount;
    }
}

// ATMApp Class
public class ATMAPP{
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}