package com.emmanuel.project;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.emmanuel.project.domain.Categoria;
import com.emmanuel.project.domain.Cidade;
import com.emmanuel.project.domain.Cliente;
import com.emmanuel.project.domain.Endereco;
import com.emmanuel.project.domain.Estado;
import com.emmanuel.project.domain.Produto;
import com.emmanuel.project.domain.enums.TipoCliente;
import com.emmanuel.project.repositories.CategoriaRepository;
import com.emmanuel.project.repositories.CidadeRepository;
import com.emmanuel.project.repositories.ClienteRepository;
import com.emmanuel.project.repositories.EnderecoRepository;
import com.emmanuel.project.repositories.EstadoRepository;
import com.emmanuel.project.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Paraíba");
		Estado est2 = new Estado(null, "Pernambuco");
		
		Cidade c1 = new Cidade(null, "João Pessoa", est1);
		Cidade c2 = new Cidade(null, "Recife", est2);
		Cidade c3 = new Cidade(null, "Capmina Grande", est1);
		
		est1.getCidades().addAll(Arrays.asList(c1, c3));
		est2.getCidades().addAll(Arrays.asList(c2));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Solidade", "solmaria@gmail.com", "06123178790", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("988737347", "999799173"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "81", "Casa", "Geisel", "58080123", cli1, c2);
		Endereco e2 = new Endereco(null, "Rua Inez Pedroza Soares", "162", "Apto 201", "João Paulo II", "58080321", cli1, c1);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

	}

}
