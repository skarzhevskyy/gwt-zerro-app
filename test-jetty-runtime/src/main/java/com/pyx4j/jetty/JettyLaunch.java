/*
 * Pyx4j framework
 * Copyright (C) 2008-2011 pyx4j.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Created on 2011-01-13
 * @author vlads
 * @version $Id: JettyLaunch.java 12195 2012-11-28 22:27:23Z vlads $
 */
package com.pyx4j.jetty;

import java.io.File;
import java.net.ServerSocket;
import java.util.TimeZone;

import org.eclipse.jetty.http.HttpVersion;
//import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.rewrite.handler.RedirectPatternRule;
import org.eclipse.jetty.rewrite.handler.RewriteHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
//import org.eclipse.jetty.server.ServerConnector;
//import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

public abstract class JettyLaunch {

    public abstract String getContextPath();

    public int getServerPort() {
        return 8080;
    }

    /**
     * <pre>
     *  keytool -genkey -keystore jetty.keystore -keyalg rsa -alias  jetty -storepass 123456 -keypass 123456 -keyalg RSA -keysize 1024 -validity 4386 -dname "cn=pyx4j Team, ou=Testing, o=pyx4j, c=CA"
     * </pre>
     */
    public int getServerSslPort() {
        return 0;
    }

    public String getWarResourceBase() {
        return "src/main/webapp";
    }

    public String getHashLoginServiceConfig() {
        return "jetty-realm.properties";
    }

    /**
     * @see http://wiki.eclipse.org/Jetty/Tutorial/RequestLog#Configuring_Request_Log
     */
    public String getRequestLogFile() {
        return "./logs/jetty.request.log";
    }

    protected String getSessionCookiePath() {
        return "/";
    }

    /**
     * @return the max age to set on the session cookie, in seconds
     */
    protected int getSessionMaxAge() {
        return 60 * 60;
    }

    public boolean isRunningInDeveloperEnviroment() {
        return true;
    }

    public static void launch(JettyLaunch jettyLaunch) throws Exception {
        int port = jettyLaunch.getServerPort();
        //see if port is available
        try {
            ServerSocket s = new ServerSocket(port);
            s.close();
        } catch (Exception e) {
            throw new RuntimeException("Port already in use", e);
        }

        if (jettyLaunch.isRunningInDeveloperEnviroment()) {
            System.setProperty("com.pyx4j.DeveloperEnviroment", Boolean.TRUE.toString());
        }

        Server server = new Server(port);

        if (jettyLaunch.getServerSslPort() != 0) {
        	SslContextFactory sslContextFactory = new SslContextFactory();
            sslContextFactory.setKeyStorePassword("123456");
            sslContextFactory.setKeyStoreType("JKS");
            sslContextFactory.setKeyStorePath("./src/test/ssl/jetty.keystore");
            sslContextFactory.setKeyManagerPassword("123456");

            SslConnectionFactory ssl = new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString());
            ServerConnector connector = new ServerConnector(server, ssl);
            connector.setPort(jettyLaunch.getServerSslPort());
            server.addConnector(connector);
        }

        HandlerList handlers = new HandlerList();

        if (jettyLaunch.getRequestLogFile() != null) {
            File logFile = new File(jettyLaunch.getRequestLogFile());
            if (!logFile.getParentFile().isDirectory()) {
                logFile.getParentFile().mkdirs();
            }

            NCSARequestLog requestLog = new NCSARequestLog(jettyLaunch.getRequestLogFile());
            requestLog.setRetainDays(1);
            requestLog.setAppend(true);
            requestLog.setExtended(true);
            requestLog.setLogTimeZone(TimeZone.getDefault().getID());
            RequestLogHandler requestLogHandler = new RequestLogHandler();
            requestLogHandler.setRequestLog(requestLog);
            handlers.addHandler(requestLogHandler);
        }

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setDescriptor(jettyLaunch.getWarResourceBase() + "/WEB-INF/web.xml");
        webAppContext.setContextPath(jettyLaunch.getContextPath());
        webAppContext.setParentLoaderPriority(true);
        webAppContext.getInitParams().put("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
        webAppContext.setResourceBase(jettyLaunch.getWarResourceBase());

        if (jettyLaunch.getSessionCookiePath() != null) {
            webAppContext.getSessionHandler().getSessionManager().getSessionCookieConfig().setPath(jettyLaunch.getSessionCookiePath());
        }
        webAppContext.getSessionHandler().getSessionManager().getSessionCookieConfig().setMaxAge(jettyLaunch.getSessionMaxAge());

        if (jettyLaunch.getHashLoginServiceConfig() != null) {
            webAppContext.getSecurityHandler().setLoginService(new HashLoginService("default", jettyLaunch.getHashLoginServiceConfig()));
        }
        handlers.addHandler(webAppContext);

        //handle default /
        RewriteHandler rewrite = new RewriteHandler();
        rewrite.setRewriteRequestURI(false);

        RedirectPatternRule redirect = new RedirectPatternRule();
        redirect.setPattern("/");
        redirect.setLocation(jettyLaunch.getContextPath());
        rewrite.addRule(redirect);
        handlers.addHandler(rewrite);

        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
