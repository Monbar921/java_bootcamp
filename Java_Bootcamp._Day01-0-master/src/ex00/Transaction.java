package ex00;

import java.util.UUID;

public class Transaction {
    public enum TransferCategory {
        DEBITS, CREDITS
    }
    private final UUID id;
    private final User recipient ;
    private final User sender;
    private final TransferCategory transferCategory;
    private double amount;


    public Transaction(User recipient, User sender, TransferCategory transferCategory, double amount) {
        this.id = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        if(transferCategory == TransferCategory.DEBITS){
            checkDebit(amount);
        } else {
            checkCredit(amount);
        }
    }

    private void checkDebit(double amount){
        this.amount = amount > 0 ? amount : 0;
    }

    private void checkCredit(double amount){
        this.amount = amount < 0 ? amount : 0;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "ID=" + id +
                ", recipient=" + recipient +
                ", sender=" + sender +
                ", transferCategory=" + transferCategory +
                ", amount=" + amount +
                '}';
    }
}
