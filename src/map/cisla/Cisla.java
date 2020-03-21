package map.cisla;
import java.util.*;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * V triede Cisla sa nachádzajú všetky metódy, ktoré sú potrebné pre splnenie úlohy:
 * generujCislaDoSuboru, filtrujCisla a zapisPocetnosť
 */

public class Cisla
{
    /**
     * Funkcia generujCislaDoSuboru vygeneruje pseudonáhodné celé kladné čísla, ktoré zapíše do súboru
     * @param vystup názov výstupného textového súboru
     * @param pocet počet generovaných čísel
     * @param rozsah rozsah generovaných hodnôt od <0;pocet)
     */

        static void generujCislaDoSuboru(String vystup, int pocet, int rozsah)
         {
             //icicializácia generátora pseudonáhodných čísel:
             Random rand=new Random();

             //inicializácia kontajnera do kt. sa budú generovať čísla:
            // Vector<Integer> pole = new Vector<>();
             ArrayList<Integer> pole=new ArrayList<>();

            //generátor pre pseudonáhodné čísla, kt. sa zapíšu do kontajnera:
             for(int i=0;i<pocet;++i)
            {
                pole.add(rand.nextInt(rozsah));

            }

            //zápis kontajnera do súboru:
             InOut.zapisCisla(vystup, pole);
        }

    /**
     * Funkcia filtrujPrvocisla načíta čísla zo súboru, vyberie z nich prvočísla
     * a následne ich zapíše do druhého súboru.
     * @param vstup názov vstupného textového súboru
     * @param vystup názov výstupného textového súboru
     */
        static void filtrujPrvocisla(String vstup, String vystup)
         {
          //iniciailizácia kontajnera Vector, do kt. sa uložia čísla zo súboru:
             ArrayList<Integer> vsetkyCisla = new ArrayList<>(InOut.citajCisla(vstup));

           // inicializácia druhého kontajnera Vector, kt. nakopíruje do seba hodnoty z prvého kontajnera:
            ArrayList<Integer> prvocisla = new ArrayList<>();

             /*
                filter prvočísel:
                Funguje na princípe, že cyklus "for" iteruje kontajner vsetkyCisla.
                Ak vňom nájde nejaké prvočíslo číslo "z", pridá ho z kontajnera prvocisla.

                POZNÁMKA: Java neumožňuje  prechádzať kontajner ArrayList a súčasne ho mazať, preto
                          musel byť problém vyriešený dvoma kontajnermi.

                Zisťovanie, či je dané  číslo "z" zložené číslo sa uskutočňuje pomocou druhého cyklu "for", kt.
                skúša či je "z" deliteľné číslami  v intervale < 2 ; sqrt(n) ). Ak ho nájde, zastaví hľadanie a nastaví
                hodnotu jePrvocislo na false. V opačnom prípade je implicitne nastavená na true. Následne sa funkcia
                na základe hodnoty jePrvpcislo rozhodne, či hodnotu zapíše do kontajnera prvočísla alebo nie.
                Bolo zistené, že pri každom čísle stačí hľadať delitele iba po jeho druhú odmocninu -- zrýchľuje to kód.
                Táto filtrácia však neplatí pre čísla 0 a 1, čo bolo treba taktiež ošetriť.

              */

           for (int value : vsetkyCisla)
           {

              //logická hodnota, kt. určí či je číslo prvočíslo
               boolean jePrvocislo=true;

               for(int i=2;i<=Math.sqrt(value);i++)
               {
                   if(value % i == 0)
                   {
                       jePrvocislo=false;
                       break;
                   }
               }
               if (jePrvocislo && value>=2)
               {
                   prvocisla.add(value);
               }
           }

           //zápis kontajnera do súboru:
             InOut.zapisCisla(vystup, prvocisla);

         }

    /**
     * Funkcia načíta čísla zo súboru, zistí absolútnu početnosť jednotlivých číselných prvkov
     * a následne ich zapíše do druhého súboru aj s ich abs. početnosťami.
     * @param vstup názov vstupného súboru
     * @param vystup názov výstupného súboru
     */

    public static void zapisPocetnost(String vstup, String vystup)
    {
        //iniciailizácia kontajnera Vector, do kt. sa uložia čísla zo súboru:
        ArrayList<Integer> vsetkyCisla=InOut.citajCisla(vstup);

        // nakopírovanie kontajnera typu Vector do kontajnera Set, aby sa odstránili duplicitné hodnoty:
        Set<Integer> cislaBezDuplicit = new HashSet<>(vsetkyCisla);

        // inicializácia prázdneho kontajnera Map, do kt. sa budú ukladať dvojice hodnota - abs. početnosť:
        Map<Integer, Integer> pocetnost= new HashMap<>();

        /*
            Algoritmus kt. zapisuje do mapy údaje:

            Cyklus "for" prechádza prvky v množine (Set) "cislaBezDuplicit" a pozerá sa na to, koľkokrát
            sa daný prok nachádza v pôvodnom kontajneri "vsetkyCisla" (Vector).
            Následne z týchto dvoch hodôt vytvorí dvojice, ktoré zapíše do mapy (Map) "pocetnost".
            Hodnoty sú zapísané: (Kľúč: hodnota prvku, Hodnota: abs. početnosť)
         */

        for (int value: cislaBezDuplicit)
        {
         pocetnost.put(value,(Collections.frequency(vsetkyCisla,value)));
        }

        /* Algoritmus, kt. dokáže usporiadať mapu podľa požadovaných kritérií a vytvorí novú mapu.
           V našom prípade "sortujeme" podľa hodnoty (viď. comparingByValue) od najväčšej po najmenšiu
           (viď. Comparator.reverseOrder())
         */

        Map <Integer, Integer> pocetnostUsporiadana = pocetnost
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        //zápis kontajnera do súboru:
        InOut.zapisCisla(vystup, pocetnostUsporiadana);
    }
       }


