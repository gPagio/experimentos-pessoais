package br.com.guilhermepagio.apis.externas.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpResponse;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.guilhermepagio.apis.externas.client.HttpRequester;
import br.com.guilhermepagio.apis.externas.model.EnderecoModel;

public class EnderecoUtil {

    public static void imprimeEnderecoTerminal() {
        System.out.println(buscarEndereco());
    }

    public static void imprimeEnderecoModelJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        EnderecoModel endereco = buscarEndereco();
        PrintWriter pw = null;

        try {
            pw = new PrintWriter("apis-externas-" + endereco.getCep().replaceAll("[^\\d]", "") + ".json");
            pw.print(gson.toJson(endereco));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Não foi possível criar o arquivo desejado pelo seguinte motivo: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Um erro não previsto aconteceu pelo seguinte motivo" + e.getMessage());
        } finally {
            pw.close();
        }
    }

    static void imprimeEnderecoModelJson(String path) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        EnderecoModel endereco = buscarEndereco();
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(path + "apis-externas-" + endereco.getCep().replaceAll("[^\\d]", "") + ".json");
            pw.print(gson.toJson(endereco));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Não foi possível criar o arquivo desejado pelo seguinte motivo: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Um erro não previsto aconteceu pelo seguinte motivo" + e.getMessage());
        } finally {
            pw.close();
        }
    }

    public static void enviaEnderecoJsonEmail() {
        MultiPartEmail emailProntoParaEnvio = EmailUtil.criarEmail();
        try {
            emailProntoParaEnvio.send();
            System.out.println("Email enviado com sucesso!");
        } catch (EmailException e) {
            e.printStackTrace();
            throw new RuntimeException("Não foi possível enviar o email pelo seguinte motivo: " + e.getMessage());
        } finally {
            System.out.println("Finalizando aplicação...");
        }
    }

    private static EnderecoModel buscarEndereco() {
        try {
            String cep = null;

            System.out.println("Insira o CEP para busca");
            System.out.print("CEP -> ");
            cep = Util.scannerKeyboard.next();

            URI uri = URI.create("https://viacep.com.br/ws/" + cep + "/json/");
            System.out.println(uri);

            HttpResponse<String> enderecoResponse = HttpRequester.dispararRequisicaoget(uri);

            return new Gson().fromJson(enderecoResponse.body(), EnderecoModel.class);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível obter o CEP a partir desse endereço pelo seguinte motivo: " + e.getMessage());
        }
    }
}
