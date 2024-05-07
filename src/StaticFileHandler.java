import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class StaticFileHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestedFile = exchange.getRequestURI().getPath().substring(1); // Remove leading slash
        File file = new File("web/" + requestedFile);

        if (file.exists() && !file.isDirectory()) {
            exchange.sendResponseHeaders(200, file.length());
            try (OutputStream os = exchange.getResponseBody()) {
                Files.copy(file.toPath(), os);
            }
        } else {
            exchange.sendResponseHeaders(404, 0);
        }

        exchange.close();
    }
}
