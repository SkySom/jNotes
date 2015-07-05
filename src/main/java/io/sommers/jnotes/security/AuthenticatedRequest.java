package io.sommers.jnotes.security;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;

/**
 * Created by Skylar on 7/5/2015.
 */
public class AuthenticatedRequest extends RequestFacade {
	public AuthenticatedRequest(Request request) {
		super(request);
	}


}
