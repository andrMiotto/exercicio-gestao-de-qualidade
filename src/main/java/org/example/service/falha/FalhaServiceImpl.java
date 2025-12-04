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

        if(!equipamentoRepository.equipamentoExiste(falha.getEquipamentoId())){
            throw new IllegalArgumentException("Equipamento não encontrado!");
        }
        falha.setStatus("ABERTA");

        falha =  falhaRepository.criarFalha(falha);

        if(falha.getCriticidade() == "CRITICA"){
            equipamentoRepository.atualizarStatus(falha.getEquipamentoId(),"EM_MANUTENCAO");
        }

        if(falha.getId() == null){
            throw new RuntimeException("Erro ao salvar falha!");
        }



        return falha;
    }

    @Override
    public List<Falha> buscarFalhasCriticasAbertas() throws SQLException {
        return falhaRepository.falhasAbertas();
    }
}
