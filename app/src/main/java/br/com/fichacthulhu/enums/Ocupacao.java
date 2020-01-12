package br.com.fichacthulhu.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.fichacthulhu.SkillCap;

public enum Ocupacao {


    ADVOGADO(1, "Advogado", 30, 80, Arrays.asList(new SkillCap(Atributo.EDU,4))),
    ANDARILHO(2, "Andarilho", 0, 5, Arrays.asList(new SkillCap(Atributo.EDU,2),new SkillCap(Atributo.APA,2))),
    ANTIQUARIO(3, "Antiquário", 30, 70, Arrays.asList(new SkillCap(Atributo.EDU,4))),
    ARTISTA(4, "Artista", 9, 50, Arrays.asList(new SkillCap(Atributo.EDU,2), new SkillCap(Atributo.POD,2))),
    ATLETA(5, "Atleta", 9, 70, Arrays.asList(new SkillCap(Atributo.EDU,2), new SkillCap(Atributo.DES,2))),
    AUTOR(6, "Autor", 9, 30, Arrays.asList(new SkillCap(Atributo.EDU,4))),
    BIBLIOTECARIO(7, "Bibliotecário", 9, 35, Arrays.asList(new SkillCap(Atributo.EDU,4))),
    CLERO(8, "Membro do Clero", 9, 60, Arrays.asList(new SkillCap(Atributo.EDU,4))),
    CRIMINOSO(9, "Criminoso", 5, 65, Arrays.asList(new SkillCap(Atributo.EDU,2), new SkillCap(Atributo.DES,2))),
    DETETIVE(10, "Detetive Particular", 9, 30, Arrays.asList(new SkillCap(Atributo.EDU,2), new SkillCap(Atributo.DES,2))),
    DILETANTE(11, "Diletante", 50, 99, Arrays.asList(new SkillCap(Atributo.EDU,2), new SkillCap(Atributo.APA,2))),
    ENGENHEIRO(12, "Engenheiro", 30, 60, Arrays.asList(new SkillCap(Atributo.EDU,4))),
    FANATICO(13, "Fanático", 0, 30, Arrays.asList(new SkillCap(Atributo.EDU,2), new SkillCap(Atributo.APA,2))),
    FAZENDEIRO(14, "Fazendeiro", 9, 30, Arrays.asList(new SkillCap(Atributo.EDU,2), new SkillCap(Atributo.DES,2))),
    HACKER(15, "Hacker", 10, 70, Arrays.asList(new SkillCap(Atributo.EDU,4))),
    INVESTIGADOR(16, "Investigador de Polícia", 20, 50, Arrays.asList(new SkillCap(Atributo.EDU,2), new SkillCap(Atributo.DES,2))),
    JORNALISTA(17, "Jornalista", 9, 30, Arrays.asList(new SkillCap(Atributo.EDU,4))),
    MEDICO(18, "Médico", 30, 80, Arrays.asList(new SkillCap(Atributo.EDU,4))),
    TRIBAL(19, "Membro de Tribo", 0, 15, Arrays.asList(new SkillCap(Atributo.EDU,2), new SkillCap(Atributo.DES,2))),
    MISSIONARIO(20, "Missionário", 0, 30, Arrays.asList(new SkillCap(Atributo.EDU,4))),
    MUSICO(21, "Músico", 9, 30, Arrays.asList(new SkillCap(Atributo.EDU,2), new SkillCap(Atributo.DES,2))),
    POLICIAL(22, "Oficial de Polícia", 9, 30, Arrays.asList(new SkillCap(Atributo.EDU,2), new SkillCap(Atributo.DES,2))),
    MILITAR(23, "Oficial Militar", 20, 70, Arrays.asList(new SkillCap(Atributo.EDU,2), new SkillCap(Atributo.DES,2))),
    PARAPSICOLOGO(24, "Parapsicólogo", 9, 30, Arrays.asList(new SkillCap(Atributo.EDU,4))),
    PILOTO(25, "Piloto", 20, 70, Arrays.asList(new SkillCap(Atributo.EDU,2), new SkillCap(Atributo.DES,2))),
    PROFESSOR(26, "Professor", 20, 70, Arrays.asList(new SkillCap(Atributo.EDU,4))),
    ENTRETENIMENTO(27, "Profissional de Entretenimento", 9, 70, Arrays.asList(new SkillCap(Atributo.EDU,2), new SkillCap(Atributo.APA,2))),
    SOLDADO(28, "Soldado", 9, 30, Arrays.asList(new SkillCap(Atributo.EDU,2), new SkillCap(Atributo.DES,2))),
    OUTROS(29, "Outros", 9, 30, new ArrayList<>());

    private int valor;
    private String descricao;
    private int creditoMinimo;
    private int creditoMaximo;
    private List<SkillCap> pontosSkills;

    Ocupacao(int valor, String descricao, int creditoMinimo, int creditoMaximo, List<SkillCap> pontosSkills) {
        this.valor = valor;
        this.descricao = descricao;
        this.creditoMinimo = creditoMinimo;
        this.creditoMaximo = creditoMaximo;
        this.pontosSkills = pontosSkills;
    }

    public static Ocupacao getOcupacao(String descricao) {
        for (Ocupacao item : values()) {
            if (item.descricao.equals(descricao)) {
                return item;
            }
        }
        return Ocupacao.OUTROS;
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

    public int getCreditoMinimo() {
        return creditoMinimo;
    }

    public void setCreditoMinimo(int creditoMinimo) {
        this.creditoMinimo = creditoMinimo;
    }

    public int getCreditoMaximo() {
        return creditoMaximo;
    }

    public void setCreditoMaximo(int creditoMaximo) {
        this.creditoMaximo = creditoMaximo;
    }

    public List<SkillCap> getPontosSkills() {
        return pontosSkills;
    }

    public void setPontosSkills(List<SkillCap> pontosSkills) {
        this.pontosSkills = pontosSkills;
    }
}
