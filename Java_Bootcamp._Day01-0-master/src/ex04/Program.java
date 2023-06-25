package ex04;



public class Program {
    public static void main(String[] args) {
        User user1 = new User("Vasya", 100);
        User user2 = new User("Danya", 200);
        TransactionsService transactionsService = new TransactionsService();
        transactionsService.addUser(user1);
        transactionsService.addUser(user2);
        transactionsService.executeTransaction(user1.getId(), user2.getId(), 100);

        System.out.println("user1 balance " + transactionsService.getBalance(user1.getId()));
        System.out.println("user2 balance " +transactionsService.getBalance(user2.getId()));

        Transaction[] transactions1 = transactionsService.getUserTransactions(user1.getId());
        System.out.println("user1 transactions ");
        for(Transaction transaction : transactions1){
            System.out.println(transaction);
        }

        Transaction[] transactions2 = transactionsService.getUserTransactions(user2.getId());
        System.out.println("user2 transactions ");
        for(Transaction transaction : transactions2){
            System.out.println(transaction);
        }

        transactionsService.removeTransaction(user1.getId(), transactions1[0].getId());
        transactions1 = transactionsService.getUserTransactions(user1.getId());
        System.out.println("\nuser1 transactions ");
        for(Transaction transaction : transactions1){
            System.out.println(transaction);
        }

        Transaction[] unpaired = transactionsService.getUnpairedTransactions();
        System.out.println("\nunpaired transactions ");
        for(Transaction transaction : unpaired){
            System.out.println(transaction);
        }
    }
}
