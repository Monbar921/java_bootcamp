package ex05;

import ex04.TransactionsService;

public class Program {
    public static void main(String[] args) {
        TransactionsService transactionsService = new TransactionsService();
        String devMode = null;
        if(args != null && args.length != 0){
            devMode = args[0];
        }
        Menu menu = new Menu(transactionsService, devMode);
        menu.start();
    }
}
