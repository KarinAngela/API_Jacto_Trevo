package API.Jacto.Repositories;

import API.Jacto.Cadastro.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
