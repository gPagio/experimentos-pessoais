package br.com.guilhermepagio.apis.externas;

import java.util.regex.Pattern;

import br.com.guilhermepagio.apis.externas.util.EnderecoUtil;
import br.com.guilhermepagio.apis.externas.util.Util;

public class Programa {

    public static void main(String[] args) {

        String opcao = "";
        int quantidadeTentativasMenuPrincipal = 0;

        System.out.println(">>>>>>>>>> BEM VINDO AO BUSCADOR DE ENDEREÇOS <<<<<<<<<<");

        while (!opcao.equals("0")) {
            if (quantidadeTentativasMenuPrincipal > 0 || quantidadeTentativasMenuPrincipal > 0 && Pattern.matches("^[0-9]+", opcao) == false)
                System.out.print("Insira apenas opções do menu! ");

            System.out.println("Escolha uma opção abaixo:");
            System.out.println("1 -> Mostrar endereço no terminal");
            System.out.println("2 -> Imprimir endereço em um arquivo json");
            System.out.println("3 -> Enviar endereço por email");
            System.out.println("0 -> Sair do programa");
            System.out.print("Digite aqui -> ");
            opcao = Util.scannerKeyboard.next().trim();

            switch (opcao) {
                case "1":
                    EnderecoUtil.imprimeEnderecoTerminal();
                    quantidadeTentativasMenuPrincipal = 0;
                    break;
                case "2":
                    EnderecoUtil.imprimeEnderecoModelJson();
                    quantidadeTentativasMenuPrincipal = 0;
                case "3":
                    EnderecoUtil.enviaEnderecoJsonEmail();
                    quantidadeTentativasMenuPrincipal = 0;
                    break;
                case "0":
                    System.out.println("Obrigado por usar nossa solução! Volte sempre!");
                    System.out.println("Saindo...");
                    quantidadeTentativasMenuPrincipal = 0;
                    break;
                default:
                    System.out.println("Opção não encontrada!");
                    quantidadeTentativasMenuPrincipal++;
                    break;
            }
        }

    }
}
