//Entidade Cliente
package API.Jacto.Cadastro;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
/**Gerar os metodos getters**/
@NoArgsConstructor
/**ele gera construtor defaul nas entidades**/
@AllArgsConstructor
/**construtor q recebe todos os campo**/
@EqualsAndHashCode(of = "id")
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String produto_interesse;



//Post
    public Cliente(DadosClienteCadastro dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.produto_interesse = dados.produto_interesse();
    }


//Atuliza as informações do cliente, está especificando qual podem ser atualizadas
    @PutMapping
    public void atualizarInformacoes(DadosClienteCadastro dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.produto_interesse() != null) {
            this.produto_interesse = dados.produto_interesse();
        }
    }
}
