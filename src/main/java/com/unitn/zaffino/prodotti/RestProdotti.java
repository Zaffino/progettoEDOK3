package com.unitn.zaffino.prodotti;

import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.Connection;
import java.util.LinkedList;


/*
* Classe che gestisce l'api rest dei prodotti
*/
public class RestProdotti {

    DBdaoProdotti dBdaoProdotti;

    public RestProdotti(Connection connection){
        this.dBdaoProdotti = new DBdaoProdotti(connection);
    }

    public Route getProdotti = (Request request, Response response) -> {
        LinkedList<Prodotto> prodotti = dBdaoProdotti.selectAll();
        if (prodotti.isEmpty()){
            response.status(404);
            return response.status();
        }
        response.status(201);
        response.body(prodotti.toString());
        return response.body();
    };

    public Route getProdottibyID = (Request request, Response response) -> {
        LinkedList<Prodotto> prodotti = dBdaoProdotti.selectByID(Integer.parseInt(request.params(":id")));
        if (prodotti.isEmpty()){
            response.status(404);
            return response.status();
        }
        response.status(201);
        response.body(prodotti.toString());
        return response.body();
    };

    public Route postProdotti = (Request request, Response response) -> {
        String nome,forn_id, prezzo;
        nome = request.queryParams("nome");
        forn_id = request.queryParams("fornid");
        prezzo = request.queryParams("prezzo");

        if (dBdaoProdotti.insert(new Prodotto(nome, Integer.parseInt(forn_id),Integer.parseInt(prezzo))))
            response.status(201);
        else response.status(400);

        return response.status();
    };

    public Route deleteProdotti = (Request request, Response response) -> {

        if(dBdaoProdotti.remove(Integer.parseInt(request.params(":id")))) response.status(201);
        else response.status(400);
        return response.status();

    };


}
