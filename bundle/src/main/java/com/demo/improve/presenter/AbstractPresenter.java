package com.demo.improve.presenter;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.api.scripting.SlingScriptHelper;

public abstract class AbstractPresenter {
	SlingHttpServletRequest slingRequest;
	ValueMap properties;
	Node currentNode;
	SlingScriptHelper sling;

	public void init(SlingHttpServletRequest request, ValueMap properties,
			Node currentNode) throws RepositoryException {
		this.slingRequest = request;
		this.currentNode = currentNode;
        this.properties = properties;
        this.sling = getSlingScriptHelper(request);
        process();
		slingRequest.setAttribute("Abstract", "This is Abstract Presenter!!!");
	}

	protected abstract void process() throws RepositoryException;

	protected void putModel(String name, Object object) {
		slingRequest.setAttribute(name, object);
	}
	

    /**
     * Returns {@link SlingScriptHelper} by slingRequest
     *
     * @param request
     * @return
     */
    private static SlingScriptHelper getSlingScriptHelper(SlingHttpServletRequest request) {
        SlingBindings bindings = (SlingBindings) request.getAttribute(SlingBindings.class.getName());
        return bindings.getSling();
    }
}
