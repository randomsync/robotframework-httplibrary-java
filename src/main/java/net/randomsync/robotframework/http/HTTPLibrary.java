package net.randomsync.robotframework.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.randomsync.robotframework.http.internal.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywordOverload;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.robotframework.javalib.library.AnnotationLibrary;

/**
 * A keyword library for Robot Framework providing keywords for performing HTTP
 * operations.
 */
@RobotKeywords
public class HTTPLibrary extends AnnotationLibrary {

    public static final String KEYWORD_PATTERN = "net/randomsync/robotframework/http/**/*.class";

    public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

    public static final String ROBOT_LIBRARY_VERSION = "1.0.0";

    public static final String ROBOT_LIBRARY_DOC_FORMAT = "HTML";

    private Map<String, HttpSession> sessionCache = new HashMap<String, HttpSession>();

    public HTTPLibrary() {
        super(KEYWORD_PATTERN);
    }

    /**
     * Create a session that can be used later to send requests
     * 
     * @param alias
     *            name of the session
     * @param url
     *            url to send request to
     * @return the HttpSession object
     */
    @RobotKeyword
    @ArgumentNames({ "alias", "url" })
    public HttpSession createSession(String alias, String url) {
        HttpSession session = new HttpSession();
        session.setUrl(url);
        sessionCache.put(alias, session);
        return session;
    }

    /**
     * Send a GET request using the session object with the provided 'alias'
     * 
     * @param alias
     *            alias of the session object that should have been previously
     *            created
     * @param uri
     *            uri/path of the request
     */
    @RobotKeyword
    @ArgumentNames({ "alias", "url" })
    public HttpResponse getRequest(String alias, String uri) {
        // TODO add headers/params/allow redirects as well
        // TODO merge headers from session object with the headers passed here
        // TODO take headers/params/cookies etc from session object and add it
        // to the request
        HttpResponse response = null;
        HttpSession session = sessionCache.get(alias);
        String url = session.getUrl();
        //concat url with uri 

        try {
            response = Request.Get(url).execute().returnResponse();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Send a GET request using the session object with the provided 'alias'
     * 
     * @param alias
     *            alias of the session object that should have been previously
     *            created
     * @param uri
     *            uri/path of the request
     */
    @RobotKeyword
    @ArgumentNames({ "alias", "url" })
    public Content get(String alias, String uri) {
        // TODO add headers/params/allow redirects as well
        // TODO merge headers from session object with the headers passed here
        // TODO take headers/params/cookies etc from session object and add it
        // to the request
        Content content = null;

        try {
            content = Request.Get(uri).execute().returnContent();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return content;
    }

}
