package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server implements Runnable {
	public Socket cliente;

	public Server(Socket cliente) {
		this.cliente = cliente;
	}

	public static void main(String[] args) throws IOException {
		ServerSocket socketServidor = new ServerSocket(6789);
		System.out.println("Aguardando conexão com o cliente");

		while (true) {
			Socket cliente = socketServidor.accept();
			Server tratamentoMensagem = new Server(cliente);
			Thread t = new Thread(tratamentoMensagem);
			t.start();
		}
	}

	@Override
	public void run() {
		System.out.println("Cliente conectado: " + this.cliente.getInetAddress().getHostAddress());

		try {
			Scanner entrada = new Scanner(this.cliente.getInputStream());

			while (entrada.hasNext()) {
				System.out.println("Entrada: " + entrada.nextLine());
			}

			entrada.close();
			this.cliente.close();
		} catch (IOException e) {
			System.out.println("Erro: " + e);
		}
	}

}
