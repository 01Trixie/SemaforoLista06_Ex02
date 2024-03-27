package view;

import java.util.concurrent.Semaphore;
import controller.PistaDecolagem;

public class Aeroporto {

	public static void main(String[] args) {
		
		int permissao = 2;
		int permissaoNorte = 1;
		int permissaoSul= 1;
		Semaphore pistaNorte = new Semaphore(permissaoNorte);
		Semaphore pistaSul = new Semaphore(permissaoSul);
		Semaphore decolar = new Semaphore(permissao);
		
		for(int idAviao = 0; idAviao < 12; idAviao++) {
			Thread tAviao = new PistaDecolagem(idAviao, pistaNorte, pistaSul, decolar);
			tAviao.start();
		}
	}

}
