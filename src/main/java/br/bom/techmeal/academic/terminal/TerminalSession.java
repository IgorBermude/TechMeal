package br.bom.techmeal.academic.terminal;

import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.net.Socket;

public class TerminalSession implements Runnable {

    private final Socket socket;
    private final ClienteRepository clienteRepository;

    public TerminalSession(Socket socket, ClienteRepository clienteRepository) {
        this.socket = socket;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream()))) {

            while (true) {
                clearTerminal(out);
                out.write("Insira o ID do cliente");
                //out.write("Passe o cartão do cliente");
                out.flush();

                String id = in.readLine();
                if (id == null || id.equalsIgnoreCase("X")) break;

                System.out.println("ID recebido: " + id);
                //System.out.println("ID do cartão: " + id);

                Cliente cliente;
                try {
                    cliente = clienteRepository.findById(Integer.parseInt(id))
                            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
                    /*cliente = clienteRepository.findByIdCartaoCliente(id)
                            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));*/
                    consultarSaldo(in, out, cliente);
                } catch (NumberFormatException e) {
                    clienteInvalido(in, out);
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido.");
                } catch (RuntimeException e) {
                    clearTerminal(out);
                    out.write(e.getMessage() + "\n");
                    out.flush();
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Erro de comunicação: " + e.getMessage());
        }
    }

    private void consultarSaldo(BufferedReader in, BufferedWriter out, Cliente cliente) throws IOException {
        while (true) {
            clearTerminal(out);
            out.write("Saldo atual R$ " + cliente.getSaldoCliente());
            out.flush();
            String codigo = in.readLine();
            if (codigo == null || codigo.equalsIgnoreCase("X")) break;
            out.newLine();
        }
    }

    private void clienteInvalido(BufferedReader in, BufferedWriter out) throws IOException {
        while (true) {
            clearTerminal(out);
            out.write("ID de cliente não cadastrado");
            out.flush();
            String codigo = in.readLine();
            if (codigo == null || codigo.equalsIgnoreCase("X")) break;
            out.newLine();
        }
    }

    private void clearTerminal(BufferedWriter out) throws IOException {
        out.write("\033[H\033[2J");
        out.flush();
    }
}
