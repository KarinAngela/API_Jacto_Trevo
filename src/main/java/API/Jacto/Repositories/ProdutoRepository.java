package API.Jacto.Repositories;

import API.Jacto.Cadastro.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
