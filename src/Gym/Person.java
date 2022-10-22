package Gym;

import javax.swing.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class Person {//klass för skapande av lista med personobjekt
    private static LinkedList<Person> gymLista = new LinkedList<>();
    //statisk lista som inte är objektspecifik utan innehåller alla skapade objekt och som är av intressa att läsa
    private  String personNr;
    private  String namn;
    private LocalDate datum;//privata klassvariabler som inte ska kunna ändras utifrån
    public Person(String nr, String n, String d) {
        skapaPerson(nr, n, d);//konstruktorn som tar 3 stringparametrar och anropar metoden skapaPerson
    }
    public void skapaPerson(String nr, String n, String d){//metoden som skapar personobjekt
        try{
            nr= nr.substring(0, 10);//ett personnummer ska innehålla 10 siffror. skalar bort extratecken
        }catch (StringIndexOutOfBoundsException e){
        //felmeddelnade skickas och exekveringsfelet tas om hand här ifall stringen är kortare än 10
            JOptionPane.showMessageDialog(null, n + "har felaktigt skrivet personnummer",
                    "Error", JOptionPane.PLAIN_MESSAGE);
            System.exit(1);
        }
        if (nr.matches("[0-9]+"))
            personNr=nr;//kontroll att stringen bara innehåller siffror innan den sparas som personnr
        else {
            JOptionPane.showMessageDialog(null, n + "har felaktigt skrivet personnummer",
                    "Error", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);//meddelande och brytning av programmet ifall Stringen innehåller annat än siffror
        }
        n = n.trim();//Stringen trimmas
        if(n.matches("[ a-öA-Ö]+"))
            namn = n;
        //kontroll görs av String för namn så den innehåller rätt tecken innan den tilldelas till klassvariabeln namn
        else{
            JOptionPane.showMessageDialog(null, n + " innehåller otillåtna tecken",
                    "Error", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);//meddelande och programavslut om Stringen innehåller siffror eller otillåtna tecken
        }
        try {
            datum = LocalDate.parse(d);
            //försök att parsa stringen d till en LocalDate med medlemsavgiftens betalningsdag
        }catch (java.time.format.DateTimeParseException e){
            //felhantering och felmeddelande om Stringen är skriven på fel format
            JOptionPane.showMessageDialog(null, n + " har felaktigt skrivet startdatum",
                    "Error", JOptionPane.PLAIN_MESSAGE);
                    System.exit(0);
        }
    }

    public String getNamn() {
        return namn;
    }
    public String getPersonNr(){
        return personNr;
    }
    public LocalDate getDatum(){
        return datum;
    }
    //3 standardgetters ovan för objektets instansvariabler
    public static void addGymLista(Person p){
        gymLista.add(p);
        //här läggs personobjektet in i listan
    }
    public static LinkedList<Person> getGymLista(){
        return gymLista;//avläsning av listan
    }
}
