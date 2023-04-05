package API.Jacto.Controller;

import API.Jacto.Cadastro.Cliente;
import API.Jacto.Cadastro.DadosClienteCadastro;
import API.Jacto.Repositories.ClienteRepository;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;


    @GetMapping
    @Transactional
    public List<DadosClienteCadastro> listar() {
        return this.repository.findAll().stream().map(DadosClienteCadastro::new).toList();
    }

    @PostMapping
    @Transactional //trabalha dentro do escopo de uma transação no banco de dados
    public ResponseEntity<DadosClienteCadastro> cadastrar(@RequestBody @Valid DadosClienteCadastro dados){
        this.repository.save(new Cliente(dados));
        return new ResponseEntity<DadosClienteCadastro> (
                dados,
                HttpStatus.CREATED
        );
    }


//    Criando rota de atualizado

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<DadosClienteCadastro> atualizar(
            @PathVariable("id") Long id,
            @RequestBody @Valid DadosClienteCadastro dados

    )  {
        var cliente = this.repository.getReferenceById(id);
        cliente.atualizarInformacoes(dados);

        return new ResponseEntity<DadosClienteCadastro> (
                dados,
                HttpStatus.OK
        );

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);

        return new ResponseEntity (
                HttpStatus.NO_CONTENT
        );
    }



    }


