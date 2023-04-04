package API.Jacto.Controller;


import API.Jacto.Cadastro.DadosCadastro;
import API.Jacto.Cadastro.DadosClienteCadastro;
import API.Jacto.Cadastro.Produto;
import API.Jacto.Cliente.DadosAtualizaCliente;
import API.Jacto.Repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

@RestController
@RequestMapping("/produtos")

public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    @Transactional
    public List<DadosCadastro> listar() {
        return this.repository.findAll().stream().map(DadosCadastro::new).toList();
    }

    @PostMapping
    @Transactional //trabalha dentro do escopo de uma transação no banco de dados
    public ResponseEntity<DadosCadastro> cadastrar(@RequestBody @Valid DadosCadastro dados){
        this.repository.save(new Produto(dados));

        return new ResponseEntity<DadosCadastro> (
                dados,
                HttpStatus.CREATED
        );
    }
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<DadosCadastro> atualizar(
            @PathVariable("id") Long id,
            @RequestBody @Valid DadosCadastro dados

    )  {
        var produto = this.repository.getReferenceById(id);
        produto.atualizarInformacoes(dados);

        return new ResponseEntity<DadosCadastro> (
                dados,
                HttpStatus.OK
        );

    }
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }



}





/**@GetMapping**/
/** public String PController() {
 return "Controller To Productor";
 }**/
