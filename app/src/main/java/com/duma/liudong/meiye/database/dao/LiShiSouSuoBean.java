package com.duma.liudong.meiye.database.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 79953 on 2016/11/4.
 */

@DatabaseTable(tableName = "tb_lishiSouSuo")
public class LiShiSouSuoBean {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "name")
    private String name;

//    public LiShiSouSuoBean(String name) {
//        this.name = name;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "LiShiSouSuoBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
