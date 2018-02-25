package com.wwc.sg.dw.resources;

import com.wwc.sg.dw.auth.User;
import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class IndexResource {

    private String name;
    private String environment;
    private String version;
    private String template;

    public IndexResource(String name, String environment, String version, String template){
        this.name = name;
        this.environment = environment;
        this.version = version;
        this.template = template;
    }

    @GET
    public Response getVersion() {
        StringBuffer sb = new StringBuffer();
        sb.append("Application:" + name + "\n");
        sb.append("Environment:" + environment + "\n");
        sb.append("Version:" + version + "\n");
        return Response.ok(sb.toString()).build();
    }

    @GET
    @Path("hello")
    public Response printGreeting(@Auth User user) {
        final String greeting = String.format(template, user.getName());
        return Response.ok(greeting).build();
    }
}
