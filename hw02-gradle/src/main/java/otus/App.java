package otus;

public class App {
    public static void main(String[] args) {
        DIYarrayList<Integer> list = new DIYarrayList<Integer>();
        System.out.println("A my realisation of arraylist\n" +
                "Data in arraylist with size now:");
        for (int i = 0; i < 10; ++i) {
            list.add(1);
        }
        list.print();
        System.out.println("\nLet's change the first element from '0' to '11'");
        list.set(0, 11);
        System.out.println("ArrayList data now:");
        list.print();
    }
}
