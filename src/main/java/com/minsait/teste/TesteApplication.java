package com.minsait.teste;

import com.minsait.teste.Algoritimos.ArquivoFormat;
import com.minsait.teste.Algoritimos.CacheLRU;
import com.minsait.teste.Algoritimos.simuladorBanco.model.Conta;
import com.minsait.teste.Algoritimos.simuladorBanco.service.BancoService;
import com.minsait.teste.Algoritimos.simuladorBanco.threads.Transferencia;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TesteApplication {
	/**
	* Descomentar a linha desejada para testar as implementações
	*/
	public static void main(String[] args) {
		SpringApplication.run(TesteApplication.class, args); /*usuarioAPI http://localhost:8080/swagger-ui/index.html#/  e http://localhost:8080/h2-console/ */
		//ExemploCacheLRU.demoCacheLRU(); 	/*Algoritimo 1 - CacheLRU*/
		//ExemploArquivoFormat.format(); 	/*Algoritimo 2 - Formatadar arquivo TXT*/
		//ExemploSimulacaoBanco.simularTransacoes(); 	/*Algoritimo 3 - Threads*/
	}


	public static class ExemploCacheLRU {

			/**
			 * Cria uma cache com o tamanho declaravel no construtor;
			 * Adiciona Elementos no cache e imprime a saida;
			 * Simula o uso da chave de valor 2, movendo-a para o ultimo lugar do cache de acordo com o LRU e imprime a saida;
			 * Adiciona um novo item, o que faz como que o ultimo valor usado seja removido, no caso sera o 1
			 * porque foi adicionado primeiro e não utilizado novamente e depois imprime a saida;
			 * Imprime o tamanho do cache;
			 * Remove o valor de chave 2 e imprime o cache novamente;
			 * Por ultimo, consulta o tamanho do cache.
			 */
		public static void demoCacheLRU() {
			CacheLRU<Integer, String> cache = new CacheLRU<>(3);

			cache.put(1, "A");
			cache.put(2, "B");
			cache.put(3, "C");
			cache.imprimeCache();

			cache.get(2);
			cache.imprimeCache();

			cache.put(4, "D");
			cache.imprimeCache();

			System.out.println("Tamanho: " + cache.size());
			cache.remove(2);

			cache.imprimeCache();
			System.out.println("Tamanho: " + cache.size());
		}
	}

	static class ExemploArquivoFormat {

		/**
		 * Executa o método que remove linhas em branco do arquivo a partir de arquivos na raiz do projeto.
		 * Se parametro 'removerEspacos' for true, além de remover as linhas em branco, também removera os espaços em branco.
		 */
		public static void format() {
			try {
				ArquivoFormat.removerLinhasEmBranco("input.txt", "output.txt", true);
				System.out.println("Arquivo formatado com sucesso!");
			} catch (Exception e) {
				System.err.println("Erro ao formatar/acessar o arquivo: " + e.getMessage());
			}
		}
	}

	public static class ExemploSimulacaoBanco {
		public static void simularTransacoes() {
			try {
				List<Conta> contas = new ArrayList<>();
				for (int i = 0; i < 5; i++) {
					contas.add(new Conta(i, 1000.0));
				}

				BancoService banco = new BancoService(contas);

				int numThreads = 5;
				int operacoesPorThread = 20;
				Thread[] threads = new Thread[numThreads];

				double saldoInicialTotal = banco.saldoTotal();

				for (int i = 0; i < numThreads; i++) {
					threads[i] = new Thread(new Transferencia(banco, operacoesPorThread));
					threads[i].start();
				}

				for (Thread t : threads) {
					t.join();
				}

				System.out.printf("Saldo total após transferências: %.2f (esperado: %.2f)%n", banco.saldoTotal(), saldoInicialTotal);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
	}

}
