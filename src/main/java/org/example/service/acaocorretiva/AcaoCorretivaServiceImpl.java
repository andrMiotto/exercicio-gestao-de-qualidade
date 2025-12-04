package org.example.service.acaocorretiva;

import org.example.model.AcaoCorretiva;
import org.example.repository.AcaoCorretivaRepository;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;

public class AcaoCorretivaServiceImpl implements AcaoCorretivaService{

    AcaoCorretivaRepository acaoCorretivaRepository = new AcaoCorretivaRepository();
    FalhaRepository falhaRepository = new FalhaRepository();
    EquipamentoRepository equipamentoRepository = new EquipamentoRepository();


    @Override
    public AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acao) throws SQLException {

        acaoCorretivaRepository.registrarFalha(acao);
        falhaRepository.atualizarStatusFalha(acao.getFalhaId());
        equipamentoRepository.atualizarEquipamentoStatus();

        return acao;

    }
}
