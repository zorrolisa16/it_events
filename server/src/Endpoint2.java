import com.sun.net.httpserver.*;
// import org.apache.commons.text.StringEscapeUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.concurrent.*;

public class Endpoint2 extends BaseEndpoint implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException{
        try {
            String requestParamValue=null;
            String requestURI = httpExchange.getRequestURI().toString();
            System.out.println(requestURI);
            System.out.println(httpExchange.getRequestMethod());
                // requestParamValue = "Val";
            if("POST".equals(httpExchange.getRequestMethod())) {
                System.out.println("AddUserEndpoint: Post handled");
                requestParamValue = handlePostRequest(httpExchange);
                System.out.println(requestParamValue);
                if(getLoginFromGetRequest(requestURI)){
                    DB2Connection item = new DB2Connection();
                    item.findUser(requestParamValue);
                    //requestParamValue = handleGetUserRequest(httpExchange, requestURI);
                }else {
                    try {
                        DB2Connection item = new DB2Connection();
                        item.addUser(requestParamValue);
                    } catch (SQLException e) {
                        setHttpExchangeResponseHeaders(httpExchange);
                        httpExchange.sendResponseHeaders(500, 0);
                        e.printStackTrace();
                    }
                }
            }
            else{
                System.out.println("UsersEndpoint: Nothing handled");
            }
            handleResponse(httpExchange,requestParamValue);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    private String handlePostRequest(HttpExchange httpExchange) throws IOException {

        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        BufferedReader reader = new BufferedReader(isr);
        int b;
        StringBuilder buf = new StringBuilder(512);
        while((b = reader.read()) != -1){
            buf.append((char) b);
        }
        reader.close();
        isr.close();
        return buf.toString();
    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue)  throws  IOException {
        OutputStream outputStream = httpExchange.getResponseBody();

        String htmlResponse = "{\"key\": \"value\"}";
        super.setHttpExchangeResponseHeaders(httpExchange);
        httpExchange.sendResponseHeaders(200, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private boolean getLoginFromGetRequest(String uri){
        boolean trans= false;
        StringTokenizer tokenizer  = new StringTokenizer(uri, "/");
        while(tokenizer.hasMoreTokens()){
            String token  = tokenizer.nextToken();
            if("login".equals(token)){
                trans=true;
            }
        }
        //////?????
        return trans;
    }

    private String handleGetUserRequest(HttpExchange httpExchange, String id) throws SQLException {
        String reqURI = httpExchange.getRequestURI().toString();
        System.out.println("Handling request URI: " + reqURI);
        DB2Connection item;
        item = new DB2Connection();
        return item.findUser(id);

    }

}