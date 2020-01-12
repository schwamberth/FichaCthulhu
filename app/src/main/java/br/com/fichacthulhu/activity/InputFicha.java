package br.com.fichacthulhu.activity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.ormlite.annotations.OrmLiteDao;
import org.apache.commons.lang3.StringUtils;

import br.com.fichacthulhu.DatabaseHelper;
import br.com.fichacthulhu.R;
import br.com.fichacthulhu.SkillCap;
import br.com.fichacthulhu.dao.InvestigadorDAO;
import br.com.fichacthulhu.dao.SkillDAO;
import br.com.fichacthulhu.enums.Atributo;
import br.com.fichacthulhu.enums.Ocupacao;
import br.com.fichacthulhu.model.Investigador;
import br.com.fichacthulhu.model.Skill;

@EActivity(R.layout.layout_input_ficha)
@OptionsMenu(R.menu.menu_ficha_personagem)
public class InputFicha extends AppCompatActivity {

    @Extra
    @InstanceState
    Investigador investigador;

    @ViewById
    EditText editTextNome;

    @ViewById
    EditText editTextFormacaoAcademica;

    @ViewById
    EditText editTextLocalNascimento;

    @ViewById
    EditText editTextResidencia;

    @ViewById
    EditText editTextTranstornosMentais;

    @ViewById
    EditText editTextSexo;

    @ViewById
    EditText editTextIdade;

    @ViewById
    Spinner spinnerSkill;

    @ViewById
    Spinner spinnerOcupacao;

    @ViewById
    EditText editTextFOR;

    @ViewById
    TextView textViewFORMeio;

    @ViewById
    TextView textViewFORQuinto;

    @ViewById
    EditText editTextDES;

    @ViewById
    TextView textViewDESMeio;

    @ViewById
    TextView textViewDESQuinto;

    @ViewById
    EditText editTextINT;

    @ViewById
    TextView textViewINTMeio;

    @ViewById
    TextView textViewINTQuinto;

    @ViewById
    EditText editTextCON;

    @ViewById
    TextView textViewCONMeio;

    @ViewById
    TextView textViewCONQuinto;

    @ViewById
    EditText editTextAPA;

    @ViewById
    TextView textViewAPAMeio;

    @ViewById
    TextView textViewAPAQuinto;

    @ViewById
    EditText editTextPOD;

    @ViewById
    TextView textViewPODMeio;

    @ViewById
    TextView textViewPODQuinto;

    @ViewById
    EditText editTextTAM;

    @ViewById
    TextView textViewTAMMeio;

    @ViewById
    TextView textViewTAMQuinto;

    @ViewById
    EditText editTextEDU;

    @ViewById
    TextView textViewEDUMeio;

    @ViewById
    TextView textViewEDUQuinto;

    @ViewById
    EditText editTextMovimento;

    @ViewById
    EditText editTextSanidade;

    @ViewById
    SeekBar seekBarSanidade;

    @ViewById
    EditText editTextMagia;

    @ViewById
    SeekBar seekBarMagia;

    @ViewById
    EditText editTextPontosVida;

    @ViewById
    SeekBar seekBarPontosVida;

    @ViewById
    EditText editTextPontosSorte;

    @ViewById
    SeekBar seekBarPontosSorte;

    @ViewById
    EditText editTextPontosSkillTotal;

    @ViewById
    EditText editTextPontosSkill;

    @ViewById
    SeekBar seekBarPontosSkill;

    @ViewById
    EditText editTextPontosSkillUsados;

    @ViewById
    EditText editTextPontosSkillRestantes;

    @OrmLiteDao(helper = DatabaseHelper.class)
    SkillDAO skillDAO;

    @OrmLiteDao(helper = DatabaseHelper.class)
    InvestigadorDAO investigadorDAO;

    String[] ocupacoes;
    String[] skills;
    int quantidadePontosSkill = 0;
    String ocupacao = "";
    static final int RequestListSkills = 1;

    //region Dados a serem salvos no botão salvar
    @InstanceState
    String nome;

    @InstanceState
    String formacaoAcademica;

    @InstanceState
    String localNascimento;

    @InstanceState
    String transtornosMentais;

    @InstanceState
    String sexo;

    @InstanceState
    Long idade;

    @InstanceState
    Long pontosSanidade;

    @InstanceState
    Long pontosMagia;

    @InstanceState
    Long pontosVida;

    @InstanceState
    Long idInvestigador;
    //endregion

