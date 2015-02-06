package net.randomsync.robotframework.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import net.randomsync.robotframework.http.internal.InternalHttpResponse;
import net.randomsync.robotframework.http.internal.InternalHttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.RobotKeyword;
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

    private Map<String, InternalHttpSession> sessionCache = new HashMap<String, InternalHttpSession>();

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
    public InternalHttpSession createSession(String alias, String url) {
        InternalHttpSession session = new InternalHttpSession();
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
    @ArgumentNames({ "alias", "path" })
    public InternalHttpResponse getRequest(String alias, String path) {
        // TODO add headers/params/allow redirects as well
        // TODO merge headers from session object with the headers passed here
        // TODO take headers/params/cookies etc from session object and add it
        // to the request
        InternalHttpResponse httpResponse = null;
        InternalHttpSession session = sessionCache.get(alias);
        URI uri = null;
        try {
            uri = new URI(session.getUrl() + path);
        } catch (URISyntaxException e) {
            System.out.println("*ERROR:" + System.currentTimeMillis() + "* "
                    + e.getLocalizedMessage());
        }

        try {
            HttpResponse response = Request.Get(uri).execute().returnResponse();
            httpResponse = new InternalHttpResponse();
            httpResponse
                    .setStatusCode(response.getStatusLine().getStatusCode());
            httpResponse.setEntity(response.getEntity());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return httpResponse;
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

    /**
     * Delete all the sessions that have been stored so far
     */
    @RobotKeyword
    public void deleteAllSessions() {
        sessionCache.clear();
    }

}
