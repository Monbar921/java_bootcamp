package ex00;

public class Program {
    public static void main(String[] args) {
        if(args.length == 1){
            if(args[0].matches("^--count=.+")){
                try {
                    int count = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
                    Thread eggThread = new Thread(() -> {
                        for(int i = 0; i < count; ++i){
                            System.out.println("Egg");
                        }
                    });
                    Thread henThread = new Thread(() -> {
                        for(int i = 0; i < count; ++i){
                            System.out.println("Hen");
                        }
                    });
                    eggThread.start();
                    henThread.start();

                    for(int i = 0; i < count; ++i){
                        System.out.println("Human");
                    }
                }catch (NumberFormatException e){
                    System.out.println("Wrong format of count");
                }
            } else {
                System.out.println("Give me right argument");
            }
        } else {
            System.out.println("Give me only 1 argument");
        }
    }
}
