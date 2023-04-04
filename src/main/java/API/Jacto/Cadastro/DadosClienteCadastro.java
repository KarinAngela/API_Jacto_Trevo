//Record chaves


package API.Jacto.Cadastro;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PostMapping;

//Post
public record DadosClienteCadastro(



    Long id,

    @NotBlank
    String nome,

    @NotBlank
    @Email
    String email,
    @NotBlank
    String telefone,
    @NotBlank
    String produto_interesse){

    public DadosClienteCadastro(Cliente cliente){
        this(cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getProduto_interesse());
    }

}
