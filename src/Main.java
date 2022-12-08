import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System. in);
    public static void main(String[] args) {

        List<Prisoner> prisoners;
        prisoners = Utilities.Read_File();
        Utilities.PrintTable(prisoners);
        boolean exit = false;
        do
        {
            int action;
            System.out.println("  Сортувати за : ");
            System.out.println("  [1] - Прізвищем");
            System.out.println("  [2] - Датаою народження");
            System.out.println("  [3] - Датаою ув'язнення");
            System.out.println("  [4] - Дата ост звільнення");
            System.out.println("  [0] - Завершити роботу");
            System.out.print("  Виберіть дію: "); action = input.nextInt();
            switch (action) {
                case 1 -> {
                    System.out.print("\n Відсортовано за Прізвищем");
                    Utilities.Sort_by(prisoners,1);
                }
                case 2 -> {
                    System.out.print("\n Відсортовано за Датаою народження");
                    Utilities.Sort_by(prisoners,2);
                }
                case 3 -> {
                    System.out.print("\n Відсортовано за Датаою ув'язнення");
                    Utilities.Sort_by(prisoners,3);
                }
                case 4 -> {
                    System.out.print("\n Відсортовано за Дата ост звільнення");
                    Utilities.Sort_by(prisoners,4);
                }
                case 0 -> exit = true;
                default -> System.out.println("\nНевідома дія!");
            }

        } while (!exit);
    }
}
