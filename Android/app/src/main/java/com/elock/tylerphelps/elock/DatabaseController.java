package com.elock.tylerphelps.elock;

/**
 * Created by TylerPhelps on 1/23/17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.List;

public class DatabaseController {
    private DaoMaster.DevOpenHelper dbHelper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private LockDao lockDao;
    private Context context;

    public DatabaseController(Context context) {
        this.context = context;
        initDatabase();
    }

    private void initDatabase() {
        dbHelper = new DaoMaster.DevOpenHelper(this.context, "ORM.sqlite", null);
        db = dbHelper.getWritableDatabase();

        //Get DaoMaster
        daoMaster = new DaoMaster(db);

        //Create database and tables
        daoMaster.createAllTables(db, true);

        daoSession = daoMaster.newSession();

        lockDao = daoSession.getLockDao();
    }

    public boolean addLock(Lock lock)
    {
        Log.d("DB", ("adding lock " + lock.getId()));

        lockDao.insert(lock);
        Log.d("DB", "added successfully");

        closeReopenDatabase();
        return true;
    }

    public List<Lock> getLocks() {
        return this.lockDao.loadAll();
    }

    public void closeDatabase()
    {
        Log.d("DB", "close");
        daoSession.clear();
        db.close();
        dbHelper.close();
    }

    public long getNextLockId() {
        List<Lock> locks = getLocks();
        return locks.get(locks.size()-1).getId()+1;
    }

    private void closeReopenDatabase()
    {
        Log.d("DB", "close reopen");
        closeDatabase();

        dbHelper = new DaoMaster.DevOpenHelper(this.context, "ORM.sqlite", null);
        db = dbHelper.getWritableDatabase();

        //Get DaoMaster
        daoMaster = new DaoMaster(db);

        //Create database and tables
        daoMaster.createAllTables(db, true);

        //Create DaoSession
        daoSession = daoMaster.newSession();

        lockDao = daoSession.getLockDao();
    }
}

