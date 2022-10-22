
import java.util.LinkedList;
import java.util.List;

public class Uppgift6 {


    public static void main(String[] args) {

        int [] styck = new int [10];
        List<Integer> va = new LinkedList<>();
        va.add(1000);
        va.add(500);
        va.add(200);
        va.add(100);
        va.add(50);
        va.add(20);
        va.add(10);
        va.add(5);
        va.add(2);
        va.add(1);
        int växel = 500;
        for(int i = 0; i < 10; i++){
            if (växel >= va.get(i)){
                int v2 = växel/va.get(i);
                styck[i] = v2;
                växel = växel % va.get(i);
            }
        }
        for (int j : styck)
            System.out.println(j);

    }

    }

