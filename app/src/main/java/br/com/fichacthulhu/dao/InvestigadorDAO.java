package br.com.fichacthulhu.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fichacthulhu.model.Investigador;

public class InvestigadorDAO extends BaseDaoImpl<Investigador, Long> {

    public static final String TAG = InvestigadorDAO.class.getName();

    @SuppressWarnings("unused")
    public InvestigadorDAO(Class<Investigador> dataClass) throws SQLException {
        super(dataClass);
    }

    @SuppressWarnings("unused")
    public InvestigadorDAO(ConnectionSource connectionSource, Class<Investigador> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    @SuppressWarnings("unused")
    public InvestigadorDAO(ConnectionSource connectionSource, DatabaseTableConfig<Investigador> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    public Investigador criar() {
        Investigador investigador = new Investigador();
        try {
            investigador = super.createIfNotExists(investigador);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return investigador;
    }

    public void salvar(Investigador investigador) {
        try {
            createOrUpdate(investigador);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Investigador> consultar() {
        try {
            List<Investigador> query = queryBuilder().query();
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public int remover(Investigador investigador) {
        try {
            return super.delete(investigador);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
