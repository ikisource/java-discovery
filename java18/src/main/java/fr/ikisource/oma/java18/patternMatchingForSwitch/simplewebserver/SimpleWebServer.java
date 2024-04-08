package fr.ikisource.oma.java18.patternMatchingForSwitch.simplewebserver;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;

import java.net.InetSocketAddress;
import java.nio.file.Path;

public class SimpleWebServer {

    private final InetSocketAddress address = new InetSocketAddress(8080);
    private final Path path = Path.of("/");

    public static void main(String[] args) {

        InetSocketAddress address = new InetSocketAddress(8080);
        Path path = Path.of("/");
        HttpServer server = SimpleFileServer.createFileServer(address, path, SimpleFileServer.OutputLevel.VERBOSE);
        server.start();
    }
}
