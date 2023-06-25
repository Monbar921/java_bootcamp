package ex05;

import ex04.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private final TransactionsService transactionsService;
    private boolean isDev = false;

    public Menu(TransactionsService transactionsService, String mode) {
        this.transactionsService = transactionsService;
        if (mode != null && mode.equals("--profile=dev")) {
            isDev = true;
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            String choice = "";
            if(scanner.hasNext()){
                choice = scanner.nextLine();
            }
            boolean isFinish = checkChoice(choice, scanner);
            if (isFinish) {
                break;
            }
        }
    }

    private void printMenu() {
        int pointNumber = 1;
        System.out.println(pointNumber++ + ". Add a user");
        System.out.println(pointNumber++ + ". View user balances");
        System.out.println(pointNumber++ + ". Perform a transfer");
        System.out.println(pointNumber++ + ". View all transactions for a specific user");
        if (isDev) {
            System.out.println(pointNumber++ + ". DEV – remove a transfer by ID");
            System.out.println(pointNumber++ + ". DEV – check transfer validity");
        }
        System.out.println(pointNumber + ". Finish execution");
    }


    private boolean checkChoice(String choice, Scanner scanner) {
        boolean isFinish = false;
        switch (choice) {
            case "1" -> handleAddUser(scanner);
            case "2" -> handleViewUserBalance(scanner);
            case "3" -> handlePerformTransfer(scanner);
            case "4" -> handleAllUserTransactions(scanner);
            case "5" -> {
                if (isDev) {
                    handleRemoveTransfer(scanner);
                } else {
                    isFinish = handleClose();
                }
            }
            case "6" -> {
                if (isDev) {
                    handleCheckValidity();
                } else {
                    System.out.println("Try again");
                }
            }
            case "7" -> {
                if (isDev) {
                    isFinish = handleClose();
                } else {
                    System.out.println("Try again");
                }
            }
            default -> System.out.println("Try again");
        }
        return isFinish;
    }

    private void handleAddUser(Scanner scanner) {
        System.out.println("Enter a user name and a balance");
        while (true) {
            String user = "";
            if(scanner.hasNext()){
                user = scanner.nextLine();
            }
            String[] userData = user.split(" ");
            if (userData.length != 2) {
                System.out.println("Input correct user");
            } else {
                double amount;
                try {
                    if(!userData[0].matches("[a-zA-Z]+")){
                        throw new NumberFormatException();
                    }
                    amount = Double.parseDouble(userData[1]);
                    User added = new User(userData[0], amount);
                    transactionsService.addUser(added);
                    System.out.printf("User with id = %d is added\n", added.getId());
                    System.out.println("---------------------------------------------------------");
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Input correct user");
                    System.out.println("---------------------------------------------------------");
                }
            }
        }


    }

    private void handleViewUserBalance(Scanner scanner) {
        System.out.println("Enter a user ID");
        try {
            int id = 0;
            if(scanner.hasNext()){
                id = scanner.nextInt();
                scanner.nextLine();
            }
            User user = transactionsService.getUsersList().getUserById(id);
            System.out.printf("%s - %.0f\n", user.getName(), transactionsService.getBalance(id));
        } catch (InputMismatchException e) {
            System.out.println("Input valid ID");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("---------------------------------------------------------");
    }

    private void handlePerformTransfer(Scanner scanner) {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        try {
            String transaction = "";
            if(scanner.hasNext()){
                transaction = scanner.nextLine();
            }
            String[] transactionInfo = transaction.split(" ");
            if(transactionInfo.length != 3){
                throw new InputMismatchException();
            }
            int senderId = Integer.parseInt(transactionInfo[0]);
            int recipientId = Integer.parseInt(transactionInfo[1]);
            double amount = Double.parseDouble(transactionInfo[2]);
            transactionsService.executeTransaction(senderId, recipientId, amount);
            System.out.println("The transfer is completed");

        } catch (InputMismatchException | NullPointerException | NumberFormatException  e) {
            System.out.println("Check your input");
        } catch (UserNotFoundException | IllegalTransactionException e) {
            System.out.printf(e.getMessage());
        }
        System.out.println("---------------------------------------------------------");
    }

    private void handleAllUserTransactions(Scanner scanner) {
        System.out.println("Enter a user ID");
        try {
            int id = 0;
            if(scanner.hasNext()){
                id = scanner.nextInt();
                scanner.nextLine();
            }
            User user = transactionsService.getUsersList().getUserById(id);
            Transaction[] transactions = user.getTransactionsList().toArray();
            for (Transaction current : transactions){
                System.out.println(current);
            }
        } catch (InputMismatchException e) {
            System.out.println("Input valid ID");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("---------------------------------------------------------");
    }

    private void handleRemoveTransfer(Scanner scanner) {
        System.out.println("Enter a user ID and a transfer ID");
        try {
            int userId = 0;
            UUID transferId = null;
            if(scanner.hasNext()){
                userId = scanner.nextInt();
                scanner.nextLine();
            }
            if(scanner.hasNext()){
                transferId = UUID.fromString(scanner.nextLine());
            }
            User user = transactionsService.getUsersList().getUserById(userId);
            Transaction[] transactions =  transactionsService.getUserTransactions(userId);
            transactionsService.removeTransaction(userId, transferId);
            Transaction removed = null;
            for(Transaction transaction : transactions){
                if(transaction.getId().equals(transferId)){
                    removed = transaction;
                    break;
                }
            }
            System.out.printf("Transfer To %s(id = %d) %.0f removed\n", user.getName(), user.getId(), removed.getAmount());
        } catch (InputMismatchException | IllegalArgumentException | NullPointerException  e) {
            System.out.println("Invalid ids");
        } catch (UserNotFoundException | TransactionNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("---------------------------------------------------------");
    }

    private void handleCheckValidity() {
        System.out.println("Check results:");
        Transaction[] unpaired = transactionsService.getUnpairedTransactions();
        for(Transaction transaction : unpaired){
            System.out.println(transaction);
        }
    }

    private boolean handleClose() {
        System.out.println("Finishing");
        return true;
    }

}

