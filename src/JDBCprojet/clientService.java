package JDBCprojet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class clientService implements IDao<Client> {

    @Override
   public boolean create(Client client) {
    String sql = "INSERT INTO client(nom, prenom) VALUES (?, ?)";
    try (Connection conn = connexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, client.getNom());
        stmt.setString(2, client.getPrenom());

        int affectedRows = stmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating client failed, no rows affected.");
        }

        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                client.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating client failed, no ID obtained.");
            }
        }

        return true;

    } catch (SQLException e) {
        System.out.println("Create failed: " + e.getMessage());
        return false;
    }
}


    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Connection conn = connexion.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Client client = new Client(rs.getString("nom"), rs.getString("prenom"), rs.getInt("id"));
                clients.add(client);
            }
        } catch (SQLException e) {
            System.out.println("FindAll failed: " + e.getMessage());
        }
        return clients;
    }

    @Override
    public Client findById(int id) {
        String sql = "SELECT * FROM client WHERE id = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Client(rs.getString("nom"), rs.getString("prenom"), rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            System.out.println("findById failed: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Client o) {
        String sql = "DELETE FROM client WHERE id = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, o.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Delete failed: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Client o) {
        String sql = "UPDATE client SET nom = ?, prenom = ? WHERE id = ?";
        try (Connection conn = connexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, o.getNom());
            stmt.setString(2, o.getPrenom());
            stmt.setInt(3, o.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
            return false;
        }
    }
}
