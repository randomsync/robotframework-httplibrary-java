package net.randomsync.robotframework.http.internal;

import java.util.HashMap;
import java.util.Map;

public class HttpSession {
    private Map<String, String> headers = new HashMap<String, String>();
    private String url;

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public String getHeader(String key) {
        return this.headers.get(key);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
