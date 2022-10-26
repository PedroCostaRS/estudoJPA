package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {

		// instanciando uma pessoa através do método Pessoa com ID, nome e e-mail
		Pessoa p1 = new Pessoa(null, "Pedro", "tecpedrocosta@gmail.com");
		Pessoa p2 = new Pessoa(null, "Aniela", "AnielaTeste@gmail.com");
		Pessoa p3 = new Pessoa(null, "Rosilda", "RosildaTeste@gmail.com");
		Pessoa p4 = new Pessoa(null, "Laura", "LauraTeste@gmail.com");
		Pessoa p5 = new Pessoa(null, "Joel", "JoelTeste@gmail.com");

		// Instanciando o emf como conexão do banco de dados
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		// Instanciando o em pelos dados do emf
		EntityManager em = emf.createEntityManager();

		// inicia a transação com a base de dados
		em.getTransaction().begin();

		// faz chamada para o banco de dados
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.persist(p4);
		em.persist(p5);
		// dá commit no bd.
		em.getTransaction().commit();
		System.out.println("Salvo com sucesso"); // Após o commit exibe mensagem no console.

		// Faz busca no banco pelo Id de pessoa chamado através da classe Pessoa
		Pessoa pesquisa = em.find(Pessoa.class, 1);
		// Exibe o resultado da pesquisa listando por campos escolhidos separadamente ao
		// invés de lista
		System.out.println(pesquisa.getId());
		System.out.println(pesquisa.getNome());
		System.out.println(pesquisa.getEmail());

		// Atualização de Registro
		Pessoa atualiza = em.find(Pessoa.class, 3);
		em.getTransaction().begin();
		atualiza.setEmail("vazio@gmail.com"); // Sobrescreve email da pessoa de Id 3
		em.getTransaction().commit();

		// Para remover do BD, tem que efetuar a pesquisa para buscar o Id
		// Faz busca no banco pelo Id de pessoa chamado através da classe Pessoa
		Pessoa remover = em.find(Pessoa.class, 2);

		// Tratamento de excessão na remoção dos valores
		try {
			em.getTransaction().begin(); // Abre a transação
			em.remove(remover); // Remove o objeto listado em remover
			em.getTransaction().commit();// Commita a transação

		} catch (Exception e) {
			// Caso não encontre na base de dados essa mensagem é exibida
			System.out.println("Talvez essa pessoa já tenha sido removida anteriormente");
		}

		// encerra o em e emf
		em.close();
		emf.close();

	}

}
