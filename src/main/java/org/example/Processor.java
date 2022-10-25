package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Processor of HTTP request.
 */
public class Processor {
    private final Socket socket;
    private final HttpRequest request;

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    public void process() throws IOException {
        // Print request that we received.
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.println(request.getRequestLine());
        System.out.flush();

        // To send response back to the client.
        PrintWriter output = new PrintWriter(socket.getOutputStream());

        if(request.getRequestLine().equals("GET / HTTP/1.1") ||
                request.getRequestLine().equals("GET /index.html HTTP/1.1")){
            // We are returning a simple web page now.
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Hello, world!</p></body>");
            output.println("</html>");
            output.flush();
        } else if (request.getRequestLine().equals("POST /create/itemid HTTP/1.1")){
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Page of item creation</p></body>");
            output.println("</html>");
            output.flush();
        } else if (request.getRequestLine().equals("DELETE /delete/itemid HTTP/1.1")){
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Page of item deletion</p></body>");
            output.println("</html>");
            output.flush();
        } else if (request.getRequestLine().equals("GET /exec/params HTTP/1.1")){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Page of execution</p></body>");
            output.println("</html>");
            output.flush();
        } else {
            output.println("HTTP/1.1 404 Not Found");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>404 Not Found</p></body>");
            output.println("</html>");
            output.flush();
        }


        socket.close();
    }
}
