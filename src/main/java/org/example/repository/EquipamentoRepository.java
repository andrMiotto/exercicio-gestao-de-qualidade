package org.example.repository;

import org.example.database.Conexao;
import org.example.model.Equipamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoRepository {


    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {

        String query = "INSERT INTO Equipamento (nome,numeroDeSerie,areaSetor,statusOperacional) VALUES (?,?,?,?)";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {


            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroDeSerie());
            stmt.setString(3, equipamento.getAreaSetor());
            stmt.setString(4, equipamento.getStatusOperacional());


            stmt.executeUpdate();


            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                equipamento.setId(rs.getLong(1));

            }


        }

        return equipamento;

    }


    public Equipamento buscarEquipamentoPorId(Long id) throws SQLException {

        String query = "SELECT id,nome, numeroDeSerie, areaSetor, statusOperacional FROM Equipamento WHERE id = ?";


        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setLong(1, id);


            ResultSet rs = stmt.executeQuery();


            if (rs.next()) {
                return new Equipamento(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("numeroDeSerie"),
                        rs.getString("areaSetor"),
                        rs.getString("statusOperacional")
                );
            }

        }

        return null;

    }


    public boolean equipamentoExiste(Long id) throws SQLException {
        String query = "SELECT COUNT(0) FROM Equipamento WHERE id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getLong(1) > 0;
            }

        }
        return false;
    }


    public void atualizarStatus(Long id, String statusOPeracional) throws SQLException {
        String query = "UPDATE Equipamento SET statusOperacional = ? WHERE id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setLong(1, id);
            stmt.setString(2, statusOPeracional);
            stmt.executeUpdate();


        }

    }

}
