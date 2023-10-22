import java.io.IOException;
import java.net.URI;

class StringServerHandler implements URLHandler {
    String response = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/add-message")) {
            String query = url.getQuery();
            if (query != null) {
                String[] parameters = query.split("=");
                if (parameters.length == 2 && parameters[0].equals("s")) {
                    String message = parameters[1];
                    response += message;
                    return response;
                }
            }
        }
        return "Invalid request. Usage: /add-message?s=<string>";
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new StringServerHandler());
    }
}
