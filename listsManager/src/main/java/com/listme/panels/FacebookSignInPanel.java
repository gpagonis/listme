package com.listme.panels;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptUrlReferenceHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.util.string.StringValue;

import com.listme.AuthWebSession;

public class FacebookSignInPanel 
extends Panel{

	/**
     * default SID
     */
    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(FacebookSignInPanel.class);
 
    protected String apiId = "547928241971103";
    protected String secret = "23ddaa4c232ecd7954137ad5b7465072";
 
    /** Wrapper to super() **/
    public FacebookSignInPanel(String id) {
        super(id);
        createPanel();
        System.out.println("lala");
    }
    
    @Override
    public void renderHead(IHeaderResponse response) {
    	response.render(JavaScriptUrlReferenceHeaderItem.forUrl("http://connect.facebook.net/en_US/all.js"));
    }
 
    /**
     * This method will load the Facebook-API and manage login-states
     */
    public void createPanel() {
        // Add and initialize Facebook-API
        StringBuffer sb = new StringBuffer();
        sb.append("FB.init({");
        sb.append("    appId: '"+ apiId +"',");
        sb.append("    status: true,");
        sb.append("    cookie: true,");
        sb.append("    xfbml: true");
        sb.append("});");
         
        // set Event-Handler for Login and Logout
        sb.append("FB.Event.subscribe('auth.login', function(respond){callWicketLogin(respond)});");
        sb.append("FB.Event.subscribe('auth.logout', function(respond){callWicketLogout(respond)});");
 
        // If the User is already logged in into Facebook, send this to Session
        if(!((AuthWebSession) getSession()).isSignedIn()) {
             sb.append("FB.getLoginStatus(function(response){");
             sb.append("if (response.session) {");
             sb.append("  FB.api('/me', function(response){");
             sb.append("    if(response.session) {");
             sb.append("        callWicketLogin(response);");
             sb.append("    } else {");
             sb.append("        FB.login(function(res) {");
             sb.append("            callWicketLogin(res); }");
             sb.append("        )");    
             sb.append("        }");
             sb.append("    })");
             sb.append("  }");
           sb.append("  else {");
           sb.append("    alert(\"not logged in\");");
           sb.append("   }");
             sb.append("  });");
        }
 
        Label FBapi = new Label("FBapi", sb.toString());
        FBapi.setEscapeModelStrings(false);
        add(FBapi);
 
        /**
         * Javascript-Wicket-Bridge that gets called after a successful login
         * contains Facebook-UserID, Facebook-SessionId for i.e. JSON and
         * AccessToken (OAuth2)
         */
        final AbstractDefaultAjaxBehavior loginBehavior = new AbstractDefaultAjaxBehavior() {
            protected void respond(final AjaxRequestTarget target) {
                handleLoginEventCallback(target.getPage());
            }
        };
        add(loginBehavior);
 
        /**
         * Javascript-Wicket-Bridge that gets called after a logout occurred
         */
        final AbstractDefaultAjaxBehavior logoutBehavior = new AbstractDefaultAjaxBehavior() {
            protected void respond(final AjaxRequestTarget target) {
                //log out the user but keep the session
                ((AuthWebSession) getSession()).signOut();
            }
        };
        add(logoutBehavior);
 
        Label loginCallback = new Label("loginCallback", new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                CharSequence url = loginBehavior.getCallbackUrl();
                StringBuffer sb = new StringBuffer();
                sb.append("function callWicketLogin(response) { \n");
                sb.append("     if (response.session) {\n");
                //optional: check permissions here if all necessary are granted!
                //example:  (2010/6/1 @ http://developers.facebook.com/docs/reference/javascript/FB.login)
//                      if (response.perms) {
//                        // user is logged in and granted some permissions.
//                        // perms is a comma separated list of granted permissions
//                      } else {
//                        // user is logged in, but did not grant any permissions
//                      }
                sb.append("         var wcall = wicketAjaxGet('");
                sb.append(url);
                sb.append("&fbid='+ response.session.uid +'");
                //SessionId in case you want to use the old API
                sb.append("&fbsessid='+ response.session.session_key +'");
                //AccessToken for new OAuth2-based requests
                sb.append("&fbaccesstoken='+ response.session.access_token");
                sb.append(", function() { }, function() { });\n");
                sb.append("     } else {\n");
                sb.append("         alert(\"Sorry, there was some kind of error in the Facebook-login. Please try again.\");");
                sb.append("     }\n");
                sb.append("}\n");
                return sb.toString();
            }
        });
        loginCallback.setEscapeModelStrings(false);
        loginCallback.setOutputMarkupId(true);
        add(loginCallback);
 
        Label logoutCallback = new Label("logoutCallback", new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                CharSequence url = logoutBehavior.getCallbackUrl();
                StringBuffer sb = new StringBuffer();
                sb.append("function callWicketLogout(response) { \n");
                sb.append("alert(\"You have been logged out from facebook. We will log you out too - see FAQ\");");
                sb.append("     var wcall = wicketAjaxGet('");
                sb.append(url);
                sb.append("', function() { }, function() { });");
                sb.append("    }");
                return sb.toString();
            }
        });
        logoutCallback.setEscapeModelStrings(false);
        logoutCallback.setOutputMarkupId(true);
        add(logoutCallback);
 
    }
 
    public void handleLoginEventCallback(Page p) {
    	StringValue uid = p.getRequest().getRequestParameters().getParameterValue("fbid");
    	StringValue accessToken = p.getRequest().getRequestParameters().getParameterValue("fbaccesstoken");
//        Map<String, String[]> map = ((WebRequestCycle) RequestCycle.get()).getRequest().getParameterMap();
//         
//        String uid = map.get("fbid")[0];
//        String accessToken = map.get("fbaccesstoken")[0];
        if(!((AuthWebSession) getSession()).signInFacebook(accessToken.toString(), uid.toString())) {
            error("Sorry, something went wrong with the Login - please try again.");
        }
    }
	
}
