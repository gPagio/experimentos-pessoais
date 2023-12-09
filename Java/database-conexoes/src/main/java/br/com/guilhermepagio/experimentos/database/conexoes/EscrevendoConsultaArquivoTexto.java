package br.com.guilhermepagio.experimentos.database.conexoes;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class EscrevendoConsultaArquivoTexto {

    public static String espacamentoLinhas = "---------------------------------\n";

    public static void main(String[] args) {

        Locale ptBr = new Locale("pt", "BR");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        PrintWriter printWriter;
        Connection con;
        Statement statement;
        ResultSet resultSet;
        //String url = "jdbc:postgresql://192.168.137.245:5432/postgres";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "10203040";

        System.out.println("Hello world!");

        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão bem sucedida");
            System.out.println(espacamentoLinhas);
            try {
                printWriter = new PrintWriter("database-conexoes-resultado_consulta.txt", "UTF-8");
                statement = con.createStatement();
                resultSet = statement.executeQuery("select nf.numero, nf.data_venda, inf.codigo_do_produto, tdp.nome_do_produto, inf.preco from notas_fiscais nf inner join itens_notas_fiscais inf on INF.numero = NF.numero inner join tabela_de_produtos tdp on tdp.codigo_do_produto = inf.codigo_do_produto where nf.numero = 150;");
                while (resultSet.next()) {
                    printWriter.println(("Nº Nota: " + resultSet.getInt(1)));
                    printWriter.println(("Data Venda: " + dateFormat.format(resultSet.getDate(2))));
                    printWriter.println(("Cód. Produto: " + resultSet.getInt(3)));
                    printWriter.println(("Descrição Produto: " + resultSet.getString(4)));
                    printWriter.println(("Preço:" + NumberFormat.getCurrencyInstance(ptBr).format(resultSet.getDouble(5))));
                    printWriter.print((espacamentoLinhas));
                }
                printWriter.close();
            } catch (Exception e) {
                System.out.println("Consulta não executada pelo seguinte problema: " + e.getMessage());
            } finally {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Conexao falhou pelo seguinte motivo: " + e.getMessage());
        }
        System.out.println("Fim do programa");
    }
}