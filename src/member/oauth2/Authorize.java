package member.oauth2;

import java.io.InputStreamReader;
import java.util.Collections;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;

public class Authorize {
	 /** Authorizes the installed application to access user's protected data. */
	 private static Credential authorize() throws Exception {
	   // load client secrets
	   GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
	       new InputStreamReader(CalendarSample.class.getResourceAsStream("/client_secrets.json")));
	   // set up authorization code flow
	   GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	       httpTransport, JSON_FACTORY, clientSecrets,
	       Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(dataStoreFactory)
	      .build();
	   // authorize
	   return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	}
}
