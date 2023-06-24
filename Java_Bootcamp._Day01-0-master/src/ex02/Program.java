package ex02;

public class Program {
    public static void main(String[] args) {
        UsersList usersList = new UsersArrayList();
        usersList.addUser(new User("Vasya", 100));
        usersList.addUser(new User("Danya", 200));

        System.out.println(usersList.getUserByIndex(1));
        System.out.println(usersList.getUserById(1));
        System.out.println(usersList.getUsersAmount());

        System.out.println(usersList.getUserById(10));
    }
}
