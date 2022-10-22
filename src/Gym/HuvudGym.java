package Gym;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class HuvudGym {//huvudprogrammet för gymmet
    public HuvudGym() {//huvudprogrammets konstruktor
        try (var scan = new Scanner(new File("src\\textfil\\customers.txt"));
        ) {//inläsning av fil sker här inuti en try
            while (scan.hasNext()) {//så länge som det finns fler rader efter varje varv
                String nr = scan.next();
                String namn = scan.nextLine();
                //personnr och namn är på samma rad. Deta sätt separerar dem till två olika strings
                String datum = scan.nextLine();//andra raden har enbart medlemsdatumet
                Person.addGymLista(new Person(nr, namn, datum));
                /*ett nytt Personobjekt skapas med de 3 stringarna som parametrar och objektet
                läggs till i gymlistan, Finns det fler rader så börjar ett nytt objekt att skapas efter detta
                 */
            }
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,  "Filen kunde inte hittas",
                    "Error file not found", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
            //felmeddelande och felhantering ifall filen inte existerar. Programmet stängs
        }
    }

    public static void main(String[] args) throws IOException {//main
        HuvudGym hej = new HuvudGym();
        //ny instance av huvudkonstruktorn för att först skapa objekt och sen lista med personerna i textfilen
        KollaKund heja = new KollaKund();
        //ny instance av KollaKundkonstruktorn för att kunna söka på namn när väl objektlistan skapats
    }
}