package br.com.fichacthulhu.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import br.com.fichacthulhu.dao.InvestigadorDAO;

@DatabaseTable(tableName = Investigador.Metadata.table, daoClass = InvestigadorDAO.class)
public class Investigador implements Serializable {

    public static final class Metadata {

        public static final String table = "Investigador";

        public static final String id = "id";
        public static final String nome = "nome";
        public static final String ocupacao = "ocupacao";
        public static final String formacaoAcademica = "formacaoAcademica";
        public static final String localNascimento = "localNascimento";
        public static final String transtornosMentais = "transtornosMentais";
        public static final String sexo = "sexo";
        public static final String idade = "idade";
        public static final String residencia = "residencia";
        public static final String forca = "forca";
        public static final String destreza = "destreza";
        public static final String inteligencia = "inteligencia";
        public static final String constituicao = "constituicao";
        public static final String aparencia = "aparencia";
        public static final String poder = "poder";
        public static final String tamanho = "tamanho";
        public static final String educacao = "educacao";
        public static final String taxaMovimento = "taxaMovimento";
        public static final String sanidade = "sanidade";
        public static final String sorte = "sorte";
        public static final String magia = "magia";
        public static final String pontosVida = "pontosVida";
        public static final String descricaoPessoal = "descricaoPessoal";
        public static final String ideologia = "ideologia";
        public static final String pessoasSignificativas = "pessoasSignificativas";
        public static final String locaisImpactantes = "locaisImpactantes";
        public static final String pertencesQueridos = "pertencesQueridos";
        public static final String caracteristicas = "caracteristicas";
        public static final String ferimentosCicratrizes = "ferimentosCicratrizes";
        public static final String fobiasManias = "fobiasManias";
        public static final String tomosFeiticosArtefatos = "tomosFeiticosArtefatos";
        public static final String encontros = "encontros";
        public static final String nivelGastos = "nivelGastos";
        public static final String dinheiro = "dinheiro";
        public static final String patrimonio = "patrimonio";

    }

    @DatabaseField(columnName = Metadata.id, generatedId = true)
    private Long id;

    @DatabaseField(columnName = Metadata.nome)
    private String nome;

    @DatabaseField(columnName = Metadata.ocupacao)
    private String ocupacao;

    @DatabaseField(columnName = Metadata.formacaoAcademica)
    private String formacaoAcademica;

    @DatabaseField(columnName = Metadata.localNascimento)
    private String localNascimento;

    @DatabaseField(columnName = Metadata.transtornosMentais)
    private String transtornosMentais;

    @DatabaseField(columnName = Metadata.sexo)
    private String sexo;

    @DatabaseField(columnName = Metadata.idade)
    private Long idade;

    @DatabaseField(columnName = Metadata.residencia)
    private String residencia;

    @DatabaseField(columnName = Metadata.forca)
    private Long forca;

    @DatabaseField(columnName = Metadata.destreza)
    private Long destreza;

    @DatabaseField(columnName = Metadata.inteligencia)
    private Long inteligencia;

    @DatabaseField(columnName = Metadata.constituicao)
    private Long constituicao;

    @DatabaseField(columnName = Metadata.aparencia)
    private Long aparencia;

    @DatabaseField(columnName = Metadata.poder)
    private Long poder;

    @DatabaseField(columnName = Metadata.tamanho)
    private Long tamanho;

    @DatabaseField(columnName = Metadata.educacao)
    private Long educacao;

    @DatabaseField(columnName = Metadata.taxaMovimento)
    private Long taxaMovimento;

    @DatabaseField(columnName = Metadata.sanidade)
    private Long sanidade;

    @DatabaseField(columnName = Metadata.sorte)
    private Long sorte;

    @DatabaseField(columnName = Metadata.magia)
    private Long magia;

    @DatabaseField(columnName = Metadata.pontosVida)
    private Long pontosVida;

    @DatabaseField(columnName = Metadata.descricaoPessoal)
    private String descricaoPessoal;

    @DatabaseField(columnName = Metadata.ideologia)
    private String ideologia;

    @DatabaseField(columnName = Metadata.pessoasSignificativas)
    private String pessoasSignificativas;

    @DatabaseField(columnName = Metadata.locaisImpactantes)
    private String locaisImpactantes;

    @DatabaseField(columnName = Metadata.pertencesQueridos)
    private String pertencesQueridos;

    @DatabaseField(columnName = Metadata.caracteristicas)
    private String caracteristicas;

    @DatabaseField(columnName = Metadata.ferimentosCicratrizes)
    private String ferimentosCicratrizes;

    @DatabaseField(columnName = Metadata.fobiasManias)
    private String fobiasManias;

    @DatabaseField(columnName = Metadata.tomosFeiticosArtefatos)
    private String tomosFeiticosArtefatos;

