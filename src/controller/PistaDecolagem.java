package controller;

import java.util.concurrent.Semaphore;

public class PistaDecolagem extends Thread {
	private int idAviao;
	private Semaphore pistaNorte;
	private Semaphore  pistaSul;
	private Semaphore decolar;
	
	public PistaDecolagem(int idAviao, Semaphore pistaNorte, Semaphore pistaSul, Semaphore decolar) {
		this.idAviao = idAviao;
		this.pistaNorte = pistaNorte;
		this.pistaSul = pistaSul;
		this.decolar = decolar;
	}
	
	public void run() {
		manobra();
	}
		
	
	private void manobra() {
		int ciclo = 1;
		int cta = 0;
		int tempo = (int) ((Math.random() * 301) + 400);
		while(cta < ciclo) {
			cta += 1;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("# " +idAviao+ " está manobrando"); 
			taxiar();
		}

	}
	private void taxiar() {
		int cta = 0;
		int tempo = (int) ((Math.random() * 501) + 500);
		while(cta < 1) {
			cta+= 1;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("#" + idAviao + " está taxiando.");
			try {
				decolar.acquire();
				decolagem();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				decolar.release();
			}
		}
	}
	
	private void decolagem() { 
		int cta = 0;
		while(cta < 1) {
			int escolha = (int) ((Math.random() *1.1) + 1);
			cta += 1;
			if(escolha == 1) {
				System.out.println("# " + idAviao + " foi para a pista Norte.");
				try {
					pistaNorte.acquire();
					decolaNorte();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					System.out.println("# " + idAviao + " foi para a pista Sul.");
					pistaSul.acquire();
					decolaSul();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		afastamento();
	}
	private void decolaNorte() {
		int tempo = (int) ((Math.random() * 601) + 200);
		System.out.println("#" + idAviao + " está decolando pela pista Norte");
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void decolaSul() {
		int tempo = (int) ((Math.random() * 601) + 200);
		System.out.println("#" + idAviao + " está decolando pela pista Sul");
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	private void afastamento() {

		int tempo = (int) ((Math.random() * 301) + 500);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("#" + idAviao + " está se afastando.");
	}
	
	
}