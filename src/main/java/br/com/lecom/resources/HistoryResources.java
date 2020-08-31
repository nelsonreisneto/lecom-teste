package br.com.lecom.resources;

import br.com.lecom.domains.dtos.ClientexServicoDto;
import br.com.lecom.domains.dtos.TarefaDto;
import br.com.lecom.services.HistoricoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoryResources {

    private final HistoricoService historicoService;

    public HistoryResources(HistoricoService historicoService) {
        this.historicoService = historicoService;
    }

    @ApiOperation(value = "Endpoint para inserir dados de Ordem de serviço na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ordem inserida com sucesso!"),
            @ApiResponse(code = 500, message = "Um erro inesperado aconteceu.")
    })
    @PostMapping(value = "/criaOrdem")
    public ResponseEntity<String> criaOrdemServico(@RequestBody ClientexServicoDto clientexServicoDto) {
        historicoService.criarOrdemServico(clientexServicoDto.getNomeCliente(), clientexServicoDto.getNomeTarefa(), clientexServicoDto.getDtInicio(), clientexServicoDto.getDtFim());
        return ResponseEntity.ok("Ordem criada com sucesso!");
    }

    @ApiOperation(value = "Endpoint para inserir dados de Ordem de serviço na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ordem inserida com sucesso!"),
            @ApiResponse(code = 500, message = "Um erro inesperado aconteceu.")
    })
    @GetMapping(value = "/exibit-historico/{nomeCliente}")
    public ResponseEntity<List<TarefaDto>> exibeHistorico(@PathVariable String nomeCliente) {
        List<TarefaDto> tarefas = historicoService.exibirHistorico(nomeCliente);
        return ResponseEntity.ok().body(tarefas);
    }

}
