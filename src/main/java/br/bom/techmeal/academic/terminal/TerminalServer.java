package br.bom.techmeal.academic.terminal;

import br.bom.techmeal.academic.repository.ClienteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TerminalServer {

    private static final int PORTA = 5000;
    private final ExecutorService pool = Executors.newCachedThreadPool();
    private final ClienteRepository clienteRepository;

    public TerminalServer(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostConstruct
    public void iniciarServidor() {
        pool.submit(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
                System.out.println("Servidor TCP ouvindo na porta " + PORTA);
                while (true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("Microterminal conectado: " + socket.getInetAddress());
                    pool.submit(new TerminalSession(socket, clienteRepository));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
