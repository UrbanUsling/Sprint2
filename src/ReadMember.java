import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ReadMember {
    static List<String> medlemsnr = new LinkedList<>();
    static List<String> medlemsnr2 = new LinkedList<>();
    static List<String> namn = new LinkedList<>();
    static List<String> namn2 = new LinkedList<>();
    static List<LocalDate> datum = new LinkedList<>();
    //public void readFile() throws FileNotFoundException {


    public static void main(String[] args) throws FileNotFoundException {
        var scan = new Scanner(new File("src\\textfil\\customers.txt"));
        while (scan.hasNext()) {
            medlemsnr.add(scan.next());
            namn.add(scan.nextLine());
            datum.add(LocalDate.parse(scan.next()));
        }
        for (String s : medlemsnr) {
            s = s.substring(0, s.length() - 2);
            medlemsnr2.add(s);
        }
        for (String s : namn) {
            s = s.trim();
            namn2.add(s);
        }
            System.out.println(medlemsnr2);
            System.out.println(namn2);
            System.out.println(datum);

        }
    }

