package br.com.fichacthulhu.activity;

import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.BindingObject;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.DataBound;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.ormlite.annotations.OrmLiteDao;
import org.apache.commons.lang3.StringUtils;

import br.com.fichacthulhu.BR;
import br.com.fichacthulhu.DatabaseHelper;
import br.com.fichacthulhu.InvestigadorDTO;
import br.com.fichacthulhu.R;
import br.com.fichacthulhu.dao.InvestigadorDAO;
import br.com.fichacthulhu.model.Investigador;

@DataBound
@EActivity(R.layout.layout_view_investigador)
@OptionsMenu(R.menu.menu_visualizar_investigador)
public class VisualizaInvestigador extends AppCompatActivity {

    @Extra
    Investigador investigador;

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
    EditText editTextDescricaoPessoal;

    @ViewById
    EditText editTextIdeologia;

    @ViewById
    EditText editTextPessoasSignificativas;

    @ViewById
    EditText editTextLocaisImpactantes;

    @ViewById
    EditText editTextPertencesQueridos;

    @ViewById
    EditText editTextCaracteristicas;

    @ViewById
    EditText editTextFerimentosCicatrizes;

    @ViewById
    EditText editTextFobiasManias;

    @ViewById
    EditText editTextTomosFeiticosArtefatos;

    @ViewById
    EditText editTextEncontros;

    @ViewById
    EditText editTextNivelGastos;

    @ViewById
    EditText editTextDinheiro;

    @ViewById
    EditText editTextPatrimonio;

    @OrmLiteDao(helper = DatabaseHelper.class)
    InvestigadorDAO investigadorDAO;

    @BindingObject
    ViewDataBinding binding;

    InvestigadorDTO investigadorDTO;

    @AfterViews
    protected void iniciar() {
        preencheInvestigadorDTO();
        configurarListeners();
    }

    @OptionsItem(R.id.item_menu_abrir_skills)
    protected void actionItemMenuAbrirSkills() {
        ListSkillsActivity_.intent(this)
                .extra("investigador", investigador)
                .start();

    }

