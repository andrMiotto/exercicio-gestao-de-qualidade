package org.example.service.acaocorretiva;

import org.example.model.AcaoCorretiva;
import org.example.model.Falha;
import org.example.repository.AcaoCorretivaRepository;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;

public class AcaoCorretivaServiceImpl implements AcaoCorretivaService {

    FalhaRepository falhaRepository = new FalhaRepository();
    AcaoCorretivaRepository acaoCorretivaRepository = new AcaoCorretivaRepository();
    EquipamentoRepository equipamentoRepository = new EquipamentoRepository();


    @Override
    public AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acao) throws SQLException {

        acaoCorretivaRepository.criarAcao(acao);
        Falha falha = falhaRepository.buscarFalhaPorId(acao.getFalhaId());



        return acao;
    }
}
