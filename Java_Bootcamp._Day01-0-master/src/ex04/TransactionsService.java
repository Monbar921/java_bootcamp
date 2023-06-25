package ex04;


import java.util.Arrays;
import java.util.UUID;

public class TransactionsService {
    private final TransactionsList allTransactions = new TransactionsLinkedList();
    private final TransactionsList removedTransactions = new TransactionsLinkedList();
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

        allTransactions.add(transaction1);
        allTransactions.add(transaction2);
    }

    public Transaction[] getUserTransactions(int id){
        return usersList.getUserById(id).getTransactionsList().toArray();
    }

    public void removeTransaction(int userId, UUID transactionId){
        Transaction[] transactionsBefore = usersList.getUserById(userId).getTransactionsList().toArray();
        usersList.getUserById(userId).getTransactionsList().remove(transactionId);
        Transaction[] transactionsAfter = usersList.getUserById(userId).getTransactionsList().toArray();
        Transaction deleted = null;
        for(int i = 0; i < transactionsAfter.length; ++i){
            if(transactionsBefore[i] != transactionsAfter[i]){
                deleted = transactionsBefore[i];
            }
        }
        if(deleted == null){
            deleted = transactionsBefore[transactionsAfter.length];
        }
        removedTransactions.add(deleted);
    }

    public Transaction[] getUnpairedTransactions(){
        Transaction[] transactions = allTransactions.toArray();
        Transaction[] deleted = removedTransactions.toArray();

        Transaction[] temp = new Transaction[deleted.length];
        int totalUnpairs = 0;
        for(Transaction removed : deleted){
            int uniqIds = 0;
            for(Transaction pair : deleted){
                if(removed.getId().equals(pair.getId())){
                    ++uniqIds;
                }
                if(uniqIds == 2){
                    break;
                }
            }
            if(uniqIds != 2){
                for(Transaction pair : transactions){
                    if(removed.getId().equals(pair.getId()) && removed != pair) {
                        temp[totalUnpairs++] = pair;
                    }
                }
            }
        }

        return Arrays.copyOfRange(temp, 0, totalUnpairs);
    }

}
