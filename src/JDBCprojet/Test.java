package JDBCprojet;

import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
       Client c1 = new Client("ben mohamed","amine");
       clientService cs = new clientService();
       cs.create(c1);
       System.out.println("client created "+c1.toString());
       List<Client> clients = cs.findAll();
            System.out.println("All clients:");
            for (Client client : clients) {
                System.out.println(client.toString());
            }

                        Client found = cs.findById(c1.getId());
                        System.out.println("Found client by ID: " + found);

                        c1.setNom("updatedName");
                        c1.setPrenom("updatedPrenom");
                        cs.update(c1);
                        Client updated = cs.findById(c1.getId());
                        System.out.println("Updated client: " + updated);

                        cs.delete(c1);
                        Client deleted = cs.findById(c1.getId());
                        if (deleted == null) {
                            System.out.println("Client deleted successfully.");
                        } else {
                            System.out.println("Client still exists: " + deleted);
                        }
          
                    }
                }
    