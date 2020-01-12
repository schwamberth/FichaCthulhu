package br.com.fichacthulhu.enums;

public enum Atributo {

    FOR(1,"FOR"),
    DES(2, "DES"),
    INT(3, "INT"),
    CON(4, "CON"),
    APA(5, "APA"),
    POD(6, "POD"),
    TAM(7, "TAM"),
    SAN(8, "SAN"),
    EDU(9, "EDU");



    private int valor;
    private String descricao;

    Atributo(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static Atributo getAtributo(int valor) {
        for (Atributo item : values()) {
            if (item.valor == valor) {
                return item;
            }
        }
        return Atributo.EDU;
    }

    public static Atributo getAtributo(String descricao) {
        for (Atributo item : values()) {
            if (item.descricao.equals(descricao)) {
                return item;
            }
        }
        return Atributo.EDU;
    }
}
