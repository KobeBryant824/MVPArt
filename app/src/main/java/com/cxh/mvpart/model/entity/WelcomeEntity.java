package com.cxh.mvpart.model.entity;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/7/3
 */
public class WelcomeEntity {

    private int code;
    private String msg;
    private Data data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Data getData() {
        return data;
    }

    public static class Data {

        private String time;
        private int noUpDate;
        private String name;
        private String versionId;
        private String downUrl;

        public void setTime(String time) {
            this.time = time;
        }

        public void setNoUpDate(int noUpDate) {
            this.noUpDate = noUpDate;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setVersionId(String versionId) {
            this.versionId = versionId;
        }

        public void setDownUrl(String downUrl) {
            this.downUrl = downUrl;
        }

        public String getTime() {
            return time;
        }

        public int getNoUpDate() {
            return noUpDate;
        }

        public String getName() {
            return name;
        }

        public String getVersionId() {
            return versionId;
        }

        public String getDownUrl() {
            return downUrl;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "time='" + time + '\'' +
                    ", noUpDate=" + noUpDate +
                    ", name='" + name + '\'' +
                    ", versionId='" + versionId + '\'' +
                    ", downUrl='" + downUrl + '\'' +
                    '}';
        }
    }
}
