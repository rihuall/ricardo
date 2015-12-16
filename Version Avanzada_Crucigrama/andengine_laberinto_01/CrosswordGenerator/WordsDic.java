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
        
        //Las pistas NO DEBEN SER LARGAS
        dictionary.put("trece", "diez mas tres");
        dictionary.put("uci", "Unidad de Cuidados Intensivos");
        dictionary.put("napoles", "Ciudad de Italia");
        dictionary.put("coda", "Musical conclusion of");
        dictionary.put("paladin", "Campeon Heroico");
        dictionary.put("VOLATIL", "Que puede convertirse en vapor");
        dictionary.put("albatross", "A large bird");
        dictionary.put("ATIPICO", "Que sale de lo común");
        dictionary.put("piston", "A solid cylinder");
        dictionary.put("DISLOCA", "Saca alguna cosa de su lugar");
        dictionary.put("Arrecife", "parte del mar");
        dictionary.put("dawn", "Abajo en ingles");
        dictionary.put("RECAIDA", "Reincidencia");
        dictionary.put("fjord", "A long narrow");
        dictionary.put("ALADINO", "Uno de los tres principes");
        dictionary.put("lima", "Departamento del peru");
        dictionary.put("INSIGNIA", "Signo distintivo");
        dictionary.put("ALERTAS", "Vigilantes, cuidadosos");
        dictionary.put("LITIGIO", "Pleito judicial");
        dictionary.put("snicker", "chocolate muy dulce");
        dictionary.put("Agata", "Christie autorka kryminaw");
        dictionary.put("agora", "Palabra del imperio romano");
        dictionary.put("Alina", "Nombre de Mujer");
        dictionary.put("Amaya", "Grupo musical");
        dictionary.put("Ayala", "apaellido de mis patas");
        dictionary.put("poema", "Frases hermosas dedicadas");
        dictionary.put("Proust", "Marcel  francuski");
        dictionary.put("bala", "Parte del armamento del ejercito");
        dictionary.put("ANIONES", "Iones negativos");
        dictionary.put("babiarz", "Film uwodziciel lowelas");
        dictionary.put("Babilon", "Historia miasto wiszcych");
        dictionary.put("babulin", "Zoologia Irlandesa Mundial");
        dictionary.put("RACISMO", "Teoría sobre la supremacía de una raza sobre otras");
        dictionary.put("barbie", "Muñeca antigua");
        dictionary.put("Baltico", "Idioma del Estranjero");
        dictionary.put("RESIGNAR", "Renunciar en favor de otro");
        dictionary.put("Baggins", "Literatura Inglesa");
        dictionary.put("bagguey", "Pan de tamaño largo");
        dictionary.put("bahaizm", "Religion Rusa");
        dictionary.put("Baa", "onomatopeya de la cabra");
        dictionary.put("baby", "bebe en ingles");
        dictionary.put("baca", "Geografia starszy pasterz");
        dictionary.put("bajada", "accion de estar abajo");
        dictionary.put("baki", "Medicina Hawayana");
        dictionary.put("bank", "Palabra en Ingles");
        dictionary.put("bant", "eglarstwo pas ptna");
        dictionary.put("ballena", "pez gigante del mar");
        dictionary.put("bardo", "Innesesario o en vano");
        dictionary.put("TALADRO", "Instrumento con que se agujerea");
        dictionary.put("Bask", "Geografia");
        dictionary.put("bata", "marca de zapatos y zapatillas");
        dictionary.put("Baum", "Literatura Española");
        dictionary.put("PUNZANTE", "Ingenioso e hiriente");
        dictionary.put("bear", "cerveza en ingles");
        dictionary.put("Cabota", "Geografia cienina oddzielajca");
        dictionary.put("Cabrit", "Historia Fryderyk Kabryt");
        dictionary.put("caciki", "Gastronomia grecki sos");
        dictionary.put("llama", "auquenido peruano");
        dictionary.put("Calabaza", "verdjura para mazamorras");
        dictionary.put("caligula", "paersonaje de serie mundial");
        dictionary.put("Cambio", "accion de trazar con objetos");
        dictionary.put("Campos", "alcalde de andahuaylas");
        dictionary.put("Pamuk", "Literatura turecki");
        dictionary.put("LIMOSNA", "Dinero que se da por caridad");
        dictionary.put("computer", "computadora en ingles");
        dictionary.put("ESCRIBA", "Doctor de la Ley entre los judios");
        dictionary.put("folder", "porta hojas de los utiles");
        dictionary.put("furia", "accion de estar molesto");
        dictionary.put("Word", "hoja de texto informatico");
        dictionary.put("Wlan", "forma de conexion en redes");
        dictionary.put("woman", "mujer en ingles");
        dictionary.put("Wikipedia", "buscador con servidores mundiales");
        dictionary.put("Microsoft", "Empresa de Tecnologia");
        dictionary.put("Google", "Navegador Mundial");
        dictionary.put("Gohan", "Hijo de GOKU dragon ball z");
        dictionary.put("Root", "Super Usuario Administrador");
        dictionary.put("Trojan", "Cortafuegos antiguo");
        dictionary.put("Telnet", "Empresa de Internet");
        dictionary.put("Rana", "batrasio que vive en pantanos");
        dictionary.put("tabaco", "instrumento que sirve para fumar");
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
