package com.listme.panels;

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
 
    protected String apiId = "547928241971103";
    protected String secret = "23ddaa4c232ecd7954137ad5b7465072";
    
    /**
     * Javascript-Wicket-Bridge that gets called after a successful login
     * contains Facebook-UserID, Facebook-SessionId for i.e. JSON and
     * AccessToken (OAuth2)
     */
    private AbstractDefaultAjaxBehavior loginBehavior = new AbstractDefaultAjaxBehavior() {
        /**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 1L;

		protected void respond(final AjaxRequestTarget target) {
            handleLoginEventCallback(target.getPage());
        }
    };
    
    private AbstractDefaultAjaxBehavior logoutBehavior = new AbstractDefaultAjaxBehavior() {
        /**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 1L;

		protected void respond(final AjaxRequestTarget target) {
            //log out the user but keep the session
            ((AuthWebSession) getSession()).signOut();
        }
    };
 
    private Label loginCallback = new Label("loginCallback", new AbstractReadOnlyModel<String>() {
        /**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 1L;

		@Override
        public String getObject() {
            CharSequence url = loginBehavior.getCallbackUrl();
            StringBuffer sb = new StringBuffer();
            sb.append("function callWicketLogin(response) { \n");
            sb.append(" alert(\" in wicket login \") ");
            sb.append("     if (response.session) {\n");
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
    
    private Label logoutCallback = new Label("logoutCallback", new AbstractReadOnlyModel<String>() {
        /**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 1L;

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
    
    /** Wrapper to super() **/
    public FacebookSignInPanel(String id) {
        super(id);
        loginCallback.setEscapeModelStrings(false);
        loginCallback.setOutputMarkupId(true);
        logoutCallback.setEscapeModelStrings(false);
        logoutCallback.setOutputMarkupId(true);
        Label FBapi = new Label("FBapi", createFacebookInit().toString());
        FBapi.setEscapeModelStrings(false);
        add(FBapi, loginCallback, logoutCallback);
        add(loginBehavior, logoutBehavior);
    }
    
    @Override
    public void renderHead(IHeaderResponse response) {
    	response.render(JavaScriptUrlReferenceHeaderItem.forUrl("http://connect.facebook.net/en_US/all.js"));
    }
    
    private StringBuffer createFacebookInit(){
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
        checkAlreadyAuth(sb);
        return sb;
    }
    
    private void checkAlreadyAuth(StringBuffer sb){
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
    }
 
    public void handleLoginEventCallback(Page p) {
    	StringValue uid = p.getRequest().getRequestParameters().getParameterValue("fbid");
    	StringValue accessToken = p.getRequest().getRequestParameters().getParameterValue("fbaccesstoken");
        if(!((AuthWebSession) getSession()).signInFacebook(accessToken.toString(), uid.toString())) {
            error("Sorry, something went wrong with the Login - please try again.");
        }
    }
	
}
