package com.daemon.pas.model;

import java.util.List;

/**
 * Created by Daemon on 2015/12/21.
 */
public class MusicPlayData {

    /**
     * code : 1
     * msg : OK
     * rows : 1
     * pages : 0
     * data : [{"_id":55480,"name":"Colbie Caillat","picUrls":[{"_id":6976332,"width":720,"height":1280,"refId":0,"quality":0,"picUrl":"http://3p.pic.ttdtweb.com/3p.ttpod.com/singer/55480/6976332.jpg"},{"_id":6292792,"width":720,"height":1280,"refId":0,"quality":0,"picUrl":"http://3p.pic.ttdtweb.com/3p.ttpod.com/singer/55480/6292792.jpg"}]}]
     */

    private int code;
    private String msg;
    private int rows;
    private int pages;
    /**
     * _id : 55480
     * name : Colbie Caillat
     * picUrls : [{"_id":6976332,"width":720,"height":1280,"refId":0,"quality":0,"picUrl":"http://3p.pic.ttdtweb.com/3p.ttpod.com/singer/55480/6976332.jpg"},{"_id":6292792,"width":720,"height":1280,"refId":0,"quality":0,"picUrl":"http://3p.pic.ttdtweb.com/3p.ttpod.com/singer/55480/6292792.jpg"}]
     */

    private List<DataEntity> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public int getRows() {
        return rows;
    }

    public int getPages() {
        return pages;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private int _id;
        private String name;
        /**
         * _id : 6976332
         * width : 720
         * height : 1280
         * refId : 0
         * quality : 0
         * picUrl : http://3p.pic.ttdtweb.com/3p.ttpod.com/singer/55480/6976332.jpg
         */

        private List<PicUrlsEntity> picUrls;

        public void set_id(int _id) {
            this._id = _id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPicUrls(List<PicUrlsEntity> picUrls) {
            this.picUrls = picUrls;
        }

        public int get_id() {
            return _id;
        }

        public String getName() {
            return name;
        }

        public List<PicUrlsEntity> getPicUrls() {
            return picUrls;
        }

        public static class PicUrlsEntity {
            private int _id;
            private int width;
            private int height;
            private int refId;
            private int quality;
            private String picUrl;

            public void set_id(int _id) {
                this._id = _id;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public void setRefId(int refId) {
                this.refId = refId;
            }

            public void setQuality(int quality) {
                this.quality = quality;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public int get_id() {
                return _id;
            }

            public int getWidth() {
                return width;
            }

            public int getHeight() {
                return height;
            }

            public int getRefId() {
                return refId;
            }

            public int getQuality() {
                return quality;
            }

            public String getPicUrl() {
                return picUrl;
            }
        }
    }
}
