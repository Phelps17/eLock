package com.elock.tylerphelps.elock;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.elock.tylerphelps.elock.Lock;

import com.elock.tylerphelps.elock.LockDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig lockDaoConfig;

    private final LockDao lockDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        lockDaoConfig = daoConfigMap.get(LockDao.class).clone();
        lockDaoConfig.initIdentityScope(type);

        lockDao = new LockDao(lockDaoConfig, this);

        registerDao(Lock.class, lockDao);
    }
    
    public void clear() {
        lockDaoConfig.getIdentityScope().clear();
    }

    public LockDao getLockDao() {
        return lockDao;
    }

}
