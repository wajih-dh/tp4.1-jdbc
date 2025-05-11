package JDBCprojet;

import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        clientService cs = new clientService();

        Client c1 = new Client("Ben Ali", "Ahmed");
        Client c2 = new Client("Trabelsi", "Sami");
        Client c3 = new Client("Kefi", "Rim");
        Client c4 = new Client("Mansour", "Yasmine");
        Client c5 = new Client("Saidi", "Nader");

        cs.create(c1);
        cs.create(c2);
        cs.create(c3);
        cs.create(c4);
        cs.create(c5);
        System.out.println(" 5 clients created.");



        Client client3 = cs.findById(3);
        if (client3 != null) {
            System.out.println(" Client with ID 3: " + client3);
        } else {
            System.out.println(" Client with ID 3 not found.");
        }


  
        if (client3 != null) {
            cs.delete(client3);
            System.out.println(" Client with ID 3 deleted.");
        }



        Client client2 = cs.findById(2);
        if (client2 != null) {
            client2.setNom("ModifiedNom");
            client2.setPrenom("ModifiedPrenom");
            cs.update(client2);
            System.out.println(" Client with ID 2 updated: " + client2);
        } else {
            System.out.println(" Client with ID 2 not found.");
        }


        
        List<Client> clients = cs.findAll();
        System.out.println(" Liste des clients:");
        for (Client client : clients) {
            System.out.println(client);
        }
    }
}
