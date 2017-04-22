package com.duma.liudong.meiye.model;

import java.util.List;

/**
 * Created by liudong on 17/4/22.
 */

public class XiaoShouBaoBiaoBean {

    /**
     * week : {"count":"28","money":"765898.14"}
     * upweek : {"count":"0","money":null}
     * month : {"count":"28","money":"765898.14"}
     * upmonth : {"count":"0","money":null}
     * year : {"count":"28","money":"765898.14"}
     * upyear : {"count":"0","money":null}
     * mark : [{"moon":4,"count":"28","money":"765898.14"}]
     */

    private WeekBean week;
    private UpweekBean upweek;
    private MonthBean month;
    private UpmonthBean upmonth;
    private YearBean year;
    private UpyearBean upyear;
    private List<MarkBean> mark;

    public WeekBean getWeek() {
        return week;
    }

    public void setWeek(WeekBean week) {
        this.week = week;
    }

    public UpweekBean getUpweek() {
        return upweek;
    }

    public void setUpweek(UpweekBean upweek) {
        this.upweek = upweek;
    }

    public MonthBean getMonth() {
        return month;
    }

    public void setMonth(MonthBean month) {
        this.month = month;
    }

    public UpmonthBean getUpmonth() {
        return upmonth;
    }

    public void setUpmonth(UpmonthBean upmonth) {
        this.upmonth = upmonth;
    }

    public YearBean getYear() {
        return year;
    }

    public void setYear(YearBean year) {
        this.year = year;
    }

    public UpyearBean getUpyear() {
        return upyear;
    }

    public void setUpyear(UpyearBean upyear) {
        this.upyear = upyear;
    }

    public List<MarkBean> getMark() {
        return mark;
    }

    public void setMark(List<MarkBean> mark) {
        this.mark = mark;
    }

    public static class WeekBean {
        /**
         * count : 28
         * money : 765898.14
         */

        private String count;
        private String money;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }

    public static class UpweekBean {
        /**
         * count : 0
         * money : null
         */

        private String count;
        private String money;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }

    public static class MonthBean {
        /**
         * count : 28
         * money : 765898.14
         */

        private String count;
        private String money;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }

    public static class UpmonthBean {
        /**
         * count : 0
         * money : null
         */

        private String count;
        private String money;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }

    public static class YearBean {
        /**
         * count : 28
         * money : 765898.14
         */

        private String count;
        private String money;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }

    public static class UpyearBean {
        /**
         * count : 0
         * money : null
         */

        private String count;
        private String money;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }

    public static class MarkBean {
        /**
         * moon : 4
         * count : 28
         * money : 765898.14
         */

        private int moon;
        private String count;
        private String money;

        public int getMoon() {
            return moon;
        }

        public void setMoon(int moon) {
            this.moon = moon;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