    @OptionsItem(R.id.item_menu_salvar)
    protected void actionItemMenuSalvar() {
        investigador.setPontosVida((long) seekBarPontosVida.getProgress());
        investigador.setSanidade((long) seekBarSanidade.getProgress());
        investigador.setSorte((long) seekBarPontosSorte.getProgress());
        investigador.setMagia((long) seekBarMagia.getProgress());

        investigador.setDescricaoPessoal(editTextDescricaoPessoal.getText().toString());
        investigador.setIdeologia(editTextIdeologia.getText().toString());
        investigador.setPessoasSignificativas(editTextPessoasSignificativas.getText().toString());
        investigador.setLocaisImpactantes(editTextLocaisImpactantes.getText().toString());
        investigador.setPertencesQueridos(editTextPertencesQueridos.getText().toString());
        investigador.setCaracteristicas(editTextCaracteristicas.getText().toString());
        investigador.setFerimentosCicratrizes(editTextFerimentosCicatrizes.getText().toString());
        investigador.setFobiasManias(editTextFobiasManias.getText().toString());
        investigador.setTomosFeiticosArtefatos(editTextTomosFeiticosArtefatos.getText().toString());
        investigador.setEncontros(editTextEncontros.getText().toString());

        investigador.setDinheiro(editTextDinheiro.getText().toString());
        investigador.setNivelGastos(editTextNivelGastos.getText().toString());
        investigador.setPatrimonio(editTextPatrimonio.getText().toString());

        investigadorDAO.salvar(investigador);

        Toast.makeText(this, "Informações salvas com sucesso!!", Toast.LENGTH_SHORT).show();
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

    private void subtrairSeekbar(SeekBar seekBar, EditText editText, int minimo) {
        if (seekBar.getProgress() > minimo) {
            seekBar.setProgress(seekBar.getProgress() - 1);
            editText.setText(String.valueOf(seekBar.getProgress()));
        }
    }

    private void somarSeekBar(SeekBar seekBar, EditText editText, int maximo) {
        if (seekBar.getProgress() < maximo) {
            seekBar.setProgress(seekBar.getProgress() + 1);
            editText.setText(String.valueOf(seekBar.getProgress()));
        }
    }

    private void preencheInvestigadorDTO() {
        InvestigadorDTO inv = new InvestigadorDTO();
        inv.setId(investigador.getId());
        inv.setNome(investigador.getNome());
        inv.setOcupacao(investigador.getOcupacao());
        inv.setFormacaoAcademica(investigador.getFormacaoAcademica());
        inv.setLocalNascimento(investigador.getLocalNascimento());
        inv.setTranstornosMentais(investigador.getTranstornosMentais());
        inv.setSexo(investigador.getSexo());
        inv.setIdade(String.valueOf(investigador.getIdade()));
        inv.setResidencia(investigador.getResidencia());
        inv.setForca(String.valueOf(investigador.getForca()));
        inv.setForcaMeio(calcularMeio(String.valueOf(investigador.getForca())));
        inv.setForcaQuinto(calcularQuinto(String.valueOf(investigador.getForca())));
        inv.setDestreza(String.valueOf(investigador.getDestreza()));
        inv.setDestrezaMeio(calcularMeio(String.valueOf(investigador.getDestreza())));
        inv.setDestrezaQuinto(calcularQuinto(String.valueOf(investigador.getDestreza())));
        inv.setInteligencia(String.valueOf(investigador.getInteligencia()));
        inv.setInteligenciaMeio(calcularMeio(String.valueOf(investigador.getInteligencia())));
        inv.setInteligenciaQuinto(calcularQuinto(String.valueOf(investigador.getInteligencia())));
        inv.setConstituicao(String.valueOf(investigador.getConstituicao()));
        inv.setConstituicaoMeio(calcularMeio(String.valueOf(investigador.getConstituicao())));
        inv.setConstituicaoQuinto(calcularQuinto(String.valueOf(investigador.getConstituicao())));
        inv.setAparencia(String.valueOf(investigador.getAparencia()));
        inv.setAparenciaMeio(calcularMeio(String.valueOf(investigador.getAparencia())));
        inv.setAparenciaQuinto(calcularQuinto(String.valueOf(investigador.getAparencia())));
        inv.setPoder(String.valueOf(investigador.getPoder()));
        inv.setPoderMeio(calcularMeio(String.valueOf(investigador.getPoder())));
        inv.setPoderQuinto(calcularQuinto(String.valueOf(investigador.getPoder())));
        inv.setTamanho(String.valueOf(investigador.getTamanho()));
        inv.setTamanhoMeio(calcularMeio(String.valueOf(investigador.getTamanho())));
        inv.setTamanhoQuinto(calcularQuinto(String.valueOf(investigador.getTamanho())));
        inv.setEducacao(String.valueOf(investigador.getEducacao()));
        inv.setEducacaoMeio(calcularMeio(String.valueOf(investigador.getEducacao())));
        inv.setEducacaoQuinto(calcularQuinto(String.valueOf(investigador.getEducacao())));
        inv.setTaxaMovimento(String.valueOf(investigador.getTaxaMovimento()));
        inv.setSanidade(String.valueOf(investigador.getSanidade()));
        inv.setSorte(String.valueOf(investigador.getSorte()));
        inv.setMagia(String.valueOf(investigador.getMagia()));
        inv.setPontosVida(String.valueOf(investigador.getPontosVida()));
        inv.setDescricaoPessoal(investigador.getDescricaoPessoal());
        inv.setIdeologia(investigador.getIdeologia());
        inv.setPessoasSignificativas(investigador.getPessoasSignificativas());
        inv.setLocaisImpactantes(investigador.getLocaisImpactantes());
        inv.setPertencesQueridos(investigador.getPertencesQueridos());
        inv.setCaracteristicas(investigador.getCaracteristicas());
        inv.setFerimentosCicratrizes(investigador.getFerimentosCicratrizes());
        inv.setFobiasManias(investigador.getFobiasManias());
        inv.setTomosFeiticosArtefatos(investigador.getTomosFeiticosArtefatos());
        inv.setEncontros(investigador.getEncontros());
        inv.setNivelGastos(investigador.getNivelGastos());
        inv.setDinheiro(investigador.getDinheiro());
        inv.setPatrimonio(investigador.getPatrimonio());
        inv.setDescricaoPessoal(investigador.getDescricaoPessoal());
        inv.setIdeologia(investigador.getIdeologia());
        inv.setPessoasSignificativas(investigador.getPessoasSignificativas());
        inv.setLocaisImpactantes(investigador.getLocaisImpactantes());
        inv.setPertencesQueridos(investigador.getPertencesQueridos());
        inv.setCaracteristicas(investigador.getCaracteristicas());
        inv.setFerimentosCicratrizes(investigador.getFerimentosCicratrizes());
        inv.setFobiasManias(investigador.getFobiasManias());
        inv.setTomosFeiticosArtefatos(investigador.getTomosFeiticosArtefatos());
        inv.setEncontros(investigador.getEncontros());
        investigadorDTO = inv;

        binding.setVariable(BR.investigadorDTO, investigadorDTO);
    }

    private String calcularMeio(String numero) {
        Long valor = Long.valueOf(numero);
        return String.valueOf(Math.floorDiv(valor, 2));
    }

    private String calcularQuinto(String numero) {
        Long valor = Long.valueOf(numero);
        return String.valueOf(Math.floorDiv(valor, 5));
    }

    private void configurarListeners() {
        atualizarControleSeekbar(editTextSanidade, seekBarSanidade);
        atualizarControleSeekbar(editTextMagia, seekBarMagia);
        atualizarControleSeekbar(editTextMagia, seekBarMagia);
        atualizarControleSeekbar(editTextPontosVida, seekBarPontosVida);
        atualizarControleSeekbar(editTextPontosSorte, seekBarPontosSorte);
    }

    private void atualizarControleSeekbar(EditText editText, SeekBar seekBar) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (StringUtils.isNotBlank(editText.getText().toString())) {
                    if (Integer.parseInt(editText.getText().toString()) <= seekBar.getMax()) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            if (Integer.parseInt(editText.getText().toString()) >= seekBar.getMin()) {
                                seekBar.setProgress(Integer.parseInt(editText.getText().toString()));
                            }
                        } else {
                            seekBar.setProgress(Integer.parseInt(editText.getText().toString()));
                        }

                    }
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

}
