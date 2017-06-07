package br.com.fiap.core;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import br.com.fiap.entity.Multa;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Veiculo;
import br.com.fiap.helper.Helper;

public class Main {
	
	private static String[] perguntasPessoa = {"Nome da Pessoa: ","Email da Pessoa: "};
	private static String[] perguntasVeiculo = {"Marca Veiculo: ","Modelo Veiculo: "};
	private static String[] perguntasMulta = {"Descricao multa: ","Valor multa: "};
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Aula4AtividadeFinal");
//		Pessoa pessoa = new Pessoa();
//		Veiculo veiculo = new Veiculo();
		
		EntityManager em = emf.createEntityManager();
		
		Integer codrsp = null;
		String codrspaux = null;

		while(codrspaux == null){
			
			codrspaux = JOptionPane.showInputDialog(null,"Informe um número: 1- Inserir  2-Consultar  Outros - Consultar por ID", "DETRAN",JOptionPane.PLAIN_MESSAGE);
			
			if (codrspaux == null || codrspaux.trim().equals("")){
				JOptionPane.showMessageDialog(null,"Informe um número","Erro",JOptionPane.ERROR_MESSAGE);
				codrspaux = null;
				continue;
			}else{
				try{
					codrsp = Integer.valueOf(codrspaux);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Informe um número","Erro",JOptionPane.ERROR_MESSAGE);
					codrspaux = null;
					continue;
				}
			}
		}
		//Insercao
		if (codrsp == 1){
			
			incluir(em);
			
		}else if(codrsp == 2){ //Consulta
			
			listarPessoas(em);
			
		}else{//Buscar por ID
			codrspaux = null;
			codrsp = 0;		
			while(codrspaux == null){
				
				codrspaux = JOptionPane.showInputDialog(null,"Informe um ID de uma pessoa", "DETRAN",JOptionPane.PLAIN_MESSAGE);
				
				if (codrspaux == null || codrspaux.trim().equals("")){
					JOptionPane.showMessageDialog(null,"Informe um número","Erro",JOptionPane.ERROR_MESSAGE);
					codrspaux = null;
					continue;
				}else{
					try{
						codrsp = Integer.valueOf(codrspaux);
					}catch(Exception e){
						JOptionPane.showMessageDialog(null,"Informe um número","Erro",JOptionPane.ERROR_MESSAGE);
						codrspaux = null;
						continue;
					}
				}
			}			
			buscarPessoa(em, codrsp);			
		}
		
		em.close();
		emf.close();
		System.out.println("fim!");
		
	}
	
	
	public static void incluir(EntityManager em){
		String resposta;
		Double valorMulta;
		int idx;
		idx = 0;
		Helper dao = new Helper(em);
		Multa multa = new Multa();
		Veiculo veiculo = new Veiculo();
		Pessoa pessoa = new Pessoa();
		
		//pega as infos da pessoa
		for(;idx < perguntasPessoa.length; idx++){
			resposta = coletaRespostas(idx,'P');
			if(idx == 0){
				pessoa.setNome(resposta);
			}else{
				pessoa.setEmail(resposta);
			}
		}
		
		idx = 0;
		//pega as infos do veiculo
		for(;idx < perguntasVeiculo.length; idx++){
			resposta = coletaRespostas(idx,'V');
			if(idx == 0){
				veiculo.setMarca(resposta);
			}else{
				veiculo.setModelo(resposta);
			}
		}
		
		idx = 0;
		//pega as infos da multa
		for(;idx < perguntasVeiculo.length;){
			resposta = coletaRespostas(idx,'M');
			if(idx == 0){
				multa.setDescricao(resposta);
				idx++;
			}else{
				try{
					valorMulta = Double.valueOf(resposta);
					multa.setValor(valorMulta);
					idx++;
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Informe o valor da multa","Erro",JOptionPane.ERROR_MESSAGE);
					continue;
				}
			}
		}
		
		veiculo.getMultas().add(multa);
		pessoa.getVeiculos().add(veiculo);
		
		try {
			dao.salvar(pessoa);   
			System.out.println("Pessoa OK");
			JOptionPane.showMessageDialog(null,"Pessoa inserida com Sucesso", "DETRAN", JOptionPane.PLAIN_MESSAGE);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
//	private static void incluirPessoa(EntityManager em) {
//		Helper dao = new Helper(em);
//
//		Multa multa = new Multa();
//		multa.setDescricao("Drigir fazendo Java");
//		multa.setValor(1.00);
//
//		Multa multa2 = new Multa();
//		multa2.setDescricao("Drigir fazendo Nada");
//		multa2.setValor(2.00);
//
//		Veiculo veiculo = new Veiculo();
//		veiculo.setMarca("Ford");
//		veiculo.setModelo("Focus");
//		veiculo.getMultas().add(multa);
//		veiculo.getMultas().add(multa2);
//
//		Pessoa pessoa = new Pessoa();
//		pessoa.setNome("Everton");
//		pessoa.setEmail("everton@fiap.com");
//		pessoa.getVeiculos().add(veiculo);
//		try {
//			dao.salvar(pessoa);   
//			System.out.println("Pessoa OK");
//		}
//		catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
	
	private static void listarPessoas(EntityManager em) {
		Helper dao = new Helper(em);
		List<Pessoa> pessoas= dao.listarTodasPessoas();
		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa.toString());
			JOptionPane.showMessageDialog(null,pessoa.toString(), "DETRAN", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	private static void buscarPessoa(EntityManager em, int id) {
		Helper dao = new Helper(em);
		Pessoa p = dao.buscarPessoa(id);
		System.out.println(p.toString());
		JOptionPane.showMessageDialog(null,p.toString(), "DETRAN", JOptionPane.PLAIN_MESSAGE);
	}
	
	public static String coletaRespostas(int i, char categoria){
		
		String resposta = null;
		
		if (categoria == 'P'){
			while(resposta  == null){
				resposta = JOptionPane.showInputDialog(null,perguntasPessoa[i], "DETRAN",JOptionPane.PLAIN_MESSAGE);
				if (resposta == null || resposta.trim().equals("")){
					JOptionPane.showMessageDialog(null,"Informe a resposta!!!","Erro",JOptionPane.ERROR_MESSAGE);
					resposta = null;
					continue;
				}
			}			
		}else if(categoria == 'V'){
			while(resposta  == null){
				resposta = JOptionPane.showInputDialog(null,perguntasVeiculo[i], "DETRAN",JOptionPane.PLAIN_MESSAGE);
				if (resposta == null || resposta.trim().equals("")){
					JOptionPane.showMessageDialog(null,"Informe a resposta!!!","Erro",JOptionPane.ERROR_MESSAGE);
					resposta = null;
					continue;
				}
			}			
		}else if(categoria == 'M'){
			
			while(resposta  == null){
				resposta = JOptionPane.showInputDialog(null,perguntasMulta[i], "DETRAN",JOptionPane.PLAIN_MESSAGE);
				if (resposta == null || resposta.trim().equals("")){
					JOptionPane.showMessageDialog(null,"Informe a resposta!!!","Erro",JOptionPane.ERROR_MESSAGE);
					resposta = null;
					continue;
				}
			}			
		}		
		return resposta;		
	}
}