/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.med.salemx.rest;

import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.med.salemx.controller.ControllerLoginE;
import org.med.salemx.model.Empresa;

/**
 *
 * @author DELL
 */
@Path("loginE")
public class RESTLoginE
{
    @POST
    @Path("validar")
    @Produces (MediaType.APPLICATION_JSON)
    public Response search(@FormParam ("u") @DefaultValue (" ") String u,
                           @FormParam ("c") @DefaultValue (" ") String c)
    {
        ControllerLoginE ch = new ControllerLoginE();
        List<Empresa> empresas = null;
        Gson gson = new Gson();
        String out = null;
        try
        {
            empresas = ch.search(u,c);           
            out = gson.toJson(empresas);
            
        }catch(Exception e)
        {
            e.printStackTrace();
            out = "{\"error:\""+e.toString()+"\"}";
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @POST
    @Path("validarToken")
    @Produces (MediaType.APPLICATION_JSON)
    public Response searchToken(@FormParam ("t") @DefaultValue ("null") String t)
    {
        ControllerLoginE ch = new ControllerLoginE();
        List<Empresa> empresas = null;
        Gson gson = new Gson();
        String out = null;
        if(t.equalsIgnoreCase("")){
            t="null";
        }
        try
        {
            empresas = ch.searchToken(t);
            
            out = gson.toJson(empresas);
            
        }catch(Exception e)
        {
            e.printStackTrace();
            out = "{\"error\":\""+e.toString()+"\"}";
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @POST
    @Path("deleteToken")
    @Produces (MediaType.APPLICATION_JSON)
    public Response deleteToken(@FormParam ("id") @DefaultValue ("0") int id)
    {
        ControllerLoginE ch = new ControllerLoginE();
        String out = null;
        try
        {
            ch.deleteToken(id);
            
            out = "{\"result\":\"OK\"}";
            
        }catch(Exception e)
        {
            e.printStackTrace();
            out = "{\"error:\""+e.toString()+"\"}";
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @GET
    @Path("validarAndroid")
    @Produces (MediaType.APPLICATION_JSON)
    public Response validar(@QueryParam ("u") @DefaultValue (" ") String u,
                           @QueryParam ("c") @DefaultValue (" ") String c)
    {
        ControllerLoginE ch = new ControllerLoginE();
        List<Empresa> empresas = null;
        String out = null;
        try
        {
            empresas = ch.search(u,c);
            if(empresas.size() == 0){
                out = "{\"token\":\"null\"}";
            }else{
                out = "{\"token\":\""+empresas.get(0).getToken()+"\"}";
            }            
            
        }catch(Exception e)
        {
            e.printStackTrace();
            out = "{\"error:\""+e.toString()+"\"}";
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }   
    
}

