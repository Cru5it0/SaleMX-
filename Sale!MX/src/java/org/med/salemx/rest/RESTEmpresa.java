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
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.med.salemx.controller.ControllerLoginE;
import org.med.salemx.controller.ControllerEmpresa;
import org.med.salemx.model.Empresa;

/**
 *
 * @author Zerox
 */
@Path("empresa")
public class RESTEmpresa extends Application {

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("token") String t) {
        ControllerEmpresa ce = new ControllerEmpresa();
        ControllerLoginE c1 = new ControllerLoginE();
        List<Empresa> empresa = null;
        Gson gson = new Gson();
        String out = null;
        try {
//            if (c1.searchToken(t).size() >= 1) {
                empresa = ce.selectAll();
                out = gson.toJson(empresa);
//            } else {
//                out = "{\"error\":\"null\"}";
//            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }

    @POST
    @Path("insert")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(
            @FormParam("fot") @DefaultValue("-") String fot,
            @FormParam("foto") @DefaultValue("-") String foto,
            @FormParam("nom") @DefaultValue("-") String nom,
            @FormParam("con") @DefaultValue("-") String con,
            @FormParam("dom") @DefaultValue("-") String dom,
            @FormParam("cor") @DefaultValue("-") String cor,
            @FormParam("use") @DefaultValue("-") String use,
            @FormParam("pas") @DefaultValue("-") String pas
    ) {
        Empresa objE = new Empresa();
        objE.setLogo(fot);
        objE.setFoto("");
        objE.setNombre(nom);
        objE.setContacto(con);
        objE.setDomicilio(dom);
        objE.setCorreo(cor);
        objE.setNomUsuario(use);
        objE.setContrasenia(pas);
        objE.setRol("Empresa");
        objE.setEstatus(1);

        ControllerEmpresa cs = new ControllerEmpresa();

        String out = null;

        try {
            int ng = cs.insert(objE);
            System.out.println(ng);
            //out = "{\"result\":\"OK\"}"; 
            out = "{\"result\":" + ng + "}";

        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();

    }

    @POST
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @FormParam("fot") @DefaultValue("-") String fot,
            @FormParam("foto") @DefaultValue("-") String foto,
            @FormParam("nom") @DefaultValue("-") String nom,
            @FormParam("con") @DefaultValue("-") String con,
            @FormParam("dom") @DefaultValue("-") String dom,
            @FormParam("cor") @DefaultValue("-") String cor,
            @FormParam("use") @DefaultValue("-") String use,
            @FormParam("pas") @DefaultValue("-") String pas,
            @FormParam("id") @DefaultValue("0") int id,
            @FormParam("token") String t
    ) {
        Empresa objE = new Empresa();
        objE.setIdEmpresa(id);
        objE.setNombre(nom);
        objE.setDomicilio(dom);
        objE.setLogo(fot);
        objE.setFoto(foto);
        objE.setCorreo(cor);
        objE.setContacto(con);
        objE.setNomUsuario(use);
        objE.setContrasenia(pas);

        ControllerEmpresa cs = new ControllerEmpresa();
        ControllerLoginE c1 = new ControllerLoginE();

        String out = null;
        try {
//            if (c1.searchToken(t).size() >= 1) {
                cs.update(objE);
                out = "{\"result\":\"OK\"}";
//            } else {
//                out = "{\"result\":\"null\"}";
//            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();

    }

    @POST
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@FormParam("id") int id,
            @FormParam("token") String t) {

        ControllerEmpresa cs = new ControllerEmpresa();
        ControllerLoginE c1 = new ControllerLoginE();

        String out = null;
        try {
//            if (c1.searchToken(t).size() >= 1) {
                cs.delete(id);
                out = "{\"result\":\"OK\"}";
//            } else {
//                out = "{\"result\":\"null\"}";
//            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();

    }
}
