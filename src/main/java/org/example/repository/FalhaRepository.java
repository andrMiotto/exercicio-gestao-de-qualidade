package org.example.repository;

import org.example.database.Conexao;
import org.example.model.Falha;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FalhaRepository {


    public Falha criarFalha(Falha falha) throws SQLException {

        String query = "INSERT INTO Falha (equipamentoId, dataHoraOcorrencia,descricao,criticidade,status,tempoParadaHoras) VALUES (?,?,?,?,?,?)";


        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, falha.getEquipamentoId());
            stmt.setTimestamp(2, Timestamp.valueOf(falha.getDataHoraOcorrencia()));
            stmt.setString(3, falha.getDescricao());
            stmt.setString(4, falha.getCriticidade());
            stmt.setString(5, falha.getStatus());
            stmt.setBigDecimal(6, falha.getTempoParadaHoras());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                falha.setId(rs.getLong(1));
                return falha;
            }


        }


        return falha;

    }


    public List<Falha> falhasAbertas() throws SQLException {
        List<Falha> falhas = new ArrayList<>();
        String query = "SELECT id, equipamentoId, dataHoraOcorrencia, descricao, criticidade, status, tempoParadaHoras FROM Falha WHERE criticidade = 'CRITICA' AND status = 'ABERTA';";


        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {

                long id = rs.getLong("id");
                long equipamentoId = rs.getLong("equipamentoId");
                Timestamp dataHoraOcorrencia = rs.getTimestamp("dataHoraOcorrencia");
                String descricao = rs.getString("descricao");
                String criticidade = rs.getString("criticidade");
                String status = rs.getString("status");
                BigDecimal tempoParadaHoras = rs.getBigDecimal("tempoParadaHoras");

                falhas.add(new Falha(
                        id,
                        equipamentoId,
                        dataHoraOcorrencia.toLocalDateTime(),
                        descricao,
                        criticidade,
                        status,
                        tempoParadaHoras
                ));

            }

        }

        return falhas;
    }


    public void atualizarStatusFalha(Long id) throws SQLException {
        String query = "UPDATE Falha SET status = 'RESOLVIDA' WHERE id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }

    }


}




