package co.edu.usbcali.bank.mapper;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.dto.ClienteDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.3.0.Final, compiler: Eclipse JDT (IDE) 3.17.0.v20190306-2240, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDTO clienteToClienteDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setTdocId( clienteTipoDocumentoTdocId( cliente ) );
        clienteDTO.setClieId( cliente.getClieId() );
        clienteDTO.setActivo( cliente.getActivo() );
        clienteDTO.setDireccion( cliente.getDireccion() );
        clienteDTO.setEmail( cliente.getEmail() );
        clienteDTO.setFechaCreacion( cliente.getFechaCreacion() );
        clienteDTO.setFechaModificacion( cliente.getFechaModificacion() );
        clienteDTO.setNombre( cliente.getNombre() );
        clienteDTO.setTelefono( cliente.getTelefono() );

        return clienteDTO;
    }

    @Override
    public Cliente clienteDTOToCliente(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setTipoDocumento( clienteDTOToTipoDocumento( clienteDTO ) );
        cliente.setClieId( clienteDTO.getClieId() );
        cliente.setActivo( clienteDTO.getActivo() );
        cliente.setDireccion( clienteDTO.getDireccion() );
        cliente.setEmail( clienteDTO.getEmail() );
        cliente.setFechaCreacion( clienteDTO.getFechaCreacion() );
        cliente.setFechaModificacion( clienteDTO.getFechaModificacion() );
        cliente.setNombre( clienteDTO.getNombre() );
        cliente.setTelefono( clienteDTO.getTelefono() );

        return cliente;
    }

    @Override
    public List<Cliente> toClientes(List<ClienteDTO> losClientesDTO) {
        if ( losClientesDTO == null ) {
            return null;
        }

        List<Cliente> list = new ArrayList<Cliente>( losClientesDTO.size() );
        for ( ClienteDTO clienteDTO : losClientesDTO ) {
            list.add( clienteDTOToCliente( clienteDTO ) );
        }

        return list;
    }

    @Override
    public List<ClienteDTO> toClientesDTO(List<Cliente> losClientes) {
        if ( losClientes == null ) {
            return null;
        }

        List<ClienteDTO> list = new ArrayList<ClienteDTO>( losClientes.size() );
        for ( Cliente cliente : losClientes ) {
            list.add( clienteToClienteDTO( cliente ) );
        }

        return list;
    }

    private Long clienteTipoDocumentoTdocId(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }
        TipoDocumento tipoDocumento = cliente.getTipoDocumento();
        if ( tipoDocumento == null ) {
            return null;
        }
        Long tdocId = tipoDocumento.getTdocId();
        if ( tdocId == null ) {
            return null;
        }
        return tdocId;
    }

    protected TipoDocumento clienteDTOToTipoDocumento(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        TipoDocumento tipoDocumento = new TipoDocumento();

        tipoDocumento.setTdocId( clienteDTO.getTdocId() );

        return tipoDocumento;
    }
}
