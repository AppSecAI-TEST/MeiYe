package com.duma.liudong.meiye.model;

import java.util.List;

/**
 * Created by liudong on 17/5/8.
 */

public class Demo {
    private List<ProvinceBean> provinceList;
    private List<List<ProvinceBean>> cityList;
    private List<List<List<ProvinceBean>>> countryList;

    public List<ProvinceBean> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<ProvinceBean> provinceList) {
        this.provinceList = provinceList;
    }

    public List<List<ProvinceBean>> getCityList() {
        return cityList;
    }

    public void setCityList(List<List<ProvinceBean>> cityList) {
        this.cityList = cityList;
    }

    public List<List<List<ProvinceBean>>> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<List<List<ProvinceBean>>> countryList) {
        this.countryList = countryList;
    }
}
