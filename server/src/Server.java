import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(48807), 0);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        HttpContext context = server.createContext("/events", new AccountsEndpoint());
        context = server.createContext("/addUser", new Endpoint2());
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("Server started");
        System.out.println(context);
    }
}

