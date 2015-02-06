package net.randomsync.robotframework.http.internal;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class InternalHttpResponse {

    /**
     * Http Status code of the response
     */
    private int statusCode;
    /**
     * Http Status code of the response, to ensure compatibility with python
     * requests library
     */
    @SuppressWarnings("unused")
    private int status_code;
    /**
     * HTTP Status line of the response
     */
    private StatusLine statusLine;
    /**
     * HTTP Status line of the response, to ensure compatibility with python
     * requests library
     */
    private StatusLine status_line;

    private HttpEntity entity;

    /**
     * Convert the content of the response to a JSONObject
     * 
     * @return
     * @throws ParseException
     */
    public JSONObject json() throws ParseException {
        String entityStr;
        JSONObject json = null;
        try {
            entityStr = EntityUtils.toString(entity);
            json = (JSONObject) new JSONParser()
            .parse(entityStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        this.status_code = statusCode;
    }

    public int getStatus_code() {
        return getStatusCode();
    }

    public void setStatus_code(int status_code) {
        this.setStatusCode(status_code);
    }

    public HttpEntity getEntity() {
        return entity;
    }

    public void setEntity(HttpEntity entity) {
        this.entity = entity;
    }

}
