package org.example.service.equipamento;

import org.example.model.Equipamento;
import org.example.repository.EquipamentoRepository;

import java.sql.SQLException;

public class EquipamentoServiceImpl implements EquipamentoService {

    EquipamentoRepository equipamentoRepository = new EquipamentoRepository();


    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {

        equipamento.setStatusOperacional("OPERACIONAL");
        equipamentoRepository.criarEquipamento(equipamento);


        return equipamento;
    }

    @Override
    public Equipamento buscarEquipamentoPorId(Long id) throws SQLException {
        Equipamento equipamento = equipamentoRepository.buscarEquipamentoPorId(id);


        if (equipamento == null) {
            throw new RuntimeException("Equipamento não encontrado!");
        }

        return equipamento;
    }
}
