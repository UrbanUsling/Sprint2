package Gym;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class KollaKund {//Här läses listan från personklassen av och kontrolleras mot namn eller personnr
    private String namn;
    private Person pr;
    private boolean medlem = false;
    private boolean aktivMedlem = false;
    private long antalDagar;//5 privata klassvariabler

    public KollaKund(String namn){
      this.namn=namn;//denna konstruktor är här för tester där det ej ska vara manuell inmatning av namn


    }
    public KollaKund() throws IOException {
        //huvudkonstruktorn för huvudprogrammet. Skriver filer så måste kasta exception
        namnInmatning();
        namnMatch(namn);
        if (medlem)
            tidsBeräkning();
        else{
            JOptionPane.showMessageDialog(null, "Denna person har aldrig varit medlem",
                    "Ej medlem", JOptionPane.PLAIN_MESSAGE);
        }

        if (aktivMedlem)
            sparaKundinfo(pr.getNamn());

    }

    public void namnInmatning() {//inmatning av namn från dialogruta. kan vara personnummer också
        namn = (JOptionPane.showInputDialog(null,
                "Vilken medlem söker du? Ange namn eller 10siffrigt personnummer",
                "Medlemssökning", JOptionPane.QUESTION_MESSAGE));
    }

    public void namnMatch(String namn){//namn/nr från antingen dialogruta eller testkonstruktor
        if(namn==null)//om inget namn/nr har skrivits så ska programmet stängas
            System.exit(0);
        namn = namn.toLowerCase();//namnet görs om till små bokstäver för att förenkla
        for (Person p : Person.getGymLista()) {
            if (p.getNamn().toLowerCase().equals(namn) || p.getPersonNr().equals(namn)) {
                //listan med personobjekt loopas för att hitta match på antingen namn eller personnr
                antalDagar = ChronoUnit.DAYS.between(p.getDatum(), LocalDate.now());
                /*om man fick träff på namn/nr så plockas datumet ut och jämförs med dagens datum.
                ChronoUnit gör jämförelsen och sparar som dagar i variabeln antalDagar
                 */
                pr = p;
                //det aktuella personobjektet i listan vi fick träff på sparas. dess data kan då enkelt avläsas
                medlem = true;
                //eftersom vi fick träff så ändras boolean till true. Den gör att vi fortsätter i konstruktorn
            }
        }

    }
    public void tidsBeräkning() {
        //simpel if sats som kontrollerar om det har gått 365 dagar från att avgiften betalades
        if (antalDagar < 365) {
            aktivMedlem = true;
            //om det inte har gått ett år än så är man aktiv medlem. Utskrift sker av antalet dagar som är kvar
            JOptionPane.showMessageDialog(null, pr.getNamn() +
                            " är välkommen att gymma! "+ (365 - antalDagar)+" dagar kvar på medlemsskapet",
                    "Aktiv medlem", JOptionPane.PLAIN_MESSAGE);
        } else {//över(lika med) ett år har gått, så personen måste betala avgiften på nytt
            JOptionPane.showMessageDialog(null, pr.getNamn()+ " måste betala avgiften då ett år passerats",
                    "Gammal medlem", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void sparaKundinfo(String namn) throws IOException {
        /*en filewriter som gör en fil för en aktiv medlem som tränar och get en tidsstämpel med dagens
        datum. Den har den nya versionen av try with resources och försäkrar att strömmen stängs oavsätt
        vilket typ av fel som kan inträffa. Inget "finally" behövs
         */
        try (
            FileWriter writer = new FileWriter("src\\textfil\\" + namn + " "+ pr.getPersonNr()
                    + ".txt", true);

        ) {
                writer.write(namn + " "+ pr.getPersonNr()+" tränade " + LocalDate.now());
                writer.write("\n");


            }
        }

        public long getAntalDagar(){
        return antalDagar;
        }

    public boolean isAktivMedlem() {
        return aktivMedlem;
    }

    public boolean isMedlem() {
        return medlem;
    }

    public Person getPr() {
        return pr;
    }

    public String getNamn() {
        return namn;
    }
    //getters för att kunna se status av booleans eller objektets data
}
