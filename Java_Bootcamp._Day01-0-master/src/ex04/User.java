package ex04;



public class User {
    private final int id;
    private final String name;
    private double balance;
    private TransactionsList transactionsList;


    public User(String name, double balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        transactionsList = new TransactionsLinkedList();
        this.name = name;
        setBalance(balance);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance >= 0 ? balance : 0;
    }

    public TransactionsList getTransactionsList() {
        return transactionsList;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}