package API.Jacto.Controller;


import API.Jacto.Cadastro.DadosCadastro;
import API.Jacto.Cadastro.Produto;

import API.Jacto.Repositories.ProdutoRepository;
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

import java.time.LocalDate;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProdutoController.class)
public class TestProdutoController {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProdutoRepository repository;

    @Test
    public void whenGetProdutos_thenReturnJsonArray() throws Exception{
        Produto produto = new Produto(
                0L,
                "UniPort",
                "cana, fruto",
                "500ha",
                "https://www.designinverso.com.br/images/base_imagens_jacto_uniport2.jpg",
                "usado para ganha monero",
                LocalDate.now(),
                "disponivel"
        );
        List<Produto> listaProduto = Arrays.asList(produto);
        given(repository.findAll()).willReturn(listaProduto);

        mvc.perform(get("/produtos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(produto.getId().intValue())))
                .andExpect(jsonPath("$[0].nome", is(produto.getNome())))
                .andExpect(jsonPath("$[0].culturaUtilizada", is(produto.getCultura_utilizada())))
                .andExpect(jsonPath("$[0].areaSuportada", is(produto.getArea_suportada())))
                .andExpect(jsonPath("$[0].imageUrl", is(produto.getImage_url())))
                .andExpect(jsonPath("$[0].descricaoProduto", is(produto.getDescricao_produto())))
                .andExpect(jsonPath("$[0].dataCadastro", is(produto.getData_cadastro().toString())))
                .andExpect(jsonPath("$[0].status", is(produto.getStatus())));
    }


    @Test
    public void whenPostProdutos_thenReturnJson() throws Exception{
        Produto produto = new Produto(
                0L,
                "UniPort",
                "cana, fruto",
                "500ha",
                "https://www.designinverso.com.br/images/base_imagens_jacto_uniport2.jpg",
                "usado para ganha monero",
                LocalDate.now(),
                "disponivel"

        );
        Produto cadastroProduto = (produto);
        given(repository.save(produto)).willReturn(cadastroProduto);
        DadosCadastro dadosCadastro = new DadosCadastro(produto);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String dadosJson = ow.writeValueAsString(dadosCadastro);

        mvc.perform(post("/produtos").contentType(MediaType.APPLICATION_JSON).content(dadosJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(produto.getId().intValue())))
                .andExpect(jsonPath("$.nome", is(produto.getNome())))
                .andExpect(jsonPath("$.culturaUtilizada", is(produto.getCultura_utilizada())))
                .andExpect(jsonPath("$.areaSuportada", is(produto.getArea_suportada())))
                .andExpect(jsonPath("$.imageUrl", is(produto.getImage_url())))
                .andExpect(jsonPath("$.descricaoProduto", is(produto.getDescricao_produto())))
                .andExpect(jsonPath("$.dataCadastro", is(produto.getData_cadastro().toString())))
                .andExpect(jsonPath("$.status", is(produto.getStatus())));
    }
    @Test
    public void whenPutProdutos_thenReturnJson() throws Exception {
        Produto produto = new Produto(
                0L,
                "UniPort",
                "cana, fruto",
                "500ha",
                "https://www.designinverso.com.br/images/base_imagens_jacto_uniport2.jpg",
                "usado para ganha monero",
                LocalDate.now(),
                "disponivel"

        );
        Produto cadastroProduto = (produto);
        given(this.repository.getReferenceById(produto.getId())).willReturn(cadastroProduto);
        DadosCadastro dadosCadastro = new DadosCadastro(produto);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String dadosJson = ow.writeValueAsString(dadosCadastro);

        mvc.perform(put("/produtos/0").contentType(MediaType.APPLICATION_JSON).content(dadosJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(produto.getId().intValue())))
                .andExpect(jsonPath("$.nome", is(produto.getNome())))
                .andExpect(jsonPath("$.culturaUtilizada", is(produto.getCultura_utilizada())))
                .andExpect(jsonPath("$.areaSuportada", is(produto.getArea_suportada())))
                .andExpect(jsonPath("$.imageUrl", is(produto.getImage_url())))
                .andExpect(jsonPath("$.descricaoProduto", is(produto.getDescricao_produto())))
                .andExpect(jsonPath("$.dataCadastro", is(produto.getData_cadastro().toString())))
                .andExpect(jsonPath("$.status", is(produto.getStatus())));

    }



    @Test
    public void whenDeleteProdutos_thenReturnNothing() throws Exception {
        doNothing().when(repository).deleteById(0L);
        mvc.perform(delete("/produtos/0"))
                .andExpect(status().isNoContent());
    }
}
