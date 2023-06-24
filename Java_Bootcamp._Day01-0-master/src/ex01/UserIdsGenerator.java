package ex01;

public class UserIdsGenerator {
    private static final UserIdsGenerator INSTANCE = new UserIdsGenerator();
    private int lastGeneratedID = 0;

    private UserIdsGenerator() {
    }

    public static UserIdsGenerator getInstance() {
        return INSTANCE;
    }

    public int generateId(){
        return ++lastGeneratedID;
    }
}
