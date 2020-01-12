package br.com.fichacthulhu;

import br.com.fichacthulhu.enums.Atributo;

public class SkillCap {
    private Atributo atributo;
    private int multiplicador;

    public SkillCap(Atributo atributo, int multiplicador) {
        this.atributo = atributo;
        this.multiplicador = multiplicador;
    }

    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    public int getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(int multiplicador) {
        this.multiplicador = multiplicador;
    }
}
