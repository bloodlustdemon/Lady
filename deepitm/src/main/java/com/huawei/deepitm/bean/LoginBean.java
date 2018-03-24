package com.huawei.deepitm.bean;

import org.paul.lib.base.BaseBean;
public class LoginBean extends BaseBean {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Login{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public class Data {
        private Token token;
        private UserInfo userInfo;

        public Token getToken() {
            return token;
        }

        public void setToken(Token token) {
            this.token = token;
        }

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "token=" + token +
                    ", userInfo=" + userInfo +
                    '}';
        }
    }

}
