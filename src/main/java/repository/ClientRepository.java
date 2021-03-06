package repository;
import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ClientRepository {

    private String Jdbc="jdbc:mysql://localhost:3306/trainTicketingSystem";
    private String username="root";
    private  String password="root";
    private Connection connection=null;
    private Statement statement=null;


    public ClientRepository(){
        try{
            connection= DriverManager.getConnection(Jdbc,username,password);
            statement= connection.createStatement();
            //  System.out.println("S-a conectat la BD");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void executeStatement(String execute){

        try{
            statement.execute(execute);

        }catch (SQLException e){
            System.out.println("Nu s-a realizat conectarea la baza de date "+ execute);
        }
    }

    private ResultSet all(){

        executeStatement("select * from client");

        try{
            return statement.getResultSet();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public List<Client> totiClientii(){

        ResultSet set=all();
        ArrayList<Client> clienti=new ArrayList<>();
        try{
            while(set.next()){

                //public Client(String nume, String prenume, String adresa, String nr_telefon, String parola)
                clienti.add(new Client(set.getString(1), set.getString(2),set.getString(3),set.getString(4),set.getString(5)));
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return clienti;
    }

   //CRUD

    public void insert(Client client){
        String insert="";
        insert+="insert into client(nume,prenume,adresa,nr_telefon,parola) values (";
        insert+=String.format("'%s','%s','%s','%s','%s'",client.getNume(),client.getPrenume(),client.getAdresa(),client.getNr_telefon(),client.getParola());
        insert+=");";
        executeStatement(insert);

    }

    public void updateAdresa(String nume, String prenume,String adresaNoua){

        String update="";
        update=String.format("update client set adresa='%s'",adresaNoua);
        update+=String.format(" where nume='%s'", nume);
        update+=String.format(" and prenume='%s'", prenume);

        executeStatement(update);

    }


    public void updateTelefon(String nume, String prenume,String nr_telefon){

        String update="";
        update=String.format("update client set nr_telefon='%s'",nr_telefon);
        update+=String.format(" where nume='%s'", nume);
        update+=String.format(" and prenume='%s'", prenume);

        executeStatement(update);

    }

    public void delete(String numeC, String prenumeC){

        String deleteC="";
        deleteC+=String.format("delete from client where nume='%s'",numeC);
        deleteC+=String.format("and prenume='%s'",prenumeC);

        executeStatement(deleteC);
    }



}
