package com.daemon.pas.model;

import java.util.List;

/**
 * Created by Daemon on 2015/12/9.
 */
public class PicTypeData {

    /**
     * msg : success
     * res : {"category":[{"count":32875,"icover":"565d53ac69401b0ba2c35ffb","rname":"美女","atime":1.291266021E9,"cover_temp":"566781da69401b02a6380bd7","name":"美女","cover":"http://img0.androidesk.com/download/5667934694e5cc7c5e734ef9","rank":1,"filter":[],"type":1,"id":"4e4d610cdf714d2966000000"},{"count":11892,"icover":"56618df069401b0b9a899cb1","rname":"情感","cover_temp":"56618df069401b0b9a899cb1","name":"情感","cover":"http://img0.androidesk.com/download/56628c4669401b2c38edb358","rank":2,"filter":[],"type":1,"id":"4ef0a35c0569795756000000"},{"count":59174,"filter":[],"rname":"风景","atime":1.291266049E9,"cover_temp":"566532bb69401b0b9a89a226","name":"风景","cover":"http://img0.androidesk.com/download/5665593a69401b5ba2aab92e","rank":3,"icover":"566532bb69401b0b9a89a226","type":1,"id":"4e4d610cdf714d2966000002"},{"count":69577,"filter":[],"rname":"动漫","atime":1.291266057E9,"cover_temp":"566064c769401b03298a7f29","name":"动漫","cover":"http://img0.androidesk.com/download/566129c094e5cc743c00cae3","rank":4,"icover":"566064c769401b03298a7f29","type":1,"id":"4e4d610cdf714d2966000003"},{"count":7553,"icover":"5618c7d969401b09cec0bdbf","rname":"文字","atime":1.359601742E9,"cover_temp":"5618c7d969401b09cec0bdbf","name":"文字","cover":"http://img0.androidesk.com/download/5618e42c94e5cc554f1196a8","rank":5,"filter":[],"type":1,"id":"5109e04e48d5b9364ae9ac45"},{"count":16013,"icover":"5661621069401b0ba2c36da8","rname":"明星","atime":1.359601746E9,"cover_temp":"5661621069401b0ba2c36da8","name":"明星","cover":"http://img0.androidesk.com/download/566253bd69401b35f9d16a98","rank":6,"filter":[],"type":1,"id":"5109e05248d5b9368bb559dc"},{"count":10679,"icover":"56628cb769401b0b9a899dee","rname":"城市","cover_temp":"56628cb769401b0b9a899dee","name":"城市","cover":"http://img0.androidesk.com/download/5663a55369401b4e280153e3","rank":7,"filter":[],"type":1,"id":"4fb47a305ba1c60ca5000223"},{"count":6674,"icover":"5663b26369401b0b9a899f42","rname":"视觉","cover_temp":"5663b26369401b0b9a899f42","name":"视觉","cover":"http://img0.androidesk.com/download/56643fb269401b144535a237","rank":8,"filter":[],"type":1,"id":"4fb479f75ba1c65561000027"},{"count":4218,"filter":[],"rname":"设计","cover_temp":"5656a6eb69401b6359d0a61a","name":"设计","cover":"http://img0.androidesk.com/download/5656aea069401b79804bb5ab","rank":9,"icover":"5656a6eb69401b6359d0a61a","type":1,"id":"4fb47a195ba1c60ca5000222"},{"count":18267,"icover":"566529ad69401b0ba2c37502","rname":"物语","cover_temp":"566529ad69401b0ba2c37502","name":"物语","cover":"http://img0.androidesk.com/download/5665394b69401b1a75ec2a1a","rank":10,"filter":[],"type":1,"id":"4fb47a465ba1c65561000028"},{"count":25294,"filter":[],"rname":"其他","atime":1.291266067E9,"cover_temp":"56618e9969401b0ba2c36fc7","name":"其他","cover":"http://img0.androidesk.com/download/5662b61269401b05bcb73feb","rank":11,"icover":"56618e9969401b0ba2c36fc7","type":1,"id":"4e4d610cdf714d2966000004"},{"count":19975,"icover":"566289a269401b0b9a899d85","rname":"机械","atime":1.297756191E9,"cover_temp":"566289a269401b0b9a899d85","name":"机械","cover":"http://img0.androidesk.com/download/5663a62a69401b5085adc2ab","rank":12,"filter":[],"type":1,"id":"4e4d610cdf714d2966000005"},{"count":3698,"filter":[],"rname":"男人","atime":1.29825154E9,"cover_temp":"5659776369401b0acfa832b1","name":"男人","cover":"http://img0.androidesk.com/download/565abf6694e5cc57a1a63a5a","rank":13,"icover":"5663b23369401b0ba2c3718a","type":1,"id":"4e4d610cdf714d2966000006"},{"count":15900,"filter":[],"rname":"动物","atime":1.291266042E9,"cover_temp":"5664ee5569401b0ba2c37344","name":"动物","cover":"http://img0.androidesk.com/download/5664f69669401b0c819d3dee","rank":14,"icover":"5664ee5569401b0ba2c37344","type":1,"id":"4e4d610cdf714d2966000001"},{"count":10806,"icover":"5665330d69401b0b9a89a23f","rname":"游戏","atime":1.300683934E9,"cover_temp":"5665330d69401b0b9a89a23f","name":"游戏","cover":"http://img0.androidesk.com/download/566558e469401b5ba2aab5ab","rank":15,"filter":[],"type":1,"id":"4e4d610cdf714d2966000007"},{"count":9282,"filter":[],"rname":"艺术","cover_temp":"564c2fe169401b5ae9eab545","name":"艺术","cover":"http://img0.androidesk.com/download/564d4e3e69401b39685b989c","rank":16,"icover":"564c2fe169401b5ae9eab545","type":1,"id":"4ef0a3330569795757000000"},{"count":6908,"icover":"5664724e69401b02f744b0ac","rname":"运动","cover_temp":"5664724e69401b02f744b0ac","name":"运动","cover":"http://img0.androidesk.com/download/566507d894e5cc61596bdf6a","rank":17,"filter":[],"type":1,"id":"4ef0a34e0569795757000001"},{"count":13263,"filter":[],"rname":"影视","cover_temp":"5661119569401b0b9a8998f9","name":"影视","cover":"http://img0.androidesk.com/download/5661184069401b1b00fc1102","rank":18,"icover":"5661119569401b0b9a8998f9","type":1,"id":"4e58c2570569791a19000000"}]}
     * code : 0
     */

