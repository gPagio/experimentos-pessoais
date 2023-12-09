package br.com.guilhermepagio.apis.externas.util;

import java.io.File;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class EmailUtil {

    private static final String diretorioAnexosEmail = "D:/anexo-envio-email/";
    
    private static String emailRemetente;
    private static final String smtpHostName = "smtp.gmail.com";
    private static final Integer smtpPort = 465;
    private static final boolean sslOnConnect = true;
    
    public static MultiPartEmail criarEmail() {
        MultiPartEmail mEmail = new MultiPartEmail();
        mEmail.setHostName(smtpHostName);
        mEmail.setSmtpPort(smtpPort);
        mEmail.setAuthenticator(authenticator());
        mEmail.setSSLOnConnect(sslOnConnect);
        
        try {
            mEmail.setFrom(emailRemetente);
            
            System.out.print("Insira o email do destinatário -> ");
            String emailDestinatario = Util.scannerKeyboard.next();

            mEmail.addTo(emailDestinatario);
            mEmail.setSubject("Teste email com anexo");
            mEmail.setMsg("Olá este email é automático");
            EmailAttachment anexo = new EmailAttachment();
            String urlArquivoEncontradoEnvio = null;
            
            EnderecoUtil.imprimeEnderecoModelJson(diretorioAnexosEmail);
            urlArquivoEncontradoEnvio = escolherArquivoParaEnvio();
            anexo.setPath(urlArquivoEncontradoEnvio);
            anexo.setName("endereco.json");
            mEmail.attach(anexo);
            
            return mEmail;
        } catch (EmailException e) {
            throw new RuntimeException("Não foi possível montar o email pelo seguinte motivo: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Um erro inesperado aconteceu pelo seguinte motivo: " + e.getMessage());
        }
    }
    
    private static DefaultAuthenticator authenticator() {
        String passwordEmailRemetente;

        System.out.println("Informações do remetente:");
        System.out.print("Insira seu email ->");
        emailRemetente = Util.scannerKeyboard.next();

        System.out.print("Insira a senha de seu email ->");
        passwordEmailRemetente = Util.scannerKeyboard.next();

        DefaultAuthenticator authenticator = new DefaultAuthenticator(emailRemetente, passwordEmailRemetente);
        return authenticator;
    }

    private static String escolherArquivoParaEnvio() {
        String nomeArquivoEnvio = "";
        File verificaExistenciaArquivoEnvio = null;
        String urlArquivoEnvio = "";

        do {

            System.out.print("Insira o nome do arquivo que deseja enviar -> ");
            nomeArquivoEnvio = Util.scannerKeyboard.next();

            urlArquivoEnvio = diretorioAnexosEmail + nomeArquivoEnvio + ".json";
            verificaExistenciaArquivoEnvio = new File(urlArquivoEnvio);

            if (!verificaExistenciaArquivoEnvio.exists()) System.out.println("Arquivo não encontrado!");
            System.out.println(urlArquivoEnvio);
        } while (!verificaExistenciaArquivoEnvio.exists());
        return urlArquivoEnvio;
    }

}
