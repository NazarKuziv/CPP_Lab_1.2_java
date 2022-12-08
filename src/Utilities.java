import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Utilities {

    public static List<Prisoner>  Read_File()
    {
        DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formatter = formatter.withLocale(Locale.ENGLISH);
       
        List<Prisoner> prisoners = new ArrayList<>();
        try {
            File myObj = new File("src/police_file.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] entries = data.split(",");

                Prisoner newPrisoner = new Prisoner();
                newPrisoner.setSurname(entries[0]);
                newPrisoner.setName(entries[1]);
                newPrisoner.setMiddle_Name(entries[2]);
          
                LocalDate localDate = LocalDate.parse(entries[3], formatter);
                newPrisoner.setBirthday(localDate);
                String[] date = new String[entries.length - 4];
                int i = 4, j = 0;

                while (i < entries.length)
                {
                    date[j] = entries[i];
                    i++;
                    j++;

                }
                newPrisoner.setDates_of_Convictions(date);
                localDate = LocalDate.parse(date[j-2], formatter);
                newPrisoner.setDate_of_Last_Imprisonment(localDate);
                localDate =LocalDate.parse(date[j-1], formatter);
                newPrisoner.setDate_of_Last_Dismissal(localDate);
                prisoners.add(newPrisoner);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return prisoners;
    }
    static int tableWidth = 170;
    public static void PrintTable(List<Prisoner> prisonerList)
    {
        PrintLine();
        PrintRow(new String[]{"П.І.П", "Дата народження ", "Дати ув'язнень", "Дата ост ув'язнення", "Дата ост звільнення"});
        PrintLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Prisoner p : prisonerList){
            if (p.getDates_of_Convictions().length == 2)
            {
                String[] str = {p.getSurname() +" "+ p.getName() +" "+ p.getMiddle_Name(), p.getBirthday().format(formatter),
                        p.getDates_of_Convictions()[0]+"-"+ p.getDates_of_Convictions()[1],p.getDate_of_Last_Imprisonment().format(formatter),
                        p.getDate_of_Last_Dismissal().format(formatter)};

                PrintRow(str);
            }
            else
            {
                String[] str = {p.getSurname() +" "+ p.getName() +" "+ p.getMiddle_Name(), p.getBirthday().format(formatter),
                        p.getDates_of_Convictions()[0]+"-"+ p.getDates_of_Convictions()[1],p.getDate_of_Last_Imprisonment().format(formatter),
                        p.getDate_of_Last_Dismissal().format(formatter)};
                PrintRow(str);
                for (int i = 2; i < p.getDates_of_Convictions().length - 1; i++)
                {
                    PrintRow(new String[]{"", "", p.getDates_of_Convictions()[i] + "-" + p.getDates_of_Convictions()[i+1], "", ""});
                }
            }
            PrintLine();
        }
        System.out.println();
    }
    public static void PrintLine()
    {
        System.out.println(new String(new char[tableWidth]).replace("\0", "-"));
    }
    public static String centerString (int width, String s) {
        return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
    public static void PrintRow(String[] columns)
    {
        int width = (tableWidth - columns.length) / columns.length;

        StringBuilder row = new StringBuilder("|");
        for (String column : columns)
        {
            row.append(centerString(width, column)).append("|");

        }
        System.out.print(row);
        System.out.println();

    }
    public static void  Sort_by(List<Prisoner> listPrisoner, int sort_by)
    {
        switch (sort_by) {
            case 1 -> listPrisoner.sort(Comparator.comparing(x -> x.Surname));
            case 2 -> listPrisoner.sort(Comparator.comparing(x -> x.Birthday));
            case 3 -> listPrisoner.sort(Comparator.comparing(x -> x.Date_of_Last_Imprisonment));
            case 4 -> listPrisoner.sort(Comparator.comparing(x -> x.Date_of_Last_Dismissal));
            default -> {
            }
        }

        PrintTable(listPrisoner);
    }
}
