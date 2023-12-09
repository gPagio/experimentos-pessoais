package br.com.guilhermepagio.apis.externas.model;

public class EnderecoModel {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    public String getCep() {
        return this.cep;
    }

    private String getLogradouro() {
        return logradouro;
    }

    private String getComplemento() {
        return complemento;
    }

    private String getBairro() {
        return bairro;
    }

    private String getLocalidade() {
        return localidade;
    }

    private String getUf() {
        return uf;
    }

    private String getIbge() {
        return ibge;
    }

    private String getGia() {
        return gia;
    }

    private String getDdd() {
        return ddd;
    }

    private String getSiafi() {
        return siafi;
    }
    
    @Override
    public String toString() {
        return "CEP: " + getCep() 
             + "Logradouro:" + getLogradouro()
             + "Complemento: " + getComplemento() 
             + "Bairro: " + getBairro() 
             + "Localidade: " + getLocalidade() 
             + "UF: " + getUf() 
             + "IBGE: " + getIbge() 
             + "GIA: " + getGia() 
             + "DDD: " + getDdd() 
             + "SIAFI: " + getSiafi();
    }
}
