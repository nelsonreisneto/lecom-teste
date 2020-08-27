package br.com.lecom.resources;

import br.com.lecom.domains.entities.Tarefa;
import br.com.lecom.services.TarefaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class TarefaResource {

    private final TarefaService tarefaService;

    public TarefaResource(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @ApiOperation(value = "Endpoint de busca de Serviços na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarefas encontrados com sucesso!"),
            @ApiResponse(code = 404, message = "Não foi encontrado um usuario"),
            @ApiResponse(code = 500, message = "Um erro inesperado aconteceu.")
    })
    @GetMapping(value = "/busca/{id}")
    public ResponseEntity<Tarefa> buscaTarefa(@PathVariable Long id) {
        Tarefa tarefa = tarefaService.buscar(id);
        return ResponseEntity.ok().body(tarefa);
    }

    @ApiOperation(value = "Endpoint de busca de todos os Serviços na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarefas encontrados com sucesso!"),
            @ApiResponse(code = 404, message = "Não foi encontrado um usuario"),
            @ApiResponse(code = 500, message = "Um erro inesperado aconteceu.")
    })
    @GetMapping(value = "/busca-todos")
    public ResponseEntity<List<Tarefa>> buscaTarefa() {
        List<Tarefa> tarefas = tarefaService.buscarTodos();
        return ResponseEntity.ok().body(tarefas);
    }

    @ApiOperation(value = "Endpoint para inserir dados de Serviço na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarefas inserido com sucesso!"),
            @ApiResponse(code = 500, message = "Um erro inesperado aconteceu.")
    })
    @PostMapping(value = "/insere")
    public ResponseEntity<String> insereTarefa(@RequestBody Tarefa tarefa) {
        tarefaService.adicionar(tarefa);
        return ResponseEntity.ok("Tarefa inserido com sucesso!");
    }

    @ApiOperation(value = "Endpoint para alterar dados de Serviço na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarefas alterado com sucesso!"),
            @ApiResponse(code = 500, message = "Um erro inesperado aconteceu.")
    })
    @PutMapping(value = "/altera")
    public ResponseEntity<String> alterarTarefa(@RequestBody Tarefa tarefa) {
        tarefaService.alterar(tarefa);
        return ResponseEntity.ok("Tarefa alterado com sucesso!");
    }

    @ApiOperation(value = "Endpoint para apagar dados de Serviço na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarefas alterado com sucesso!"),
            @ApiResponse(code = 500, message = "Um erro inesperado aconteceu.")
    })
    @DeleteMapping(value = "/apaga")
    public ResponseEntity<String> apagaTarefa(Long id) {
        tarefaService.apagar(id);
        return ResponseEntity.ok("Tarefa apagado com sucesso.");
    }
}
