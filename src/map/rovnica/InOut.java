package map.rovnica;

import java.io.*;
import java.util.Map;
import java.util.Vector;


/**
 * Trieda InOut obsahuje všetky metódy potrebné pre narábanie zo súbormi:
 * cítajCisla() pre Vector, zapisCisla pre Vector, Map
 */
public class InOut
{
    /**
     * Funkcia citajCisla prečíta čísla zo súboru a hodnoty inicializuje do kontajnera kt. odovzdá
     * @param vstup názov vstupného  textového súboru
     * @return kontajner typu Vector
     * @throws FileNotFoundException ak nenájde požadovaný súbor
     * @throws IOException ak nastane chyba pri načítaní dát
     * @throws NumberFormatException ak by súbor obsahoval iné znaky než čísla int
     */
    public static Vector<Integer> citajCisla (String vstup)

    {
        //inicalizácia  buffra:
        BufferedReader fin=null;

        //inicalizácia  kontajnera, do kt. sa zapíšu hodnoty:
        Vector<Integer> cisla=new Vector<>();

        try
        {
            // inicializácia vstupu súborom:
            fin=new BufferedReader(new FileReader(vstup));

           //reťazec do kt. sa uloží riadok súboru:
            String riadok;

            //cyklus číta zo súboru až po prvý prázdny riadok

            while ((riadok=fin.readLine())!=null)
            {
               //rozdelia sa riadky na znaky
                String[] znaky=riadok.split(" ");

                //cyklus prečíta znaky, prevedie ich na int a zapíše ich do kontajnera
                for (String s : znaky)
                {

                    cisla.add(Integer.parseInt(s));
                }
            }
        }

        //výnimky
        catch (FileNotFoundException e)
          {

           System.out.println("Súbor sa nepodarilo nájsť !");
           System.exit(1);

          }
        catch(NumberFormatException | IOException e)
         {

           System.out.println("Chyba pri čítaní dát !");
           System.exit(1);

         }
        finally
        {
            try
            {
                if(fin!=null)
                {
                    fin.close();
                }

            }
            catch(IOException e)
            {
                System.out.println("Chyba pri zatváraní súboru !");
                System.exit(1);
            }
        }
        return cisla;
    }

    /**
     * Funkcia zapíše hodnoty kontajnera do súboru
     * @param vystup meno výstupného textového súboru
     * @param poleCisiel kontajner typu Vector, kt. zapisujem do súboru
     * @throws FileNotFoundException ak nenájde požadovaný súbor
     * @throws IOException ak nastane chyba pri načítaní dát
     * @throws NumberFormatException ak by súbor obsahoval iné znaky než čísla int
     */

    public static void zapisCisla(String vystup, Vector<Integer> poleCisiel)
    {
        //inicializácia buffra
        BufferedWriter fout=null;
        try
        {
           //inicializácia vstupu súborom
            fout=new BufferedWriter(new FileWriter(vystup));

          //cyklus zapíše hodnoty z kontajnera do súboru
            for (int value : poleCisiel)
            {
                fout.write(value + " ");
            }
        }

        //výnimky
        catch (FileNotFoundException e)
        {
            System.out.println("Súbor sa nepodarilo nájsť !");
            System.exit(1);
        }

        catch (NumberFormatException | IOException e)
        {
            System.out.println("Chyba pri čítaní dát !");
            System.exit(1);
        }
        finally
        {
            try
            {
                if(fout!=null)
                {
                    fout.close();
                }
            }
            catch (IOException e)
            {
                System.out.println("Nepodarilo sa zatvoriť súbor !");
                System.exit(1);
            }
        }
    }

    /**
     * Funkcia zapíše hodnoty kontajnera do súboru
     * @param vystup meno výstupného textového súboru
     * @param poleCisiel kontajner typu Map<Integer, Integer>, kt. zapisujem do súboru
     * @throws FileNotFoundException ak nenájde požadovaný súbor
     * @throws IOException ak nastane chyba pri načítaní dát
     * @throws NumberFormatException ak by súbor obsahoval iné znaky než čísla int
     */
    public static void zapisCisla(String vystup, Map<Integer,Integer> poleCisiel)
    {
       //inicializácia buffra
        BufferedWriter fout=null;
        try
        {
            //inicializácia vstupu súborom
            fout=new BufferedWriter(new FileWriter(vystup));

            //vytlačí sa hlavička do súboru
            fout.write("hodnota:početnosť\n");

            //cyklus zapíše kontajner do súboru (kľúč-hodnota)
            for (int key: poleCisiel.keySet())
            {
                fout.write(key + " "+ poleCisiel.get(key)+" \n");
            }
        }

        //výnimky
        catch (FileNotFoundException e)
        {
            System.out.println("Súbor sa nepodarilo nájsť !");
            System.exit(1);
        }

        catch (NumberFormatException | IOException e)
        {
            System.out.println("Chyba pri čítaní dát !");
            System.exit(1);
        }
        finally
        {
            try
            {
                if(fout!=null)
                {
                    fout.close();
                }
            }
            catch (IOException e)
            {
                System.out.println("Nepodarilo sa zatvoriť súbor !");
                System.exit(1);
            }
        }
    }


}