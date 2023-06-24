package ex02;


public class User {
    private final int id;
    private final String name;
    private double balance;
    public User(String name, double balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        setBalance(balance);
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance >= 0 ? balance : 0;
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