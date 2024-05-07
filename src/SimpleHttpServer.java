import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        int port = 8000; // You can change the port as needed

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new StaticFileHandler()); // Serve files from the root directory
        server.setExecutor(null); // Creates a default executor
        server.start();

        System.out.println("Server is running on port " + port);
    }
}
