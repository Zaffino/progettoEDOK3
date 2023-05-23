package com.unitn.zaffino.prodotti;

import com.unitn.zaffino.dbConnection.DBdao;
import com.unitn.zaffino.fornitori.DBdaoFornitori;
import com.unitn.zaffino.fornitori.Fornitore;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class DBdaoProdotti implements DBdao<Prodotto> {
    Connection c;



    public DBdaoProdotti(Connection c){
        this.c = c;

    }



    @Override
    public LinkedList<Prodotto> selectAll() {

        LinkedList<Fornitore> listaFornitori = new DBdaoFornitori(c).selectAll();
        //LinkedList<Fornitore> listaFornitori = DatabaseDAO.getAllFornitori(c);
        LinkedList<Prodotto> listaProdotti = new LinkedList<>();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql;
            ResultSet res;
            sql = "SELECT * FROM prodotto;";
            res = stmt.executeQuery(sql);

            while (res.next()){
                int prod_id = res.getInt("id_prodotto");
                int forn_id = res.getInt("id_fornitore");
                int prezzo = res.getInt("prezzo");
                String nome = res.getString("nome");



                Prodotto p = new Prodotto(nome, prod_id, forn_id, prezzo);
                listaProdotti.add(p);
            }
            stmt.close();

        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return listaProdotti;
    }

    @Override
    public boolean insert(Prodotto p) {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "insert into prodotto (nome,prezzo, id_fornitore) " +
                    "values ('"+ p.getNome() +"','" + p.getPrezzo() +"','"  + p.getFornitore() + "')";
            stmt.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(int p) {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "delete from prodotto where id_prodotto = " + p +";";
            stmt.executeUpdate(sql);

        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public LinkedList<Prodotto> selectByID(int id) {
        LinkedList<Fornitore> listaFornitori = new DBdaoFornitori(c).selectAll();
        //LinkedList<Fornitore> listaFornitori = DatabaseDAO.getAllFornitori(c);
        LinkedList<Prodotto> listaProdotti = new LinkedList<>();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql;
            ResultSet res;
            sql = "SELECT * FROM prodotto WHERE id_prodotto = " + id +";";
            res = stmt.executeQuery(sql);

            while (res.next()){
                int prod_id = res.getInt("id_prodotto");
                int forn_id = res.getInt("id_fornitore");
                int prezzo = res.getInt("prezzo");
                String nome = res.getString("nome");

                Prodotto p = new Prodotto(nome, prod_id, forn_id, prezzo);
                listaProdotti.add(p);
            }
            stmt.close();

        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return listaProdotti;

    }
}
