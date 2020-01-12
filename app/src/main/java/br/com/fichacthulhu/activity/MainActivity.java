package br.com.fichacthulhu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.ormlite.annotations.OrmLiteDao;

import java.util.ArrayList;
import java.util.List;

import br.com.fichacthulhu.DatabaseHelper;
import br.com.fichacthulhu.InvestigadorAdapter;
import br.com.fichacthulhu.R;
import br.com.fichacthulhu.dao.InvestigadorDAO;
import br.com.fichacthulhu.dao.SkillDAO;
import br.com.fichacthulhu.model.Investigador;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    static final int CRIAR_INVESTIGADOR = 1;
    static final int VISUALIZAR_INVESTIGADOR = 2;

    @ViewById
    ListView listViewInvestigador;

    @ViewById
    LinearLayout linearLayoutListaInvestigador;

    @OrmLiteDao(helper = DatabaseHelper.class)
    InvestigadorDAO investigadorDAO;

    @OrmLiteDao(helper = DatabaseHelper.class)
    SkillDAO skillDAO;

    InvestigadorAdapter investigadorAdapter;
    long linhaInicial = 0;

    @AfterViews
    protected void iniciar() {
        configurarComponentes();
        pesquisarInvestigadores(0);
    }

    @OnActivityResult(CRIAR_INVESTIGADOR)
    protected void resultCriarInvestigador() {
        pesquisarInvestigadores(0);
    }

    @OnActivityResult(VISUALIZAR_INVESTIGADOR)
    protected void resultVisualizar() {
        pesquisarInvestigadores(0);
    }

    @Click
    protected void linearLayoutCriarInvestigador() {
        InputFicha_.intent(this).startForResult(CRIAR_INVESTIGADOR);
    }

    private void pesquisarInvestigadores(long inicio) {
        new PesquisaInvestigadoresTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void configurarComponentes() {
        investigadorAdapter = new InvestigadorAdapter(this, new ArrayList<>());
    }

    private class PesquisaInvestigadoresTask extends AsyncTask<Void, Void, List<Investigador>> {
        @Override
        protected List<Investigador> doInBackground(Void... params) {
            List<Investigador> resultado = investigadorDAO.consultar();
            return resultado;
        }

        @Override
        protected void onPostExecute(List<Investigador> result) {
            if (result.size()==0) {
                linearLayoutListaInvestigador.setVisibility(View.GONE);
                return;
            } else {
                linearLayoutListaInvestigador.setVisibility(View.VISIBLE);
            }
            if (linhaInicial == 0) {
                investigadorAdapter.clear();
            }
            investigadorAdapter.addAll(result);
            listViewInvestigador.setAdapter(investigadorAdapter);
            investigadorAdapter.setOnDeleteListener(MainActivity.this::deletarInvestigador);
            investigadorAdapter.setOnClickListener(investigador -> {
//                Toast.makeText(MainActivity.this, "Nome clicado: " + investigador.getNome(), Toast.LENGTH_SHORT).show();
                VisualizaInvestigador_.intent(MainActivity.this)
                        .extra("investigador",investigador).startForResult(VISUALIZAR_INVESTIGADOR);
            });
            investigadorAdapter.setOnClickEditListener(investigador -> {
                InputFicha_.intent(MainActivity.this)
                        .extra("investigador", investigador)
                        .startForResult(CRIAR_INVESTIGADOR);
            });
        }
    }

    private void deletarInvestigador(Investigador investigador) {
        int removidos = 0;
        skillDAO.remover(investigador.getId());
        removidos = investigadorDAO.remover(investigador);
        if (removidos>0) {
            Toast.makeText(MainActivity.this, "Investigador removido com sucesso!!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Falha ao remover investigador.", Toast.LENGTH_SHORT).show();
        }
        pesquisarInvestigadores(0);
    }
}
