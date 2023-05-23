package com.unitn.zaffino;

import com.unitn.zaffino.fornitori.DBdaoFornitori;
import com.unitn.zaffino.fornitori.Fornitore;
import com.unitn.zaffino.fornitori.RestFornitori;
import com.unitn.zaffino.prodotti.DBdaoProdotti;
import com.unitn.zaffino.prodotti.Prodotto;
import com.unitn.zaffino.prodotti.RestProdotti;
import org.eclipse.jetty.util.ajax.JSON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import static spark.Spark.*;

public class Main {

    public static Connection connectToDB(){
        Connection c = null;



        try {
            String url, user, password;
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            System.out.println("inserire url del database:");
            url = reader.readLine();
            System.out.println("inserire nome utente:");
            user = reader.readLine();
            System.out.println("inserire password:");
            password = reader.readLine();

            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

        return c;
    }

    public static void main(String[] args) {


        Connection connection = connectToDB();

        RestFornitori restFornitori = new RestFornitori(connection);
        RestProdotti restProdotti = new RestProdotti(connection);


        get("/prodotti", restProdotti.getProdotti);
        get("/prodotti/:id", restProdotti.getProdottibyID);
        get("/fornitori", restFornitori.getFornitori);
        get("/fornitori/:id",restFornitori.getFornitoriByID);


        post("/prodotti",restProdotti.postProdotti);
        post("/fornitori", restFornitori.postFornitori);


        delete("/prodotti/:id",restProdotti.deleteProdotti);
        delete("/fornitori/:id", restFornitori.deleteFornitori);




        //questo API permette le chiamate get, post e delete implementate nel app precedente

    }
}
