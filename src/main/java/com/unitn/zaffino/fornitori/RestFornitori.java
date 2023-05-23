package com.unitn.zaffino.fornitori;


import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.Connection;
import java.util.LinkedList;

/*
 * Classe che gestisce l'api rest dei fornitori
 */
public class RestFornitori {

    DBdaoFornitori dBdaoFornitori;

    public RestFornitori(Connection connection){
        this.dBdaoFornitori = new DBdaoFornitori(connection);
    }



    public Route getFornitori = (Request request, Response response) ->{

        LinkedList<Fornitore> fornitori;
        fornitori = dBdaoFornitori.selectAll();

        if (fornitori.isEmpty()){
            response.status(404);
            return response.status();
        }
        response.status(201);
        response.body(fornitori.toString());
        return response.body();
    };



    public Route getFornitoriByID = (Request request, Response response) -> {
        LinkedList<Fornitore> fornitori;
        fornitori = dBdaoFornitori.selectByID(Integer.parseInt(request.params(":id")));

        if (fornitori.isEmpty()){
            response.status(404);
            return response.status();
        }
        response.status(201);
        response.body(fornitori.toString());
        return response.body();
    };


    public Route postFornitori = (Request request, Response response) -> {
        String nome,forn_id, prezzo;
        nome = request.queryParams("nome");

        if (dBdaoFornitori.insert(new Fornitore(nome))) response.status(201);
        else response.status(400);

        return response.status();
    };

    public Route deleteFornitori = (Request request, Response response) -> {
        if(dBdaoFornitori.remove(Integer.parseInt(request.params(":id")))) response.status(201);
        else response.status(400);
        return response.status();
    };

}
