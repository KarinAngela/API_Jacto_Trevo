package API.Jacto.Controller;

import API.Jacto.Cadastro.Cliente;
import API.Jacto.Cadastro.DadosCadastro;
import API.Jacto.Cadastro.DadosClienteCadastro;
import API.Jacto.Cadastro.Produto;
import API.Jacto.Repositories.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
public class TestClienteController {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClienteRepository repository;

    @Test
    public void whenGetClientes_thenReturnJsonArray() throws Exception{
        Cliente cliente = new Cliente(
                0L,
                "Cliente Teste",
                "cliente@teste.com",
                "99999999",
                "produto teste"
        );

        List<Cliente> listaClientes = Arrays.asList(cliente);
        given(repository.findAll()).willReturn(listaClientes);

        mvc.perform(get("/clientes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(cliente.getId().intValue())))
                .andExpect(jsonPath("$[0].nome", is(cliente.getNome())))
                .andExpect(jsonPath("$[0].email", is(cliente.getEmail())))
                .andExpect(jsonPath("$[0].telefone", is(cliente.getTelefone())))
                .andExpect(jsonPath("$[0].produto_interesse", is(cliente.getProduto_interesse())));
    }
    @Test
    public void whenPostClientes_thenReturnJson() throws Exception{
        Cliente cliente = new Cliente(
                0L,
                "karol marcondes",
                "karom.k@gmail.com",
                "14984022610",
                "UniPort 3030"
        );
        Cliente cadastroCliente = (cliente);
        given(repository.save(cliente)).willReturn(cliente);
        DadosClienteCadastro dadosClienteCadastro = new DadosClienteCadastro(cliente);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String dadosJson = ow.writeValueAsString(dadosClienteCadastro);

        mvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON).content(dadosJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(cliente.getId().intValue())))
                .andExpect(jsonPath("$.nome", is(cliente.getNome())))
                .andExpect(jsonPath("$.email", is(cliente.getEmail())))
                .andExpect(jsonPath("$.telefone", is(cliente.getTelefone())))
                .andExpect(jsonPath("$.produto_interesse", is(cliente.getProduto_interesse())));

    }

    @Test
    public void whenPutClientes_thenReturnJson() throws Exception {
        Cliente cliente = new Cliente(
                0L,
                "Karin",
                "kahnogueira04@gmail.com",
                "14984022356",
                "Uniport"
        );

        given(this.repository.getReferenceById(cliente.getId())).willReturn(cliente);
         DadosClienteCadastro dadosClienteCadastro = new DadosClienteCadastro(cliente);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String dadosJson = ow.writeValueAsString(dadosClienteCadastro);

        mvc.perform(put("/clientes/0").contentType(MediaType.APPLICATION_JSON).content(dadosJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(cliente.getId().intValue())))
                .andExpect(jsonPath("$.nome", is(cliente.getNome())))
                .andExpect(jsonPath("$.email", is(cliente.getEmail())))
                .andExpect(jsonPath("$.telefone", is(cliente.getTelefone())))
                .andExpect(jsonPath("$.produto_interesse", is(cliente.getProduto_interesse())));

    }

    @Test
    public void whenDeleteCliente_thenReturnNothing() throws Exception {
        doNothing().when(repository).deleteById(0L);
        mvc.perform(delete("/clientes/0"))
                .andExpect(status().isNoContent());
    }
}





