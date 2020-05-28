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
import org.med.salemx.controller.ControllerLoginC;
import org.med.salemx.controller.ControllerLoginE;
import org.med.salemx.controller.ControllerSucursal;
import org.med.salemx.model.Sucursal;

/**
 *
 * @author DELL
 */
@Path("sucursal")
public class RESTSucursal extends Application {

    //Ver sucurales activas
    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("token") String t, @QueryParam("idEmpresa") int iE) {
        ControllerSucursal cs = new ControllerSucursal();
        ControllerLoginE c1 = new ControllerLoginE();
        List<Sucursal> sucursales = null;
        Gson gson = new Gson();
        String out = null;
        try {
            if (c1.searchToken(t).size() >= 1) {
                sucursales = cs.selectAll(iE);
                out = gson.toJson(sucursales);
            } else {
                out = "{\"error\":\"null\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }

    //Ver sucurales activas para promocion
    @GET
    @Path("getAllForPromo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllForPromo(@QueryParam("token") String t, @QueryParam("idEmpresa") int IE) {
        ControllerSucursal cs = new ControllerSucursal();
        ControllerLoginE c1 = new ControllerLoginE();
        List<Sucursal> sucursales = null;
        Gson gson = new Gson();
        String out = null;
        try {
            if (c1.searchToken(t).size() >= 1) {
                sucursales = cs.selectAllForEmpresa(IE);
                out = gson.toJson(sucursales);
            } else {
                out = "{\"error\":\"null\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }

    //Buscar sucursal
    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("s") @DefaultValue(" ") String s,
            @QueryParam("token") String t) {
        ControllerSucursal cs = new ControllerSucursal();
        ControllerLoginE c1 = new ControllerLoginE();
        List<Sucursal> sucursales = null;
        Gson gson = new Gson();
        String out = null;
        try {
            if (c1.searchToken(t).size() >= 1) {
                sucursales = cs.search(s);
                out = gson.toJson(sucursales);
            } else {
                out = "{\"error\":\"null\"}";
            }

        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }

    @GET
    @Path("searchN")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchN(@QueryParam("s") @DefaultValue(" ") String s,
            @QueryParam("token") String t) {
        ControllerSucursal cs = new ControllerSucursal();
        ControllerLoginE c1 = new ControllerLoginE();
        List<Sucursal> sucursales = null;
        Gson gson = new Gson();
        String out = null;
        try {
            if (c1.searchToken(t).size() >= 1) {
                sucursales = cs.searchN(s);
                out = gson.toJson(sucursales);
            } else {
                out = "{\"error\":\"null\"}";
            }

        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }

    //Insertar Sucursal
    @POST
    @Path("insert")
    @Produces(MediaType.APPLICATION_JSON) //Produce una aplicacion JSON
    //CONSUMMER EL VA A A CONSUMIR UNA APLICACION JSON

    public Response insert(@FormParam("dom") @DefaultValue("-") String dom,
            @FormParam("nom") @DefaultValue("-") String nom,
            @FormParam("lat") double lat,
            @FormParam("lng") double lng,
            @FormParam("foto") @DefaultValue("-") String foto,
            @FormParam("idE") int idE,
            @FormParam("token") String t
    ) {
        Sucursal objSucursal = new Sucursal();
        objSucursal.setNombre(nom);
        objSucursal.setDomicilio(dom);
        objSucursal.setLatitud(lat);
        objSucursal.setLongitud(lng);
        objSucursal.setFoto(foto);
        objSucursal.setIdEmpresa(idE);

        ControllerSucursal cs = new ControllerSucursal();
        ControllerLoginE c1 = new ControllerLoginE();
        String out = null;
        try {
            if (c1.searchToken(t).size() >= 1) {
                int ng = cs.insert(objSucursal);
                System.out.println(ng);
                //out = "{\"result\":\"OK\"}"; 
                out = "{\"result\":" + ng + "}";
            } else {
                out = "{\"error\":\"null\"}";
            }

        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();

    }

    //Actualizar Sucursal
    @POST
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("dom") @DefaultValue("-") String dom,
            @FormParam("nom") @DefaultValue("-") String nom,
            @FormParam("lat") double lat,
            @FormParam("lng") double lng,
            @FormParam("foto") String foto,
            @FormParam("idE") int idE,
            @FormParam("idS") int idS,
            @FormParam("token") String t
    ) {
        Sucursal objSucursal = new Sucursal();
        objSucursal.setDomicilio(dom);
        objSucursal.setNombre(nom);
        objSucursal.setLatitud(lat);
        objSucursal.setLongitud(lng);
        objSucursal.setFoto(foto);
        objSucursal.setIdEmpresa(idE);
        objSucursal.setIdSucursal(idS);

        ControllerSucursal cs = new ControllerSucursal();
        ControllerLoginE c1 = new ControllerLoginE();

        String out = null;
        try {
            if (c1.searchToken(t).size() >= 1) {
                cs.update(objSucursal);
                out = "{\"result\":\"OK\"}";
            } else {
                out = "{\"result\":\"null\"}";
            }
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

        ControllerSucursal cs = new ControllerSucursal();
        ControllerLoginE c1 = new ControllerLoginE();

        String out = null;
        try {
            if (c1.searchToken(t).size() >= 1) {
                cs.delete(id);
                out = "{\"result\":\"OK\"}";
            } else {
                out = "{\"result\":\"null\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();

    }

    @POST
    @Path("activar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response activar(@FormParam("id") int id,
            @FormParam("token") String t) {

        ControllerSucursal cs = new ControllerSucursal();
        ControllerLoginE c1 = new ControllerLoginE();

        String out = null;
        try {
            if (c1.searchToken(t).size() >= 1) {
                cs.activate(id);
                out = "{\"result\":\"OK\"}";
            } else {
                out = "{\"result\":\"null\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();

    }

    @GET
    @Path("getInac")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInac(@QueryParam("token") String t) {
        ControllerSucursal cs = new ControllerSucursal();
        ControllerLoginE c1 = new ControllerLoginE();

        List<Sucursal> sucursales = null;
        Gson gson = new Gson();
        String out = null;
        try {
            if (c1.searchToken(t).size() >= 1) {
                sucursales = cs.selectAllNoDisponible();
                out = gson.toJson(sucursales);
            } else {
                out = "{\"error\":\"null\"}";
            }

        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

    //Ver sucurales activas
    @GET
    @Path("getAllCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCliente(@QueryParam("token") String t) {
        ControllerSucursal cs = new ControllerSucursal();
        ControllerLoginC c1 = new ControllerLoginC();
        List<Sucursal> sucursales = null;
        Gson gson = new Gson();
        String out = null;
        try {
            if (c1.searchToken(t).size() >= 1) {
                sucursales = cs.selectAllCliente();
                out = gson.toJson(sucursales);
            } else {
                out = "{\"error\":\"null\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }

    //Ver sucurales activas para arduino
    @GET
    @Path("getAllClienteA")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllClienteA() {
        ControllerSucursal cs = new ControllerSucursal();
        ControllerLoginC c1 = new ControllerLoginC();
        List<Sucursal> sucursales = null;
        Gson gson = new Gson();
        String out = null;
        try {
            sucursales = cs.selectAllCliente();
            out = gson.toJson(sucursales);
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }
    
}
