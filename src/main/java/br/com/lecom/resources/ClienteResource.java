package br.com.lecom.resources;

import br.com.lecom.domains.entities.Cliente;
import br.com.lecom.services.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

    private final ClienteService clienteService;

    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @ApiOperation(value = "Endpoint de busca de clientes na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes encontrados com sucesso!"),
            @ApiResponse(code = 404, message = "Não foi encontrado um usuario"),
            @ApiResponse(code = 500, message = "Um erro inesperado aconteceu.")
    })
    @GetMapping(value = "/busca/{id}")
    public ResponseEntity<Cliente> buscaCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.buscar(id);
        return ResponseEntity.ok().body(cliente);
    }

    @ApiOperation(value = "Endpoint de busca de todos os clientes na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes encontrados com sucesso!"),
            @ApiResponse(code = 404, message = "Não foi encontrado um usuario"),
            @ApiResponse(code = 500, message = "Um erro inesperado aconteceu.")
    })
    @GetMapping(value = "/busca-todos")
    public ResponseEntity<List<Cliente>> buscaCliente() {
        List<Cliente> cliente = clienteService.buscarTodos();
        return ResponseEntity.ok().body(cliente);
    }

    @ApiOperation(value = "Endpoint para inserir dados de cliente na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes inserido com sucesso!"),
            @ApiResponse(code = 500, message = "Um erro inesperado aconteceu.")
    })
    @PostMapping(value = "/insere")
    public ResponseEntity<String> insereCliente(@RequestBody Cliente cliente) {
        clienteService.adicionar(cliente);
        return ResponseEntity.ok("Cliente inserido com sucesso!");
    }

    @ApiOperation(value = "Endpoint para alterar dados de cliente na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes alterado com sucesso!"),
            @ApiResponse(code = 500, message = "Um erro inesperado aconteceu.")
    })
    @PutMapping(value = "/altera")
    public ResponseEntity<String> alterarCliente(@RequestBody Cliente cliente) {
        clienteService.alterar(cliente);
        return ResponseEntity.ok("Cliente alterado com sucesso!");
    }

    @ApiOperation(value = "Endpoint para apagar dados de cliente na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes alterado com sucesso!"),
            @ApiResponse(code = 500, message = "Um erro inesperado aconteceu.")
    })
    @DeleteMapping(value = "/apaga")
    public ResponseEntity<String> apagaCliente(Long id) {
        clienteService.apagar(id);
        return ResponseEntity.ok("Cliente apagado com sucesso.");
    }
}
