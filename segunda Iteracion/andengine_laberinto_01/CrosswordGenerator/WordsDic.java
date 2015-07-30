package com.example.mipc.andengine_laberinto_01.CrosswordGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordsDic {

    private Hashtable<String, String> dictionary;
    private int numWords = 6;

    public WordsDic(){

        dictionary = new Hashtable<String, String>();

        try{

            readDictionary();

        } catch (IOException e) {

            dictionary = new Hashtable<String, String>();
        }
    }
    public void setNumWords(int numWords){
        this.numWords = numWords;
    }
    private void readDictionary() throws IOException{
        /*
        String fileName = "dictionary.txt";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String str;

        while ((str=reader.readLine()) != null) {

            String[] line = str.split(";");

            dictionary.put(line[0].trim(), line[1].trim());
        }
        reader.close();
        */
        //Las pistas no deben ser largas
        dictionary.put("saffron", "The dried orange");
        dictionary.put("pumpernickel", "Dark sour bread");
        dictionary.put("leaven", "An agent such");
        dictionary.put("coda", "Musical conclusion of");
        dictionary.put("paladin", "A heroic champion");
        dictionary.put("syncopation", "Shifting the emphasis");
        dictionary.put("albatross", "A large bird");
        dictionary.put("harp", "Musical instrument with");
        dictionary.put("piston", "A solid cylinder");
        dictionary.put("caramel", "A smooth chery");
        dictionary.put("coral", "A rocklike deposit");
        dictionary.put("dawn", "The time of");
        dictionary.put("pitch", "A resin derived");
        dictionary.put("fjord", "A long narrow");
        dictionary.put("lip", "Either of two");
        dictionary.put("lime", "The eggshaped citrus");
        dictionary.put("mist", "A mass of");
        dictionary.put("plague", "A widespread affliction");
        dictionary.put("yarn", "A strand of");
        dictionary.put("snicker", "A snide slightly");
        dictionary.put("Agata", "Christie autorka kryminaw");
        dictionary.put("agora", "wydawca Gazety Wyborczej");
        dictionary.put("Alina", "siostra Balladyny");
        dictionary.put("Amata", "ona mitycznego krla");
        dictionary.put("Ayala", "wspczesny pisarz hiszpaski");
        dictionary.put("poemat", "dugi podniosy utwr");
        dictionary.put("Proust", "Marcel  francuski");
        dictionary.put("baalizm", "Religie kult Baala");
        dictionary.put("Babbage", "Matematyka Charles angielski");
        dictionary.put("babiarz", "Film uwodziciel lowelas");
        dictionary.put("Babilon", "Historia miasto wiszcych");
        dictionary.put("babulin", "Zoologia odmiana pawiana");
        dictionary.put("bacwka", "Geografia szaas juhasw");
        dictionary.put("badanie", "Medycyna diagnozowanie");
        dictionary.put("Baetica", "Geografia Betika Andaluzja");
        dictionary.put("bafomet", "Religie wyobraenie diaba");
        dictionary.put("Baggins", "Literatura Bilbo ");
        dictionary.put("bagieta", "Technika ozdobna listwa");
        dictionary.put("bahaizm", "Religie ruch religijny");
        dictionary.put("Baal", "Mitologia kananejski bg");
        dictionary.put("baby", "Edukacja dziecko po");
        dictionary.put("baca", "Geografia starszy pasterz");
        dictionary.put("bajt", "Informatyka jednostka informacji");
        dictionary.put("baki", "Medycyna bokobrody");
        dictionary.put("bank", "Ekonomia udziela kredytw");
        dictionary.put("bant", "eglarstwo pas ptna");
        dictionary.put("baon", "Wojsko batalion");
        dictionary.put("bard", "Muzyka celtycki pieniarz");
        dictionary.put("bary", "Sport szerokie u");
        dictionary.put("Bask", "Geografia mieszkaniec krainy");
        dictionary.put("bata", "Technika czeska fabryka");
        dictionary.put("Baum", "Literatura Maks bohater");
        dictionary.put("bki", "Zoologia rodzina muchwek");
        dictionary.put("bean", "Edukacja nowo przyjty");
        dictionary.put("Cabota", "Geografia cienina oddzielajca");
        dictionary.put("Cabrit", "Historia Fryderyk Kabryt");
        dictionary.put("caciki", "Gastronomia grecki sos");
        dictionary.put("Caguas", "Geografia miasto we");
        dictionary.put("Calaba", "Geografia miasto w");
        dictionary.put("caligo", "Zoologia wyjtkowo duy");
        dictionary.put("Cambio", "Sztuka Arnolfo di");
        dictionary.put("Campos", "Geografia rodzaj poudniowoamerykaskiej");
        dictionary.put("Pamuk", "Literatura Orhan turecki");
        dictionary.put("Clezio", "Literatura JeanMarie Gustave");
        dictionary.put("komputer", "mylce urzdzenie");
        dictionary.put("fiszka", "wyparta przez dyskietk");
        dictionary.put("folder", "zestawienie plikw katalog");
        dictionary.put("furtka ", "rama komputer penicy");
        dictionary.put("Word", "komputerowy edytor tekstw");
        dictionary.put("Wlan", "bezprzewodowa komputerowa sie");
        dictionary.put("worm", "wirus komputerowy rozprzestrzeniajcy");
        dictionary.put("Wiki", "nazwa szczeglnego rodzaju");
        dictionary.put("Microsoft", "Firma komputerowa");
        dictionary.put("Google", "wyszukiwarka program uytkowy");
        dictionary.put("Gopher", "usuga internetowa dziaajca");
        dictionary.put("Root", "katalog gwny na");
        dictionary.put("Trojan", "rodzaj programu komputerowego");
        dictionary.put("Telnet", "standard");
        dictionary.put("raca", "Wojsko pocisk sygnalizacyjny");
        dictionary.put("tabaka", "Medycyna sproszkowany tyto");
    }

    public Hashtable<String, String> getDictionary() {
        //devolveremos solo una parte del diccionario
        if(numWords>dictionary.size()) return dictionary;

        int ini = (new Random()).nextInt(dictionary.size());
        int resto = dictionary.size() - ini - 1;
        List<String> list = new ArrayList<String>(dictionary.keySet());
        Hashtable<String, String> newdictionary;
        newdictionary = new Hashtable<String, String>();
        if(resto < numWords){
            ini = ini - numWords;
            if(ini<0){
                ini = 0;
            }
        }
        for(int i = ini; i< ini+numWords; i++){
            newdictionary.put(list.get(i), dictionary.get(list.get(i)));
        }
        dictionary = newdictionary;
        return dictionary;
    }

}
