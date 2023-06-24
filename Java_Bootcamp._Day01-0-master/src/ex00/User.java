package ex00;

public class User {
    private int id;
    private final String name;
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance >= 0 ? balance : 0;
    }

    public User(String name, double balance) {
        this.name = name;
        setBalance(balance);
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