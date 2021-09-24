package member.oauth2;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

/**
 * Servlet implementation class CalendarServletSample
 */
@WebServlet("/CalendarServletSample")
public class CalendarServletSample extends AbstractAuthorizationCodeServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// do stuff
	}

	@Override
	protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
		GenericUrl url = new GenericUrl(req.getRequestURL().toString());
		url.setRawPath("/oauth2callback");
		return url.build();
	}

	@Override
	protected AuthorizationCodeFlow initializeFlow() throws IOException {
		return new GoogleAuthorizationCodeFlow.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance(),
				"[[ENTER YOUR CLIENT ID]]", "[[ENTER YOUR CLIENT SECRET]]",
				Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(DATA_STORE_FACTORY)
						.setAccessType("offline").build();
	}

	@Override
	protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
		// return user ID
	}
}

public class CalendarServletCallbackSample extends AbstractAuthorizationCodeCallbackServlet {

	@Override
	protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential)
			throws ServletException, IOException {
		resp.sendRedirect("/");
	}

	@Override
	protected void onError(HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl errorResponse)
			throws ServletException, IOException {
		// handle error
	}

	@Override
	protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
		GenericUrl url = new GenericUrl(req.getRequestURL().toString());
		url.setRawPath("/oauth2callback");
		return url.build();
	}

	@Override
	  protected AuthorizationCodeFlow initializeFlow() throws IOException {
	    return new GoogleAuthorizationCodeFlow.Builder(
	        new NetHttpTransport(), GsonFactory.getDefaultInstance()
	        "[[ENTER YOUR CLIENT ID]]", "[[ENTER YOUR CLIENT SECRET]]",
	        Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(
	        DATA_STORE_FACTORY).setAccessType("offline").build();
	  }

	@Override
	protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
		// return user ID
	}
}
