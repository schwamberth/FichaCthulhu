package br.com.fichacthulhu.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import br.com.fichacthulhu.dao.SkillDAO;

@DatabaseTable(tableName = Skill.Metadata.table, daoClass = SkillDAO.class)
public class Skill {

    public static final class Metadata {

        public static final String table = "Skill";

        public static final String id = "id";
        public static final String idInvestigador = "idInvestigador";
        public static final String descricao = "descricao";
        public static final String evoluir = "evoluir";
        public static final String pontos = "pontos";
        public static final String meio = "meio";
        public static final String quinto = "quinto";
        public static final String ultimaAtualizacao = "ultimaAtualizacao";

    }

    @DatabaseField(columnName = Metadata.id, generatedId = true)
    private Long id;

    @DatabaseField(columnName = Metadata.idInvestigador)
    private Long idInvestigador;

    @DatabaseField(columnName = Metadata.descricao)
    private String descricao;

    @DatabaseField(columnName = Metadata.evoluir)
    private Boolean evoluir = false;

    @DatabaseField(columnName = Metadata.pontos)
    private Long pontos;

    @DatabaseField(columnName = Metadata.meio)
    private Long meio;

    @DatabaseField(columnName = Metadata.quinto)
    private Long quinto;

    @DatabaseField(columnName = Metadata.ultimaAtualizacao)
    private Date ultimaAtualizacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdInvestigador() {
        return idInvestigador;
    }

    public void setIdInvestigador(Long idInvestigador) {
        this.idInvestigador = idInvestigador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getPontos() {
        return pontos;
    }

    public void setPontos(Long pontos) {
        this.pontos = pontos;
    }

    public Long getMeio() {
        return meio;
    }

    public void setMeio(Long meio) {
        this.meio = meio;
    }

    public Long getQuinto() {
        return quinto;
    }

    public void setQuinto(Long quinto) {
        this.quinto = quinto;
    }

    public Boolean getEvoluir() {
        return evoluir;
    }

    public void setEvoluir(Boolean evoluir) {
        this.evoluir = evoluir;
    }

    public Date getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(Date ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }
}
