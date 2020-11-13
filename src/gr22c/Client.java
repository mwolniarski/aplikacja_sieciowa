package gr22c;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String []args) throws IOException {
        log("Start");
        String serverName = "localhost";
//        String serverName = "gaia.cs.umass.edu";
        int serverPort = 10000;
//        int serverPort = 80;
        log("Server name resolving (DNS): " + serverName);
        InetAddress serverAddress = InetAddress.getByName(serverName);
        log("Server name resolved (DNS): " + serverAddress);
        log("TCP connection creating - socket opening");
        Socket client = new Socket(serverAddress, serverPort);
        log("TCP connection created - socket opened");

        log("TCP streams collecting");
        InputStream sis = client.getInputStream();
        OutputStream sos = client.getOutputStream();
        InputStreamReader sisr = new InputStreamReader(sis);
        OutputStreamWriter sosw = new OutputStreamWriter(sos);
        BufferedReader br = new BufferedReader(sisr);
        BufferedWriter bw = new BufferedWriter(sosw);

        String request = "GET /wireshark-labs/HTTP-wireshark-file1.html HTTP/1.1";

        log("HTTP request sending: " + request);
        bw.write(request);
        bw.newLine();
        bw.newLine();
        bw.flush();
        log("HTTP request sent: " + request);

        log("HTTP response receiving");
        String response = br.readLine();
        log("HTTP response received: " + response);

        log("TCP connection closing - socket closing");
        sis.close();
        sos.close();
        client.close();
        log("TCP connection closed - socket closed");

        log("End");
    }

    public static void log(String message){
        System.out.println("[C]: " + message);
    }
}
