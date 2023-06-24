package ex04;


import java.util.UUID;

public class TransactionsService {
    private final TransactionsList transactionsList = new TransactionsLinkedList();
    private final UsersList usersList = new UsersArrayList();

    public void addUser(User user) {
        usersList.addUser(user);
    }

    public double getBalance(int id) {
        return usersList.getUserById(id).getBalance();
    }

    public void executeTransaction(int senderId, int recipientId, double amount) {
        User sender = usersList.getUserById(senderId);
        User recipient = usersList.getUserById(recipientId);
        if (sender == recipient || amount < 0 || sender.getBalance() < amount) {
            throw new IllegalTransactionException();
        }
        Transaction transaction1 = new Transaction(recipient, sender, Transaction.TransferCategory.DEBITS, amount);
        Transaction transaction2 = new Transaction(sender, recipient, Transaction.TransferCategory.CREDITS, amount * -1);
        transaction2.setId(transaction1.getId());

        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);

        sender.getTransactionsList().add(transaction1);
        recipient.getTransactionsList().add(transaction2);

        transactionsList.add(transaction1);
        transactionsList.add(transaction2);
    }

    public Transaction[] getUserTransactions(int id){
        return usersList.getUserById(id).getTransactionsList().toArray();
    }

    public void removeTransaction(int userId, UUID transactionId){
        usersList.getUserById(userId).getTransactionsList().remove(transactionId);
    }

}
