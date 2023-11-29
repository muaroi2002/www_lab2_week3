package vn.edu.iuh.fit.backend.cors;

import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.container.*;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@PreMatching
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter
{
    protected boolean allowCredentials = true;
    protected String allowedMethods;
    protected String allowedHeaders;
    protected String exposedHeaders;
    protected int corsMaxAge = -1;
    protected Set<String> allowedOrigins = new HashSet<String>();

    public Set<String> getAllowedOrigins()
    {
        return allowedOrigins;
    }

    public boolean isAllowCredentials()
    {
        return allowCredentials;
    }

    public void setAllowCredentials(boolean allowCredentials)
    {
        this.allowCredentials = allowCredentials;
    }

    public String getAllowedMethods()
    {
        return allowedMethods;
    }

    public void setAllowedMethods(String allowedMethods)
    {
        this.allowedMethods = allowedMethods;
    }

    public String getAllowedHeaders()
    {
        return allowedHeaders;
    }

    public void setAllowedHeaders(String allowedHeaders)
    {
        this.allowedHeaders = allowedHeaders;
    }

    public int getCorsMaxAge()
    {
        return corsMaxAge;
    }

    public void setCorsMaxAge(int corsMaxAge)
    {
        this.corsMaxAge = corsMaxAge;
    }

    public String getExposedHeaders()
    {
        return exposedHeaders;
    }

    /**
     * Comma delimited list.
     *
     * @param exposedHeaders exposed headers
     */
    public void setExposedHeaders(String exposedHeaders)
    {
        this.exposedHeaders = exposedHeaders;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException
    {
        String origin = requestContext.getHeaderString(CorsHeaders.ORIGIN);
        if (origin == null)
        {
            return;
        }
        if (requestContext.getMethod().equalsIgnoreCase("OPTIONS"))
        {
            preflight(origin, requestContext);
        }
        else
        {
            checkOrigin(requestContext, origin);
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException
    {
        String origin = requestContext.getHeaderString(CorsHeaders.ORIGIN);
        if (origin == null || requestContext.getMethod().equalsIgnoreCase("OPTIONS") || requestContext.getProperty("cors.failure") != null)
        {
            return;
        }
        responseContext.getHeaders().putSingle(CorsHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
        responseContext.getHeaders().putSingle(CorsHeaders.VARY, CorsHeaders.ORIGIN);
        if (allowCredentials) responseContext.getHeaders().putSingle(CorsHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");

        if (exposedHeaders != null) {
            responseContext.getHeaders().putSingle(CorsHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, exposedHeaders);
        }
    }


    protected void preflight(String origin, ContainerRequestContext requestContext) throws IOException
    {
        checkOrigin(requestContext, origin);

        Response.ResponseBuilder builder = Response.ok();
        builder.header(CorsHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
        builder.header(CorsHeaders.VARY, CorsHeaders.ORIGIN);
        if (allowCredentials) builder.header(CorsHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        String requestMethods = requestContext.getHeaderString(CorsHeaders.ACCESS_CONTROL_REQUEST_METHOD);
        if (requestMethods != null)
        {
            if (allowedMethods != null)
            {
                requestMethods = this.allowedMethods;
            }
            builder.header(CorsHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethods);
        }
        String allowHeaders = requestContext.getHeaderString(CorsHeaders.ACCESS_CONTROL_REQUEST_HEADERS);
        if (allowHeaders != null)
        {
            if (allowedHeaders != null)
            {
                allowHeaders = this.allowedHeaders;
            }
            builder.header(CorsHeaders.ACCESS_CONTROL_ALLOW_HEADERS, allowHeaders);
        }
        if (corsMaxAge > -1)
        {
            builder.header(CorsHeaders.ACCESS_CONTROL_MAX_AGE, corsMaxAge);
        }
        requestContext.abortWith(builder.build());

    }

    protected void checkOrigin(ContainerRequestContext requestContext, String origin)
    {
        if (!allowedOrigins.contains("*") && !allowedOrigins.contains(origin))
        {
            requestContext.setProperty("cors.failure", true);
            throw new ForbiddenException("Origin: "+ origin+ " is not allowed");
        }
    }
}