package com.br.qualiti.banck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.qualiti.banck.model.Customer;

/*
 * Com a classe modelo criada, o próximo passo é criar o repositório (ou DAO: Data Access Object) 
 * que irá fornecer os métodos para as operações CRUD. 
 * Uma forma fácil de fazer isso é criar uma interface que extende a interface JpaRepository (do Spring Data):
 * A interface JpaRepository precisa de dois parâmetros do tipo Generics: o primeiro é a entidade JPA que representa a tabela 
 * e o segundo é o tipo da chave primária (o mesmo tipo do atributo id).
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
