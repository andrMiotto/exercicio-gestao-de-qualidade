package org.example.service.falha;

import org.example.model.Falha;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;
import java.util.List;

public class FalhaServiceImpl implements FalhaService {

    FalhaRepository falhaRepository = new FalhaRepository();
    EquipamentoRepository equipamentoRepository = new EquipamentoRepository();

    @Override
    public Falha registrarNovaFalha(Falha falha) throws SQLException {

        falha.setStatus("ABERTA");

        if (!equipamentoRepository.equipamentoExiste(falha.getEquipamentoId())) {
            throw new IllegalArgumentException("Equipamento não encontrado!");
        }


        if (falha.getCriticidade() == "CRITICA") {
            falhaRepository.registrarNovaFalha(falha);
            equipamentoRepository.atualizarStatus(falha.getEquipamentoId(),"OPERACIONAL");

        }


        return falha;

    }

    @Override
    public List<Falha> buscarFalhasCriticasAbertas() throws SQLException {
        return falhaRepository.listarFalhasAbertas();
    }
}
