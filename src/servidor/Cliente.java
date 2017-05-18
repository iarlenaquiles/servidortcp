package servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente implements Runnable {
	private Socket cliente;

	public Cliente(Socket cliente) {
		this.cliente = cliente;
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socketCliente = new Socket("127.0.0.1", 6789);
		
		Cliente cli = new Cliente(socketCliente);
		
		Thread t = new Thread(cli);
		t.start();
	}

	@Override
	public void run() {
		
		try {
			PrintStream saida;
			System.out.println("Cliente conectou");
			
			Scanner teclado = new Scanner(System.in);
			
			saida = new PrintStream(this.cliente.getOutputStream());
			
			while(teclado.hasNext()){
				saida.println(teclado.nextLine());
			}
			
			saida.close();
			teclado.close();
			this.cliente.close();
			System.out.println("terminou");
		} catch (IOException e) {
			System.out.println("Erro: " + e);
		}

	}

}
