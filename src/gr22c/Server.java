package gr22c;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        log("Start");
        int port = 10000;
        log("Server socket opening");
        ServerSocket serverSocket = new ServerSocket(port);
        log("Server socket opened");
        log("Server socket listening for incoming connections (accepting)");
        Socket client = serverSocket.accept();
        log("Connection established: " + client.getInetAddress() + ":" + client.getPort());

        log("TCP streams collecting");
        InputStream sis = client.getInputStream();
        OutputStream sos = client.getOutputStream();
        InputStreamReader sisr = new InputStreamReader(sis);
        OutputStreamWriter sosw = new OutputStreamWriter(sos);
        BufferedReader br = new BufferedReader(sisr);
        BufferedWriter bw = new BufferedWriter(sosw);


        log("HTTP request receiving");
        String request = br.readLine();
        String requestEmptyLine = br.readLine();
        if(!requestEmptyLine.isEmpty())
            throw new RuntimeException("requestEmpty is not empty");
        log("HTTP request received: " + request);

        String response = request.toUpperCase();
        log("HTTP response sending");
        bw.write(response);
        bw.newLine();
        bw.flush();
        log("HTTP response sent");


        log("TCP connection closing - socket closing");
        sis.close();
        sos.close();
        client.close();
        log("TCP connection closed - socket closed");
        log("Server socket closing");
        serverSocket.close();
        log("Server socket closed");
        log("End");
    }

    public static void log(String message){
        System.out.println("[S]: " + message);
    }
}