    @DatabaseField(columnName = Metadata.encontros)
    private String encontros;

    @DatabaseField(columnName = Metadata.nivelGastos)
    private String nivelGastos;

    @DatabaseField(columnName = Metadata.dinheiro)
    private String dinheiro;

    @DatabaseField(columnName = Metadata.patrimonio)
    private String patrimonio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public String getFormacaoAcademica() {
        return formacaoAcademica;
    }

    public void setFormacaoAcademica(String formacaoAcademica) {
        this.formacaoAcademica = formacaoAcademica;
    }

    public String getTranstornosMentais() {
        return transtornosMentais;
    }

    public void setTranstornosMentais(String transtornosMentais) {
        this.transtornosMentais = transtornosMentais;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Long getIdade() {
        return idade;
    }

    public void setIdade(Long idade) {
        this.idade = idade;
    }

    public Long getForca() {
        return forca;
    }

    public void setForca(Long forca) {
        this.forca = forca;
    }

    public Long getDestreza() {
        return destreza;
    }

    public void setDestreza(Long destreza) {
        this.destreza = destreza;
    }

    public Long getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(Long inteligencia) {
        this.inteligencia = inteligencia;
    }

    public Long getConstituicao() {
        return constituicao;
    }

    public void setConstituicao(Long constituicao) {
        this.constituicao = constituicao;
    }

    public Long getAparencia() {
        return aparencia;
    }

    public void setAparencia(Long aparencia) {
        this.aparencia = aparencia;
    }

    public Long getPoder() {
        return poder;
    }

    public void setPoder(Long poder) {
        this.poder = poder;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public Long getEducacao() {
        return educacao;
    }

    public void setEducacao(Long educacao) {
        this.educacao = educacao;
    }

    public Long getTaxaMovimento() {
        return taxaMovimento;
    }

    public void setTaxaMovimento(Long taxaMovimento) {
        this.taxaMovimento = taxaMovimento;
    }

    public Long getSanidade() {
        return sanidade;
    }

    public void setSanidade(Long sanidade) {
        this.sanidade = sanidade;
    }

    public Long getMagia() {
        return magia;
    }

    public void setMagia(Long magia) {
        this.magia = magia;
    }

    public Long getPontosVida() {
        return pontosVida;
    }

    public void setPontosVida(Long pontosVida) {
        this.pontosVida = pontosVida;
    }

    public String getLocalNascimento() {
        return localNascimento;
    }

    public void setLocalNascimento(String localNascimento) {
        this.localNascimento = localNascimento;
    }

    public Long getSorte() {
        return sorte;
    }

    public void setSorte(Long sorte) {
        this.sorte = sorte;
    }

    public String getDescricaoPessoal() {
        return descricaoPessoal;
    }

    public void setDescricaoPessoal(String descricaoPessoal) {
        this.descricaoPessoal = descricaoPessoal;
    }

    public String getIdeologia() {
        return ideologia;
    }

    public void setIdeologia(String ideologia) {
        this.ideologia = ideologia;
    }

    public String getPessoasSignificativas() {
        return pessoasSignificativas;
    }

    public void setPessoasSignificativas(String pessoasSignificativas) {
        this.pessoasSignificativas = pessoasSignificativas;
    }

    public String getLocaisImpactantes() {
        return locaisImpactantes;
    }

    public void setLocaisImpactantes(String locaisImpactantes) {
        this.locaisImpactantes = locaisImpactantes;
    }

    public String getPertencesQueridos() {
        return pertencesQueridos;
    }

    public void setPertencesQueridos(String pertencesQueridos) {
        this.pertencesQueridos = pertencesQueridos;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getFerimentosCicratrizes() {
        return ferimentosCicratrizes;
    }

    public void setFerimentosCicratrizes(String ferimentosCicratrizes) {
        this.ferimentosCicratrizes = ferimentosCicratrizes;
    }

    public String getFobiasManias() {
        return fobiasManias;
    }

    public void setFobiasManias(String fobiasManias) {
        this.fobiasManias = fobiasManias;
    }

    public String getTomosFeiticosArtefatos() {
        return tomosFeiticosArtefatos;
    }

    public void setTomosFeiticosArtefatos(String tomosFeiticosArtefatos) {
        this.tomosFeiticosArtefatos = tomosFeiticosArtefatos;
    }

    public String getEncontros() {
        return encontros;
    }

    public void setEncontros(String encontros) {
        this.encontros = encontros;
    }

    public String getNivelGastos() {
        return nivelGastos;
    }

    public void setNivelGastos(String nivelGastos) {
        this.nivelGastos = nivelGastos;
    }

    public String getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(String dinheiro) {
        this.dinheiro = dinheiro;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }
}
