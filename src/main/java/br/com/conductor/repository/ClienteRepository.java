package br.com.conductor.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.conductor.bean.Cliente;

/**
 * Classe utilizada como repositório do @Cliente
 * @author Jhone
 *
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	/**
	 * Método utilizando para buscar um cliente na base de dados a partir do cpf.
	 * @param cpf
	 * @return Retorna um objeto @Cliente
	 */
	Cliente findByCpf(String cpf);
	
}