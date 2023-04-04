package API.Jacto.Cadastro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record DadosCadastro(


        Long id,

        @NotBlank
        String nome,
        @NotBlank
        String culturaUtilizada,

        @NotBlank
        String areaSuportada,
        
        @NotBlank
        String imageUrl,
        
        @NotBlank
        String descricaoProduto,

        @NotNull
        LocalDate dataCadastro,
        @NotBlank
        String status) {

        public DadosCadastro(Produto produto){
                this(produto.getId(),
                        produto.getNome(),
                        produto.getCultura_utilizada(),
                        produto.getArea_suportada(),
                        produto.getImage_url(),
                        produto.getDescricao_produto(),
                        produto.getData_cadastro(),
                        produto.getStatus()
                );
        }



}
