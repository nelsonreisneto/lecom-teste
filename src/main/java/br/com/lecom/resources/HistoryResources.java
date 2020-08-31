package br.com.lecom.resources;

import br.com.lecom.services.HistoricoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historico")
public class HistoryResources {

    private final HistoricoService historicoService;

    public HistoryResources(HistoricoService historicoService) {
        this.historicoService = historicoService;
    }


}
