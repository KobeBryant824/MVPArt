package com.cxh.mvpsample.model.entity;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/3/8 11:08.
 */
public class WelcomeEntity {

    /**
     * code : 1
     * msg : 请求成功
     * data : {"time":"2016-12-12","noUpDate":0,"name":"阳光采购","versionId":"1.2","downUrl":"http://210.51.183.101:8081/group2/M00/00/00/ygcg.apk"}
     */

    private int code;
    private String msg;
    private DataBean data;

    @Override
    public String toString() {
        return "WelcomeEntity{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * time : 2016-12-12
         * noUpDate : 0
         * name : 阳光采购
         * versionId : 1.2
         * downUrl : http://210.51.183.101:8081/group2/M00/00/00/ygcg.apk
         */

        private String time;
        private int noUpDate;
        private String name;
        private String versionId;
        private String downUrl;

        @Override
        public String toString() {
            return "DataBean{" +
                    "time='" + time + '\'' +
                    ", noUpDate=" + noUpDate +
                    ", name='" + name + '\'' +
                    ", versionId='" + versionId + '\'' +
                    ", downUrl='" + downUrl + '\'' +
                    '}';
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getNoUpDate() {
            return noUpDate;
        }

        public void setNoUpDate(int noUpDate) {
            this.noUpDate = noUpDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersionId() {
            return versionId;
        }

        public void setVersionId(String versionId) {
            this.versionId = versionId;
        }

        public String getDownUrl() {
            return downUrl;
        }

        public void setDownUrl(String downUrl) {
            this.downUrl = downUrl;
        }
    }


}
