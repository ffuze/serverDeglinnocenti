package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inputDalClient;
    DataOutputStream outputVersoClient;

    public Socket attendi(){
        try{
            System.out.println("Server partito in esecuzione");
            server = new ServerSocket(1284);
            client = server.accept();
            server.close();
            inputDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputVersoClient = new DataOutputStream(client.getOutputStream());
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
        return client;
    }

    public void comunica(){
        try{
            System.out.println("Benvenuto client, scrivi una frase e la trasformo in maiuscolo. Attendo...");
            stringaRicevuta = inputDalClient.readLine();
            System.out.println("Ricevuta la stringa dal cliente: " + stringaRicevuta);
            stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("Invio la stringa modificata al client...");
            outputVersoClient.writeBytes(stringaModificata + "\n");
            System.out.println("Server: fine elaborazione, buona notte bambino");
            client.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione del server");
            System.exit(1);
        }
    }
}
