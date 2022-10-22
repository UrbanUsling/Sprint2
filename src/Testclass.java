import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Testclass{
    static List<String> temp = new ArrayList<>();
    static List<Double> tempInt = new ArrayList<>();
    public static void main(String[] args){
        String tempLine;
        try(BufferedReader bufIn = new BufferedReader(new
                FileReader("src\\textfil\\temp.txt")))
        {
            while((tempLine = bufIn.readLine()) != null){
                temp.add(tempLine);
            }
        }
        catch (Exception e){
            System.out.println("error happened");
        }
        for (String s : temp) {
            tempInt.add(Double.parseDouble(s));
        }
        System.out.println(temp);
        Double tempMax = Collections.max(tempInt);
        Double tempMin = Collections.min(tempInt);
        DoubleSummaryStatistics iss = tempInt.stream().mapToDouble((a) -> a). summaryStatistics();
        System.out.println("The average of the List is: "+iss.getAverage());

        System.out.println(tempMax + " " + tempMin);
    }

}