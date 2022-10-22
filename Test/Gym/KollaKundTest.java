package Gym;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class KollaKundTest {
    String nr = "8801011111wgwe";
    String n = " Philip ";
    String d = "2022-01-01";

    Person p = new Person(nr, n, d);
    //Personobjekt skapas
    KollaKund k = new KollaKund(p.getNamn());
    KollaKund l = new KollaKund(p.getPersonNr());
    //två identiska KollaKundObjekt skapas av personobjektet. Ena genom namn, andra genom personnr.

    public KollaKundTest() {//testkonstruktor som kör metoderna för objekten så upprepning ej krävs i metoderna
        Person.addGymLista(p);
        k.namnMatch(k.getNamn());
        l.namnMatch(l.getNamn());
        k.tidsBeräkning();
        l.tidsBeräkning();

    }



        @Test
        void sparaKundinfo () throws IOException {

            assertDoesNotThrow(()->k.sparaKundinfo(k.getPr().getNamn()));
            assertDoesNotThrow(()->l.sparaKundinfo(l.getPr().getNamn()));
            /*Lambda används för att köra och kolla att inget kastas vidare.
            Sen kan man se ifall en fil skapas med
            personinformation och datumstämpel. Båda objekten refererar till samma person,
            så dem ska skriva i samma fil (2* skrift)
             */

        }

        @Test
        void getAntalDagar () {
            assertEquals(k.getAntalDagar(), ChronoUnit.DAYS.between(p.getDatum(), LocalDate.now()));
            assertEquals(l.getAntalDagar(), ChronoUnit.DAYS.between(k.getPr().getDatum(), LocalDate.now()));
            //antal dagar jämförs på 2 olika sätt. Ena hämtar startdatum från person p, andra från KollaKund k.
        }

        @Test
        void isAktivMedlem () {
            assertTrue(k.isAktivMedlem());
            assertTrue(l.isAktivMedlem());

        }

        @Test
        void isMedlem () {
            assertTrue(k.isMedlem());
            assertTrue(l.isMedlem());
        }
        //två enkla getters ovan där boolean kontrolleras att den ändras till true
        @Test
        void getPr () {
            assertEquals(k.getPr().getNamn(), p.getNamn());
            assertEquals(l.getPr().getPersonNr(), p.getPersonNr());
            assertEquals(l.getPr().getDatum(), p.getDatum());
            //kontroll att rätt personobjekt från listan, pr, sparats. Alla 3 variablerna matchas till person p
        }
    }
