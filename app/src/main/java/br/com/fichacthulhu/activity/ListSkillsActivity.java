package br.com.fichacthulhu.activity;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.ormlite.annotations.OrmLiteDao;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import br.com.fichacthulhu.DatabaseHelper;
import br.com.fichacthulhu.R;
import br.com.fichacthulhu.SkillAdapter;
import br.com.fichacthulhu.dao.SkillDAO;
import br.com.fichacthulhu.model.Investigador;
import br.com.fichacthulhu.model.Skill;

@EActivity(R.layout.activity_list_skills)
public class ListSkillsActivity extends AppCompatActivity {

    @Extra
    Investigador investigador;

    @ViewById
    EditText editTextPontosSkill;

    @ViewById
    SeekBar seekBarPontosSkill;

    @ViewById
    ListView listViewSkills;

    @ViewById
    Spinner spinnerSkill;

    @OrmLiteDao(helper = DatabaseHelper.class)
    SkillDAO skillDAO;

    SkillAdapter skillAdapter;
    long linhaInicial = 0;

    @AfterViews
    protected void iniciar() {
        configurarComponentes();
        configurarListeners();
        pesquisarSkills(0);
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
        adicionarSkill(spinnerSkill, seekBarPontosSkill, investigador.getId());
    }

    private void configurarListeners() {
        atualizarControleSeekbar(editTextPontosSkill, seekBarPontosSkill);
    }

    private void configurarComponentes() {
        String[] skills = getResources().getStringArray(R.array.skills_gerais);
        spinnerSkill.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, skills));
        skillAdapter = new SkillAdapter(this, new ArrayList<>());
        skillAdapter.setOnClickEvoluirListener(skill -> {
            skill.setEvoluir(!skill.getEvoluir());
            skillDAO.salvar(skill);
            pesquisarSkills(0);
        });
        skillAdapter.setOnClickSomarListener(skill -> {
            skill.setPontos(skill.getPontos()+1);
            skill.setMeio(Math.floorDiv(skill.getPontos(), 2));
            skill.setQuinto(Math.floorDiv(skill.getPontos(), 5));
            skillDAO.salvar(skill);
            pesquisarSkills(0);
        });
        skillAdapter.setOnClickSubtrairListener(skill -> {
            skill.setPontos(skill.getPontos()-1);
            skill.setMeio(Math.floorDiv(skill.getPontos(), 2));
            skill.setQuinto(Math.floorDiv(skill.getPontos(), 5));
            skillDAO.salvar(skill);
            pesquisarSkills(0);
        });
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

    private void adicionarSkill(Spinner spinner, SeekBar seekBar, Long idInvestigador) {
        if (skillJaExiste(idInvestigador, (String) spinner.getSelectedItem())) {
            Toast.makeText(this, "Skill já selecionada. Escolha outra.", Toast.LENGTH_SHORT).show();
            return;
        }

        Skill skill = skillDAO.criar();
        skill.setIdInvestigador(idInvestigador);
        skill.setDescricao((String) spinner.getSelectedItem());
        skill.setPontos((long) seekBar.getProgress());
        skill.setMeio(Math.floorDiv(skill.getPontos(), 2));
        skill.setQuinto(Math.floorDiv(skill.getPontos(), 5));

        skillDAO.salvar(skill);
        Toast.makeText(this, "Skill adicionada: " + skill.getDescricao(), Toast.LENGTH_LONG).show();
        pesquisarSkills(0);
    }

    private boolean skillJaExiste(Long idInvestigador, String descricaoSkill) {
        return skillDAO.jaSelecionada(idInvestigador, descricaoSkill);
    }

    private void pesquisarSkills(long inicio) {
        new PesquisaSkillsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class PesquisaSkillsTask extends AsyncTask<Void, Void, List<Skill>> {
        @Override
        protected List<Skill> doInBackground(Void... params) {
            List<Skill> resultado = skillDAO.queryForAll(investigador.getId());
            return resultado;
        }

        @Override
        protected void onPostExecute(List<Skill> result) {
            if (linhaInicial == 0) {
                skillAdapter.clear();
            }
            skillAdapter.addAll(result);
            listViewSkills.setAdapter(skillAdapter);
        }
    }

}
