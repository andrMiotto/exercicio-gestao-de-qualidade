package org.example.repository;

import org.example.database.Conexao;
import org.example.model.Falha;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FalhaRepository {


    public Falha criarFalha(Falha falha) throws SQLException{

        String query = "INSERT INTO Falha (equipamentoId, dataHoraOcorrencia,descricao,criticidade,status,tempoParadaHoras) VALUES (?,?,?,?,?,?)0";


        try(Connection connection = Conexao.conectar();
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setLong(1, falha.getEquipamentoId());
            stmt.setTimestamp(2, Timestamp.valueOf(falha.getDataHoraOcorrencia()));
            stmt.setString(3, falha.getDescricao());
            stmt.setString(4, falha.getCriticidade());
            stmt.setString(5, falha.getStatus());
            stmt.setBigDecimal(6,falha.getTempoParadaHoras());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                falha.setId(rs.getLong(1));
                return falha;
            }


        }



        return falha;

    }


    public List<Falha> falhasAbertas()throws SQLException{
        String query = "SELECT id,equipamentoId,dataHoraOcorrencia,descricao,criticidade,status,tempoParadaHoras WHERE criticidade = 'CRITICA' AND status = 'ABERTA';";

        List<Falha> falhas = new ArrayList<>();

        try(Connection connection = Conexao.conectar();
        PreparedStatement stmt = connection.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Long id = rs.getLong("id");


            }

        }



        return falhas;
    }


}
