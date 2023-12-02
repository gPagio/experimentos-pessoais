package br.com.guilhermepagio.apis.externas;

import java.io.IOException;
import br.com.guilhermepagio.apis.externas.model.EnderecoModel;

public class MostraEnderecoCEPTerminal {

    public static void main(String[] args) throws IOException, InterruptedException {
        EnderecoModel.listaEndereco();
    }
}