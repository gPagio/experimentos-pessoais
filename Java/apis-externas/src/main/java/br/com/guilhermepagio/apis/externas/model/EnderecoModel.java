package br.com.guilhermepagio.apis.externas.model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.Scanner;

import br.com.guilhermepagio.apis.externas.client.HttpRequester;

public class EnderecoModel {

    public static void listaEndereco() throws IOException, InterruptedException {
        System.out.println(buscarEndereco());
    }

    private static String buscarEndereco() throws IOException, InterruptedException {

        Scanner consoleRead = new Scanner(System.in);
        String cep;

        System.out.println("Insira o CEP para busca");
        System.out.print("CEP -> ");
        cep = consoleRead.next();

        URI uri = URI.create("https://viacep.com.br/ws/" + cep + "/json/");
        System.out.println(uri);

        HttpResponse<String> endereco = HttpRequester.dispararRequisicaoget(uri);
        consoleRead.close();

        return endereco.body();
    }
}
