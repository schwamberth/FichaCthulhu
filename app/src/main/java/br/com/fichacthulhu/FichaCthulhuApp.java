package br.com.fichacthulhu;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.support.ConnectionSource;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;

@EApplication
public class FichaCthulhuApp extends Application {

    SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        inicializarBanco();
    }

    private void inicializarBanco() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        ConnectionSource connectionSource = databaseHelper.getConnectionSource();
        db = databaseHelper.getWritableDatabase();
        databaseHelper.onCreate(db, connectionSource);
    }

}
