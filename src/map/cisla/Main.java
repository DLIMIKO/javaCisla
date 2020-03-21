package map.cisla;


import static map.cisla.Cisla.generujCislaDoSuboru;
import static map.cisla.Cisla.filtrujPrvocisla;
import static map.cisla.Cisla.zapisPocetnost;

/**
 * Dobrý deň pán profesor, tu je môj program + meranie času s presnosťou na nanosekundy :)
 */
public class Main
{

    public static void main(String[] args)
    {


        long mark1 = System.nanoTime();

        //generátor pseudonáhodných čísiel
        generujCislaDoSuboru("cisla.txt",10000000,100);

        long mark2 = System.nanoTime();

        //filter prvočísiel
        filtrujPrvocisla("cisla.txt","prvocisla.txt");

        long mark3 = System.nanoTime();

        //analyzátor početnosti
        zapisPocetnost("prvocisla.txt","pocetnost.txt");

        long mark4 = System.nanoTime();

       //časy mearní
        long casGenerovania = (mark2-mark1);
        long casFiltracie= mark3-mark2;
        long casAnalyzy =mark4-mark3;
        long celkovyCas=mark4-mark1;

        //výstup na konzolu
        System.out.printf("Čas generovania:    %.10f s \n",(float)casGenerovania/1000000000);
        System.out.printf("Čas filtrácie:      %.10f s \n",(float)casFiltracie/1000000000);
        System.out.printf("Čas analýzy:        %.10f s \n",(float)casAnalyzy/1000000000);
        System.out.printf("Celkový čas:        %.10f s \n",(float)celkovyCas/1000000000);

    }
}
