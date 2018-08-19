package com.ph.image;

import java.io.InputStream;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-7-22  上午12:10
 */
public class Response {

    private InputStream ins;
    private int respCode;

    public Response(Builder builder) {
        this.ins = builder.ins;
        this.respCode = builder.respCode;
    }

    public InputStream stream() {
        return ins;
    }

    public int respCode() {
        return respCode;
    }


    static class Builder {

        //字节流
        private InputStream ins;

        //返回码
        private int respCode;


        public Builder inputStream(InputStream ins) {
            this.ins = ins;
            return this;
        }

        public Builder respCode(int respCode) {
            this.respCode = respCode;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }

}
