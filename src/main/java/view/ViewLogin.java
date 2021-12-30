package view;
import controller.AngajatController;
import controller.ClientController;
import model.Angajat;
import model.Client;

import javax.swing.text.View;
import java.util.Scanner;
public class ViewLogin {
    private Scanner scanner;
    private AngajatController angajati;
    private ClientController clienti;

    public ViewLogin() {
        this.scanner = new Scanner(System.in);
        this.angajati = new AngajatController();
        this.clienti = new ClientController();
    }

    private String meniu(){
        String text="";
        text+="Apasati tasta 0 pentru a va deconecta\n";
        text+="Apasati tasta 1 pentru a va loga\n";
        return text;
    }

    private void login(){
        System.out.println("Introduceti statusul dumneavoastra: client sau administrator");
        String status=scanner.nextLine();
        String nume="";
        String parola="";

        if(status.equals("admin")==false && status.equals("client")==false){
            System.out.println("Nu ati introdus un status valid!");

        }
        else{
            System.out.println("Introduceti numele dumeavoastra:");
            nume=scanner.nextLine();
            System.out.println("Introduceti parola dumneavoastra:");
            parola=scanner.nextLine();

        }
        if (status.equals("admin")) {

            if (angajati.parola(nume) == null || !angajati.parola(nume).equals(parola)) {
                System.out.println("numele de utilizator sau parola este incorecta");
            } else {
                Angajat a = angajati.angajat(nume);
                ViewAdministrator administrator = new ViewAdministrator(a);
                administrator.play();
            }
        }
            else if (status.equals("client")){



            }
        }
    }




