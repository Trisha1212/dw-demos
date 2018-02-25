package com.wwc.sg.dw.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
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
    @Path("hello/{name}")
    public Response printGreeting(@PathParam("name") String name) {
        final String greeting = String.format(template, name);
        return Response.ok(greeting).build();
    }
}
