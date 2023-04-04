//Minha entidade Produto.java
package API.Jacto.Cadastro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDate;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
/**Gerar os metodos getters**/
@NoArgsConstructor
/**ele gera construtor defaul nas entidades**/
@AllArgsConstructor
/**construtor q recebe todos os campo**/
@EqualsAndHashCode(of = "id")

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cultura_utilizada;
    private String area_suportada;
    private String image_url;
    private String descricao_produto;
    private LocalDate data_cadastro;
    private String status;

    public Produto(DadosCadastro dados) {
        this.nome = dados.nome();
        this.cultura_utilizada = dados.culturaUtilizada();
        this.area_suportada = dados.areaSuportada();
        this.image_url = dados.imageUrl();
        this.descricao_produto = dados.descricaoProduto();
        this.data_cadastro = dados.dataCadastro();
        this.status = dados.status();
    }

    //validação
    @PutMapping
    public void atualizarInformacoes(DadosCadastro dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.culturaUtilizada() != null){
            this.cultura_utilizada = dados.culturaUtilizada();
        }
        if (dados.areaSuportada() != null){
            this.area_suportada = dados.areaSuportada();
        }
        if (dados.imageUrl() != null) {
            this.image_url = dados.imageUrl();
        }
        if (dados.descricaoProduto() != null){
            this.descricao_produto = dados.descricaoProduto();
        }
        if (dados.dataCadastro() != null){
            this.data_cadastro = dados.dataCadastro();
        }

        if (dados.status() != null) {
            this.status = dados.status();
        }

    }
}
