package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {


		Pessoa p1 = new Pessoa(null,"Pedro","tecpedrocosta@gmail.com");
		
		// Instanciando o emf como conexão do banco de dados
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");

		// Instanciando o em pelos dados do emf
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(p1);


		em.getTransaction().commit();
		System.out.println("Pronto");

	}

}
