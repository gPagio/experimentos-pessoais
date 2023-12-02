package br.com.guilhermepagio.apis.externas.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpRequester {

    final static HttpClient client = HttpClient.newHttpClient();

    public static HttpResponse<String> dispararRequisicaoget(URI uri) throws IOException, InterruptedException {
    
        return request(client, uri);
    
    }

    private static HttpResponse<String> request(HttpClient client, URI uri)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();
    
        return client.send(request, BodyHandlers.ofString());
    
    }
}
