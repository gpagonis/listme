package com.listme;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.exception.FacebookException;
import com.restfb.types.User;

public class AuthWebSession extends AuthenticatedWebSession {
		 
    /** UserID in database */
    private long userId = -1;
 
    /** facebook-account? */
    private boolean facebookAcc = false;
 
    /** Access-Token for Facebook-REST-Api */
    private String fbToken = null;
 
    /** Fixed SerialVersionUID */
    private static final long serialVersionUID = 1L;
 
    /** Logger */
    private Log log = LogFactory.getLog(AuthWebSession.class);
 
    /**
     * @return Current authenticated web session
     */
    public static AuthenticatedWebSession get() {
        return (AuthenticatedWebSession) Session.get();
    }
 
    /**
     * Constructor sets Spring-Injector
     * 
     * @param request
     *            Webrequest
     */
    public AuthWebSession(Request request) {
        super(request);
    }
 
    /**
     * Wrapper method for signin in a User via his Facebook-account
     * 
     * @param accesstoken
     * @param uid
     * @return
     */
    public boolean signInFacebook(final String accesstoken, final String uid) {
        boolean status = authenticateFacebook(accesstoken, uid);
        signIn(status);
        return status;
    }
 
    /**
     * The user logged in at facebook.
     * 
     * @param accesstoken
     *            Access-Token for OAuth2-REST-Api of Facebook
     * @param uid
     *            Uique User-ID in facebook
     * @return successfuly changend Session
     */
    public boolean authenticateFacebook(String accesstoken, String uid) {
        long uidLong = Long.parseLong(uid);
         
        if(isSignedIn() && userId!=uidLong) {
            //wrong user in current sesson
            signOut();
            replaceSession();
        }
         
        userId = uidLong;
        facebookAcc = true;
        fbToken = accesstoken;
         
        FacebookClient fbClient = new DefaultFacebookClient(accesstoken);
        try {
            User u = fbClient.fetchObject("me", User.class);
            //get some data of the User...
            //i.e. check if user is already in database, add/update entry
            // user-picture via  String picLink = u.getLink()+"/picture"
            //u.getPic doesn't seem to work for me
        } catch (FacebookException e) {
            log.error("Facebook-REST-error for uid:"+ uid +" token:"+ accesstoken, e);
            return false;
        }
        return true;
    }
 
    /**
     * @return Id of the user logged in with this session
     */
    public long getUserId() {
        if (isSignedIn()) {
            return userId;
        } else {
            return -1;
        }
    }
     
    public void signOut() {
        super.signOut();
        userId=-1;
    }

	@Override
	public boolean authenticate(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Roles getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

}
