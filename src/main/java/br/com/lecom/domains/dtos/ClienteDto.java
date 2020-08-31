package br.com.lecom.domains.dtos;

import br.com.lecom.domains.entities.Cliente;
import br.com.lecom.domains.enums.TipoDocumentos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDto {

    private String nome;
    private String documento;
    private TipoDocumentos tipoDocumentos;
    private Boolean clienteOuro;
    private Boolean clientePrata;

    public Cliente toEntity(ClienteDto clienteDto) {
        return Cliente.builder()
                .nome(clienteDto.getNome())
                .documento(clienteDto.getDocumento())
                .clienteOuro(clienteDto.getClienteOuro())
                .clientePrata(clienteDto.getClientePrata())
                .tipoDocumentos(clienteDto.getTipoDocumentos())
                .build();
    }
}
