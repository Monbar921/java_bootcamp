package ex00;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Vasya", 100);
        User user2 = new User("Danya", 200);
        Transaction transaction1 = new Transaction(user2, user1, Transaction.TransferCategory.DEBITS, 100);
        Transaction transaction2 = new Transaction(user1, user2, Transaction.TransferCategory.CREDITS, -100);

        System.out.println(user1);
        System.out.println(user2);

        System.out.println(transaction1);
        System.out.println(transaction2);
    }
}
