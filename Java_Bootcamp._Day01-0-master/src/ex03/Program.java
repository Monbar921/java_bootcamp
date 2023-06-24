package ex03;


import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Vasya", 100);
        User user2 = new User("Danya", 200);
        Transaction transaction1 = new Transaction(user2, user1, Transaction.TransferCategory.DEBITS, 100);
        Transaction transaction2 = new Transaction(user1, user2, Transaction.TransferCategory.CREDITS, -100);

        TransactionsList transactionsList = new TransactionsLinkedList();
        transactionsList.add(transaction1);
        transactionsList.add(transaction2);

        transactionsList.remove(transaction2.getId());

        Transaction[] transactions = transactionsList.toArray();
        for (Transaction x : transactions){
            System.out.println(x);
        }

        transactionsList.remove(UUID.randomUUID());
    }
}