    @AfterViews
    protected void iniciar() {
        if (investigador == null) {
            investigador = investigadorDAO.criar();
            idInvestigador = investigador.getId();
        } else {
            idInvestigador = investigador.getId();
            preencherInvestigador();
        }
        skills = getResources().getStringArray(R.array.skills_gerais);
        spinnerSkill.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, skills));

        ocupacoes = getResources().getStringArray(R.array.ocupacoes);
        spinnerOcupacao.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ocupacoes));

        configurarListeners(idInvestigador);
    }

    @Click
    protected void imageViewSubtrairSanidade() {
        subtrairSeekbar(seekBarSanidade, editTextSanidade, 0);
    }

    @Click
    protected void imageViewSomarSanidade() {
        somarSeekBar(seekBarSanidade, editTextSanidade, 100);
    }

    @Click
    protected void imageViewSubtrairMagia() {
        subtrairSeekbar(seekBarMagia, editTextMagia, 0);
    }

    @Click
    protected void imageViewSomarMagia() {
        somarSeekBar(seekBarMagia, editTextMagia, 24);
    }

    @Click
    protected void imageViewSubtrairPontosVida() {
        subtrairSeekbar(seekBarPontosVida, editTextPontosVida, -2);
    }

    @Click
    protected void imageViewSomarPontosVida() {
        somarSeekBar(seekBarPontosVida, editTextPontosVida, 20);
    }

    @Click
    protected void imageViewSubtrairPontosSorte() {
        subtrairSeekbar(seekBarPontosSorte, editTextPontosSorte, 1);
    }

    @Click
    protected void imageViewSomarPontosSorte() {
        somarSeekBar(seekBarPontosSorte, editTextPontosSorte, 99);
    }

    @Click
    protected void imageViewSubtrairPontosSkill() {
        subtrairSeekbar(seekBarPontosSkill, editTextPontosSkill, 0);
    }

    @Click
    protected void imageViewSomarPontosSkill() {
        somarSeekBar(seekBarPontosSkill, editTextPontosSkill, seekBarPontosSkill.getMax());
    }

    @Click
    protected void imageViewAdicionarSkill() {
        adicionarSkill(spinnerSkill, seekBarPontosSkill, editTextPontosSkillRestantes, idInvestigador);
    }

    private void adicionarSkill(Spinner spinner, SeekBar seekBar, EditText editTextPontosRestantes, Long idInvestigador) {
        calcularPontosSkillUsados(idInvestigador);
        if (skillJaExiste(idInvestigador, (String) spinner.getSelectedItem())) {
            Toast.makeText(this, "Skill já selecionada. Escolha outra.", Toast.LENGTH_SHORT).show();
            return;
        }

        Integer pontosRestantes = Integer.valueOf(editTextPontosRestantes.getText().toString());
        if (pontosRestantes < 1) {
            Toast.makeText(this, "Totos pontos de skill já foram usados.", Toast.LENGTH_SHORT).show();
            return;
        }

        Skill skill = skillDAO.criar();
        skill.setIdInvestigador(idInvestigador);
        skill.setDescricao((String) spinner.getSelectedItem());
        skill.setPontos((long) seekBar.getProgress());
        skill.setMeio(Math.floorDiv(skill.getPontos(), 2));
        skill.setQuinto(Math.floorDiv(skill.getPontos(), 5));

        skillDAO.salvar(skill);
//        skills.add(skill);
        calcularPontosSkillUsados(idInvestigador);
        Toast.makeText(this, "Skill adicionada: " + skill.getDescricao(), Toast.LENGTH_LONG).show();
    }

    @ItemSelect
    protected void spinnerOcupacao(boolean somethingSelected, String ocupacao) {
        if (somethingSelected) {
            this.ocupacao = ocupacao;
            calcularPontosSkill(ocupacao, idInvestigador);
        }
    }

    @OptionsItem(R.id.item_menu_salvar)
    protected void actionItemMenuSalvar() {
        Toast.makeText(this, "Comando para salvar personagem", Toast.LENGTH_SHORT).show();

        nome = editTextNome.getText().toString();
        formacaoAcademica = editTextFormacaoAcademica.getText().toString();
        localNascimento = editTextLocalNascimento.getText().toString();
        transtornosMentais = editTextTranstornosMentais.getText().toString();
        sexo = editTextSexo.getText().toString();

        if (StringUtils.isNotBlank(editTextIdade.getText().toString())) {
            idade = Long.valueOf(editTextIdade.getText().toString());
        } else {
            idade = 0L;
        }

        pontosSanidade = Long.valueOf(seekBarSanidade.getProgress());
        pontosMagia = Long.valueOf(seekBarMagia.getProgress());
        pontosVida = Long.valueOf(seekBarPontosVida.getProgress());

        investigador.setNome(nome);
        investigador.setFormacaoAcademica(formacaoAcademica);
        investigador.setLocalNascimento(localNascimento);
        investigador.setResidencia(editTextResidencia.getText().toString());
        investigador.setTranstornosMentais(transtornosMentais);
        investigador.setSexo(sexo);
        investigador.setIdade(idade);
        investigador.setSanidade(pontosSanidade);
        investigador.setMagia(pontosMagia);
        investigador.setPontosVida(pontosVida);
        investigador.setSorte(Long.valueOf(seekBarPontosSorte.getProgress()));
        investigador.setForca(Long.valueOf(editTextFOR.getText().toString()));
        investigador.setDestreza(Long.valueOf(editTextDES.getText().toString()));
        investigador.setInteligencia(Long.valueOf(editTextINT.getText().toString()));
        investigador.setConstituicao(Long.valueOf(editTextCON.getText().toString()));
        investigador.setAparencia(Long.valueOf(editTextAPA.getText().toString()));
        investigador.setPoder(Long.valueOf(editTextPOD.getText().toString()));
        investigador.setTamanho(Long.valueOf(editTextTAM.getText().toString()));
        investigador.setEducacao(Long.valueOf(editTextEDU.getText().toString()));
        investigador.setOcupacao(ocupacao);
        if (StringUtils.isNotEmpty(editTextMovimento.getText().toString())) {
            investigador.setTaxaMovimento(Long.valueOf(editTextMovimento.getText().toString()));
        } else {
            investigador.setTaxaMovimento(0L);
        }

        investigadorDAO.salvar(investigador);

        //salvarPersonagem();
    }

    public boolean skillJaExiste(Long idInvestigador, String descricaoSkill) {
//        for (Skill skill : skillsSelecionadas) {
//            if (skill.getDescricao().equals(descricao)) {
//                return true;
//            }
//        }
        return skillDAO.jaSelecionada(idInvestigador, descricaoSkill);
    }

    private void configurarListeners(Long idInvestigador) {
        atualizarControleSeekbar(editTextSanidade, seekBarSanidade);
        atualizarControleSeekbar(editTextMagia, seekBarMagia);
        atualizarControleSeekbar(editTextPontosVida, seekBarPontosVida);
        atualizarControleSeekbar(editTextPontosSorte, seekBarPontosSorte);
        atualizarControleSeekbar(editTextPontosSkill, seekBarPontosSkill);
        atualizarAtributo(editTextFOR, textViewFORMeio, textViewFORQuinto, Atributo.FOR, idInvestigador);
        atualizarAtributo(editTextDES, textViewDESMeio, textViewDESQuinto, Atributo.DES, idInvestigador);
        atualizarAtributo(editTextINT, textViewINTMeio, textViewINTQuinto, Atributo.INT, idInvestigador);
        atualizarAtributo(editTextCON, textViewCONMeio, textViewCONQuinto, Atributo.CON, idInvestigador);
        atualizarAtributo(editTextAPA, textViewAPAMeio, textViewAPAQuinto, Atributo.APA, idInvestigador);
        atualizarAtributo(editTextPOD, textViewPODMeio, textViewPODQuinto, Atributo.POD, idInvestigador);
        atualizarAtributo(editTextTAM, textViewTAMMeio, textViewTAMQuinto, Atributo.TAM, idInvestigador);
        atualizarAtributo(editTextEDU, textViewEDUMeio, textViewEDUQuinto, Atributo.EDU, idInvestigador);
    }

    private void somarSeekBar(SeekBar seekBar, EditText editText, int maximo) {
        if (seekBar.getProgress() < maximo) {
            seekBar.setProgress(seekBar.getProgress() + 1);
            editText.setText(String.valueOf(seekBar.getProgress()));
        }
    }

    private void subtrairSeekbar(SeekBar seekBar, EditText editText, int minimo) {
        if (seekBar.getProgress() > minimo) {
            seekBar.setProgress(seekBar.getProgress() - 1);
            editText.setText(String.valueOf(seekBar.getProgress()));
        }
    }

    private void atualizarControleSeekbar(EditText editText, SeekBar seekBar) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (StringUtils.isNotBlank(editText.getText().toString())) {
                    seekBar.setProgress(Integer.parseInt(editText.getText().toString()));
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                editText.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void atualizarControleSeekbarSkill(EditText editText, SeekBar seekBar) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (StringUtils.isNotBlank(editText.getText().toString())) {
                    seekBar.setProgress(Integer.parseInt(editText.getText().toString()));
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                editText.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void atualizarAtributo(EditText atributo, TextView textViewMeio, TextView textViewQuinto, Atributo tipoAtributo, Long idInvestigador) {
        atributo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (StringUtils.isNotBlank(atributo.getText().toString())) {
                    Long valor = Long.valueOf(atributo.getText().toString());
                    Long meio = Math.floorDiv(valor, 2);
                    Long quinto = Math.floorDiv(valor, 5);

                    textViewMeio.setText(String.valueOf(meio));
                    textViewQuinto.setText(String.valueOf(quinto));
//                    if (tipoAtributo.getDescricao().equals("FOR")) {
//                        FOR = valor;
//                    } else if (tipoAtributo.getDescricao().equals("DES")) {
//                        DES = valor;
//                    } else if (tipoAtributo.getDescricao().equals("INT")) {
//                        INT = valor;
//                    } else if (tipoAtributo.getDescricao().equals("CON")) {
//                        CON = valor;
//                    } else if (tipoAtributo.getDescricao().equals("APA")) {
//                        APA = valor;
//                    } else if (tipoAtributo.getDescricao().equals("POD")) {
//                        POD = valor;
//                    } else if (tipoAtributo.getDescricao().equals("TAM")) {
//                        TAM = valor;
//                    } else if (tipoAtributo.getDescricao().equals("EDU")) {
//                        EDU = valor;
//                    }
                } else {
                    textViewMeio.setText("");
                    textViewQuinto.setText("");
                }
                calcularPontosSkill(ocupacao, idInvestigador);
            }
        });
    }

    private void calcularPontosSkill(String ocupacao, Long idInvestigador) {
        Ocupacao selecionada = Ocupacao.getOcupacao(ocupacao);
        if (selecionada.getValor() != 29) {
            quantidadePontosSkill = 0;
            for (SkillCap skillCap : selecionada.getPontosSkills()) {
                if (skillCap.getAtributo().getDescricao().equals("FOR") && StringUtils.isNotBlank(editTextFOR.getText().toString())) {
                    quantidadePontosSkill += Integer.parseInt(editTextFOR.getText().toString()) * skillCap.getMultiplicador();
                } else if (skillCap.getAtributo().getDescricao().equals("DES") && StringUtils.isNotBlank(editTextDES.getText().toString())) {
                    quantidadePontosSkill += Integer.parseInt(editTextDES.getText().toString()) * skillCap.getMultiplicador();
                } else if (skillCap.getAtributo().getDescricao().equals("INT") && StringUtils.isNotBlank(editTextINT.getText().toString())) {
                    quantidadePontosSkill += Integer.parseInt(editTextINT.getText().toString()) * skillCap.getMultiplicador();
                } else if (skillCap.getAtributo().getDescricao().equals("CON") && StringUtils.isNotBlank(editTextCON.getText().toString())) {
                    quantidadePontosSkill += Integer.parseInt(editTextCON.getText().toString()) * skillCap.getMultiplicador();
                } else if (skillCap.getAtributo().getDescricao().equals("APA") && StringUtils.isNotBlank(editTextAPA.getText().toString())) {
                    quantidadePontosSkill += Integer.parseInt(editTextAPA.getText().toString()) * skillCap.getMultiplicador();
                } else if (skillCap.getAtributo().getDescricao().equals("POD") && StringUtils.isNotBlank(editTextPOD.getText().toString())) {
                    quantidadePontosSkill += Integer.parseInt(editTextPOD.getText().toString()) * skillCap.getMultiplicador();
                } else if (skillCap.getAtributo().getDescricao().equals("TAM") && StringUtils.isNotBlank(editTextTAM.getText().toString())) {
                    quantidadePontosSkill += Integer.parseInt(editTextTAM.getText().toString()) * skillCap.getMultiplicador();
                } else if (skillCap.getAtributo().getDescricao().equals("EDU") && StringUtils.isNotBlank(editTextEDU.getText().toString())) {
                    quantidadePontosSkill += Integer.parseInt(editTextEDU.getText().toString()) * skillCap.getMultiplicador();
                }
            }
        } else {
            quantidadePontosSkill = 0;
        }
        editTextPontosSkillTotal.setText(String.valueOf(quantidadePontosSkill));
        calcularPontosSkillUsados(idInvestigador);
    }

    private void calcularPontosSkillUsados(Long idInvestigador) {
        Long quantidadePontos = skillDAO.getSumPontos(idInvestigador);
//        for (Skill skill : skillsSelecionadas) {
//            quantidadePontos += skill.getPontos();
//        }
        editTextPontosSkillUsados.setText(String.valueOf(quantidadePontos));
        calcularPontosSkillRestantes(idInvestigador);
    }

    private void calcularPontosSkillRestantes(Long idInvestigador) {
        Long pontosUsados = skillDAO.getSumPontos(idInvestigador);
        Long pontosTotais = Long.valueOf(editTextPontosSkillTotal.getText().toString());
        editTextPontosSkillRestantes.setText(String.valueOf(pontosTotais - pontosUsados));
        if (pontosTotais - pontosUsados < 99) {
            seekBarPontosSkill.setMax((int) (pontosTotais - pontosUsados));
        } else {
            seekBarPontosSkill.setMax(99);
        }
    }

    private void preencherInvestigador() {
        editTextNome.setText(investigador.getNome());
        spinnerOcupacao.setSelection(Ocupacao.getOcupacao(investigador.getOcupacao()).getValor());
        editTextFormacaoAcademica.setText(investigador.getFormacaoAcademica());
        editTextLocalNascimento.setText(investigador.getLocalNascimento());
        editTextResidencia.setText(investigador.getResidencia());
        editTextTranstornosMentais.setText(investigador.getTranstornosMentais());
        editTextSexo.setText(investigador.getSexo());
        editTextIdade.setText(String.valueOf(investigador.getIdade()));

        editTextFOR.setText(String.valueOf(investigador.getForca()));
        textViewFORMeio.setText(String.valueOf(calcularMeio(investigador.getForca())));
        textViewFORQuinto.setText(String.valueOf(calcularQuinto(investigador.getForca())));

        editTextDES.setText(String.valueOf(investigador.getDestreza()));
        textViewDESMeio.setText(String.valueOf(calcularMeio(investigador.getDestreza())));
        textViewDESQuinto.setText(String.valueOf(calcularQuinto(investigador.getDestreza())));

        editTextINT.setText(String.valueOf(investigador.getInteligencia()));
        textViewINTMeio.setText(String.valueOf(calcularMeio(investigador.getInteligencia())));
        textViewINTQuinto.setText(String.valueOf(calcularQuinto(investigador.getInteligencia())));

        editTextCON.setText(String.valueOf(investigador.getConstituicao()));
        textViewCONMeio.setText(String.valueOf(calcularMeio(investigador.getConstituicao())));
        textViewCONQuinto.setText(String.valueOf(calcularQuinto(investigador.getConstituicao())));

        editTextAPA.setText(String.valueOf(investigador.getAparencia()));
        textViewAPAMeio.setText(String.valueOf(calcularMeio(investigador.getAparencia())));
        textViewAPAQuinto.setText(String.valueOf(calcularQuinto(investigador.getAparencia())));

        editTextPOD.setText(String.valueOf(investigador.getPoder()));
        textViewPODMeio.setText(String.valueOf(calcularMeio(investigador.getPoder())));
        textViewPODQuinto.setText(String.valueOf(calcularQuinto(investigador.getPoder())));

        editTextTAM.setText(String.valueOf(investigador.getTamanho()));
        textViewTAMMeio.setText(String.valueOf(calcularMeio(investigador.getTamanho())));
        textViewTAMQuinto.setText(String.valueOf(calcularQuinto(investigador.getTamanho())));

        editTextEDU.setText(String.valueOf(investigador.getEducacao()));
        textViewEDUMeio.setText(String.valueOf(calcularMeio(investigador.getEducacao())));
        textViewEDUQuinto.setText(String.valueOf(calcularQuinto(investigador.getEducacao())));

        editTextMovimento.setText(String.valueOf(investigador.getTaxaMovimento()));

        seekBarSanidade.setProgress(Math.toIntExact(investigador.getSanidade()));
        editTextSanidade.setText(String.valueOf(investigador.getSanidade()));

        seekBarMagia.setProgress(Math.toIntExact(investigador.getMagia()));
        editTextMagia.setText(String.valueOf(investigador.getMagia()));

        seekBarPontosVida.setProgress(Math.toIntExact(investigador.getPontosVida()));
        editTextPontosVida.setText(String.valueOf(investigador.getPontosVida()));

        seekBarPontosSorte.setProgress(Math.toIntExact(investigador.getSorte()));
        editTextPontosSorte.setText(String.valueOf(investigador.getSorte()));

        calcularPontosSkill(investigador.getOcupacao(),investigador.getId());
    }

    private Long calcularMeio(Long numero) {
        return Math.floorDiv(numero, 2);
    }

    private Long calcularQuinto(Long numero) {
        return Math.floorDiv(numero, 5);
    }

}
