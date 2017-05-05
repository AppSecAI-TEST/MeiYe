package com.duma.liudong.meiye.database.dao;

import android.content.Context;

import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.database.DatabaseHelper;
import com.duma.liudong.meiye.utils.Ts;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LiShiSouSuoDao {
    private Context context;
    private Dao<LiShiSouSuoBean, Integer> liShiSouSuoBeanIntegerDao;
    private DatabaseHelper helper;

    public LiShiSouSuoDao() {
        this.context = MyApplication.getInstance();
        try {
            helper = DatabaseHelper.getHelper(context);
            liShiSouSuoBeanIntegerDao = helper.getDao(LiShiSouSuoBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个用户
     */
    public void add(LiShiSouSuoBean bean) {
        try {
            if (bean == null) {
                return;
            }

            List<LiShiSouSuoBean> mlist = new ArrayList<>();
            // LD: 先查询有没有重复的 如果有就删除前面重复的
            mlist.addAll(queryByBean(bean));
            if (mlist.size() != 0) {
                // LD: 有重复
                for (int i = 0; i < mlist.size(); i++) {
                    deleteForBean(mlist.get(i));
                }
            }
            // LD: 前面重复的删除完毕 添加最新的
            liShiSouSuoBeanIntegerDao.create(bean);

            // LD: 保持始终8个历史结果
            mlist.clear();
            mlist.addAll(queryAll());
            if (mlist.size() == 9) {
                deleteForBean(mlist.get(8));
            }
        } catch (SQLException e) {
            Ts.databaseErroy();
        }

    }

    public List<LiShiSouSuoBean> queryAll() {
        List<LiShiSouSuoBean> list = null;
        try {
            list = liShiSouSuoBeanIntegerDao.queryForAll();
        } catch (SQLException e) {
            Ts.databaseErroy();
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        Collections.reverse(list);
        return list;
    }

    public List<LiShiSouSuoBean> queryByBean(LiShiSouSuoBean bean) {
        List<LiShiSouSuoBean> list = null;
        try {
            list = liShiSouSuoBeanIntegerDao.queryBuilder().where().eq("name", bean.getName()).query();
        } catch (SQLException e) {
            Ts.databaseErroy();
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    public void delete() {
        try {
            liShiSouSuoBeanIntegerDao.deleteBuilder().delete();
        } catch (SQLException e) {
            Ts.databaseErroy();
        }
    }

    public void deleteForBean(LiShiSouSuoBean bean) {
        try {
            liShiSouSuoBeanIntegerDao.delete(bean);
        } catch (SQLException e) {
            Ts.databaseErroy();
        }
    }
}
