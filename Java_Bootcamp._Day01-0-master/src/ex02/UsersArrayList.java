package ex02;

import java.util.Arrays;

public class UsersArrayList implements UsersList {
    private final int INITIAL_USERS_CAPACITY = 10;
    private final double INCREASE_FACTOR = 1.5;
    private User[] usersStorage;
    private int usersAmount;

    public UsersArrayList() {
        usersStorage = new User[INITIAL_USERS_CAPACITY];
        usersAmount = 0;
    }

    @Override
    public void addUser(User user) {
        if (usersAmount == usersStorage.length) {
            increaseArray();
        }
        usersStorage[usersAmount++] = user;
    }

    private void increaseArray() {
        usersStorage = Arrays.copyOfRange(usersStorage, 0, usersStorage.length);
    }

    @Override
    public User getUserById(int id) {
        if(id < 0){
            throw new UserNotFoundException();
        }
        for(int i = 0; i < usersAmount; ++i){
            User user = usersStorage[i];
            if(user.getId() == id){
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getUserByIndex(int index) {
        if (index < 0 || index >= usersAmount) {
            throw new UserNotFoundException();
        }
        return usersStorage[index];
    }

    @Override
    public int getUsersAmount() {
        return usersAmount;
    }


}
