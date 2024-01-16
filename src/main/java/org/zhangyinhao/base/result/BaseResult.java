package org.zhangyinhao.base.result;

import java.beans.Transient;
import java.io.Serializable;
/**
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
public class BaseResult<T> implements Serializable {
    private static final long serialVersionUID = 8004487252556526569L;
    private int code;
    private String message;
    private T data;
    private long time;
    private String traceId;

    private BaseResult() {
    }

    private BaseResult(int code, String message, T data, long time) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.time = time;
    }

    private BaseResult(Builder<T> builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
        this.time = builder.time;
    }

    public static <T> BaseResult<T> result(int code, String message) {
        return result(code, message, null);
    }

    public static <T> BaseResult<T> result(int code, String message, T data) {
        return result(code, message, data, System.currentTimeMillis());
    }

    public static <T> BaseResult<T> result(int code, String message, T data, long time) {
        return builder().code(code).message(message).data(data).time(time).build();
    }

    public static BaseResult successResult() {
        return successResult((Object) null);
    }

    public static <T> BaseResult<T> successResult(T data) {
        return result(BaseCode.SUCCESS.getCode(), BaseCode.SUCCESS.getMessage(), data);
    }

    public static <T> BaseResult<T> successResult(String message, T data) {
        return result(BaseCode.SUCCESS.getCode(), message, data);
    }

    public static <T> BaseResult<T> successResult(String message, T data, long time) {
        return result(BaseCode.SUCCESS.getCode(), message, data, time);
    }

    public static BaseResult failResult() {
        return result(BaseCode.ERROR.getCode(), BaseCode.ERROR.getMessage());
    }

    public static BaseResult failResult(int errorCode, String message) {
        return result(errorCode, message, (Object) null);
    }

    public static <T> BaseResult<T> failResult(int errorCode, String message, T data) {
        return result(errorCode, message, data);
    }

    public static <T> BaseResult<T> failResult(int errorCode, String message, T data, long time) {
        return result(errorCode, message, data, time);
    }

    @Transient
    public boolean isError() {
        return BaseCode.SUCCESS.getCode() != this.getCode();
    }

    @Transient
    public boolean isSuccess() {
        return BaseCode.SUCCESS.getCode() == this.getCode();
    }

    public static Builder builder() {
        return new Builder();
    }

    public String toString() {
        return "BaseResult{code=" + this.code + ", message='" + this.message + '\'' + ", data=" + this.data + ", time=" + this.time + '}';
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public static class Builder<T> {
        private int code;
        private String message;
        private T data;
        private long time;
        private String traceId;

        public Builder() {
        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(T data) {
            this.data = data;
            return this;
        }

        public Builder time(long time) {
            this.time = time;
            return this;
        }

        public Builder traceId(String traceId) {
            this.traceId = traceId;
            return this;
        }

        public BaseResult build() {
            return new BaseResult(this);
        }
    }

}