    private String msg;
    private ResEntity res;
    private int code;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setRes(ResEntity res) {
        this.res = res;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public ResEntity getRes() {
        return res;
    }

    public int getCode() {
        return code;
    }

    public static class ResEntity {
        /**
         * count : 32875
         * icover : 565d53ac69401b0ba2c35ffb
         * rname : 美女
         * atime : 1.291266021E9
         * cover_temp : 566781da69401b02a6380bd7
         * name : 美女
         * cover : http://img0.androidesk.com/download/5667934694e5cc7c5e734ef9
         * rank : 1
         * filter : []
         * type : 1
         * id : 4e4d610cdf714d2966000000
         */

        private List<CategoryEntity> category;

        public void setCategory(List<CategoryEntity> category) {
            this.category = category;
        }

        public List<CategoryEntity> getCategory() {
            return category;
        }

        public static class CategoryEntity {
            private int count;
            private String icover;
            private String rname;
            private double atime;
            private String cover_temp;
            private String name;
            private String cover;
            private int rank;
            private int type;
            private String id;
            private List<?> filter;

            public void setCount(int count) {
                this.count = count;
            }

            public void setIcover(String icover) {
                this.icover = icover;
            }

            public void setRname(String rname) {
                this.rname = rname;
            }

            public void setAtime(double atime) {
                this.atime = atime;
            }

            public void setCover_temp(String cover_temp) {
                this.cover_temp = cover_temp;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public void setType(int type) {
                this.type = type;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setFilter(List<?> filter) {
                this.filter = filter;
            }

            public int getCount() {
                return count;
            }

            public String getIcover() {
                return icover;
            }

            public String getRname() {
                return rname;
            }

            public double getAtime() {
                return atime;
            }

            public String getCover_temp() {
                return cover_temp;
            }

            public String getName() {
                return name;
            }

            public String getCover() {
                return cover;
            }

            public int getRank() {
                return rank;
            }

            public int getType() {
                return type;
            }

            public String getId() {
                return id;
            }

            public List<?> getFilter() {
                return filter;
            }
        }
    }
}
