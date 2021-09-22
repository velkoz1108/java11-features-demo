package com.twang.demo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class HttpFeature {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException, TimeoutException {
//        Synchronous Example
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
//                .proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80)))
//                .authenticator(Authenticator.getDefault())
                .build();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080")).build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());


        System.out.println("--------------------------");

//        Asynchronous Example
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080"))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("file.json")))
                .build();
        CompletableFuture<Integer> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::statusCode);

        Integer code = future.get(1, TimeUnit.MINUTES);
        System.out.println("code = " + code);
    }
}
