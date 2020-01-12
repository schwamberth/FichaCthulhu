package br.com.fichacthulhu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.androidannotations.annotations.EBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fichacthulhu.model.Investigador;
import br.com.fichacthulhu.model.Skill;

@EBean(scope = EBean.Scope.Singleton)
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "DBFichaCthulhu";
    private static final int DATABASE_VERSION = 1;

    private static final List<Class> tables = new ArrayList<>();

    static {
        tables.add(Investigador.class);
        tables.add(Skill.class);
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Investigador.class);
            Log.i("CriarTabela", Investigador.class.getName());
            TableUtils.createTableIfNotExists(connectionSource, Skill.class);
            Log.i("CriarTabela", Skill.class.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try {
//            for (Class<?> cls : tables) {
//                TableUtils.createTableIfNotExists(connectionSource, cls);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        //Quando algum update for feito no banco, criar o migration utilizando este método
//        try {
//            if (oldVersion < 2) {
//                upgradeToVersion2(connectionSource);
//            }
//            if (oldVersion < 3) {
//                upgradeToVersion3(connectionSource);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
