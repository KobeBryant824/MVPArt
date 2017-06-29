package com.cxh.mvpsample.model.api;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/7
 */
public interface UserApi {

    // BUG  服务器都用request来解析参数
    @POST("version/detail")
    Observable<WelcomeEntity> welcomeObservalbe();

    public class WelcomeEntity {
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

}
