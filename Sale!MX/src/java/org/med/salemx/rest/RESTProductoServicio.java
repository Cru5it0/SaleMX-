/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.med.salemx.rest;

import com.google.gson.Gson;
import java.util.ArrayList;
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

import org.med.salemx.controller.ControladorPS;
import org.med.salemx.controller.ControllerLoginE;
import org.med.salemx.controller.ControllerSucursal;
import org.med.salemx.model.Empresa;
import org.med.salemx.model.ProductoServicio;
import org.med.salemx.model.Sucursal;

/**
 *
 * @author Carlos
 */
@Path("productoservicio")
public class RESTProductoServicio extends Application {

   @POST
    @Path("insertarps")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertarPS(@FormParam("nombre") @DefaultValue("-") String nombre,
            @FormParam("foto") @DefaultValue("-") String foto,
            @FormParam("precio") @DefaultValue("0") double precio,
            @FormParam("descripcion") @DefaultValue("-") String descripcion,
            @FormParam("idEmpresa") @DefaultValue("0") int idEmpresa
    ) {
        ControladorPS cps = new ControladorPS();
        ProductoServicio ps = new ProductoServicio();
        Empresa e = new Empresa();
        ps.setNombre(nombre);
        ps.setFoto(foto);
        ps.setPrecio(precio);
        ps.setDescripcion(descripcion);
        e.setIdEmpresa(idEmpresa);
        //e.setNombre(empresa);
        ps.setEmpresa(e);
        String salida = null;
        try {
//            ControladorLogin cl = new ControladorLogin();
//            String token = cl.validarToken(idEmpresa);
 //           if (token != "" || token != null) {
                cps.insertProSer(ps);
                salida = "{\"result\":\"OK\"}";
//            } else {
 //               salida = "{\"errorToken\":\"OK\"}";
//            }
        } catch (Exception ex) {
            salida = "{\"result\":\"error\"}";

        }
        return Response.status(Response.Status.OK).entity(salida).build();

    }

    @GET
    @Path("getAllps")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllps(@QueryParam("idEmpresa") @DefaultValue("0") int idEmpresa) {

        List<ProductoServicio> ps = new ArrayList<>();
        Gson json = new Gson();

        ControladorPS ctPS = new ControladorPS();

        String salida = null;
        try {
//            ControladorLogin cl = new ControladorLogin();
//            Empresa em = new Empresa();
//            String token = cl.validarToken(idEmpresa);

            ps = ctPS.getAll(idEmpresa);
//            if (token != "" || token != null) {
                salida = json.toJson(ps);
//            } else {
//                salida = "{\"errorToken\":\"OK\"}";
               // System.out.println("Error en token");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            salida = "{\"result\":\"error\"}";
        }
        //System.out.println("SALIDA -> " + salida);
        return Response.status(Response.Status.OK).entity(salida).build();
    }

    @GET
    @Path("get2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll02(@QueryParam("letra") @DefaultValue("0") int id) {

        List<ProductoServicio> ps = new ArrayList<>();
        Gson json = new Gson();

        ControladorPS ctPS = new ControladorPS();

        String salida = null;
        try {
            Empresa em = new Empresa();
            ps = ctPS.getAll02(id);
                salida = json.toJson(ps);
        } catch (Exception e) {
            e.printStackTrace();
            salida = "{\"result\":\"error\"}";
        }
        //System.out.println("SALIDA -> " + salida);
        return Response.status(Response.Status.OK).entity(salida).build();
    }
    
    @GET
    @Path("getAllInactivops")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInactivoPS(@QueryParam("idEmpresa") @DefaultValue("0") int idEmpresa) {
        List<ProductoServicio> ps = new ArrayList<>();
        Gson json = new Gson();

        ControladorPS ctPS = new ControladorPS();

        String salida = null;
        try {
            ps = ctPS.getAllInactivos(idEmpresa);
            salida = json.toJson(ps);
        } catch (Exception e) {
            e.printStackTrace();
            salida = "{\"result\":\"error\"}";
        }
        // System.out.println("SALIDA -> " + salida);
        return Response.status(Response.Status.OK).entity(salida).build();
    }

    @POST
    @Path("updateps")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @FormParam("idProductoServicio") @DefaultValue("0") int id,
            @FormParam("nombre") @DefaultValue("-") String nombre,
            @FormParam("foto") @DefaultValue("-") String foto,
            @FormParam("precio") @DefaultValue("0") double precio,
            @FormParam("descripcion") @DefaultValue("-") String descripcion,
            @FormParam("idEmpresa") @DefaultValue("0") int idEmpresa
    ) {

        ControladorPS cps = new ControladorPS();
        ProductoServicio objPs = new ProductoServicio();
        Empresa em=new Empresa();
        objPs.setIdProductoServicio(id);
        objPs.setNombre(nombre);
        objPs.setFoto(foto);
        objPs.setPrecio(precio);
        objPs.setDescripcion(descripcion);
        em.setIdEmpresa(idEmpresa);
        objPs.setEmpresa(em);
        String salida = null;
        try {
//            ControladorLogin cl = new ControladorLogin();
//            String token = cl.validarToken(idEmpresa);
//            if (token != "" || token != null) {
                cps.updateProSer(objPs);
                //salida = "{\"result\":\"ok\"}";
           // } else {
                salida = "{\"errorToken\":\"OK\"}";
                //System.out.println("error en token");
           // }
        } catch (Exception e) {
            //System.out.println(e.toString());
            salida = "{\"result\":\"error\"}";
        }
        return Response.status(Response.Status.OK).entity(salida).build();

    }

    @POST
    @Path("deleteps")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePS(
            @FormParam("id") @DefaultValue("0") int id,
            @FormParam("idEmpresa") @DefaultValue("0") int idEmpresa
    ) {

        ControladorPS cps = new ControladorPS();

        String salida = null;
        try {
//            ControladorLogin cl = new ControladorLogin();
//            String token = cl.validarToken(idEmpresa);
//            if (token != "" || token != null) {
                cps.deleteProSer(id);
//                salida = "{\"result\":\"OK\"}";
//            } else {
//                salida = "{\"errorToken\":\"OK\"}";
//            }
        } catch (Exception e) {
            salida = "{\"result\":\"error\"}";
        }
        return Response.status(Response.Status.OK).entity(salida).build();

    }

    
    //para promocion
    @GET
    @Path("getAllForPromo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllForPromo(@QueryParam("idEmpresa") @DefaultValue("0") int idEmpresa) {

        List<ProductoServicio> ps = new ArrayList<>();
        Gson json = new Gson();

        ControladorPS ctPS = new ControladorPS();

        String salida = null;
        try {
//            ControladorLogin cl = new ControladorLogin();
//            Empresa em = new Empresa();
//            String token = cl.validarToken(idEmpresa);

            ps = ctPS.getAllForEmpresa(idEmpresa);
//            if (token != "" || token != null) {
            salida = json.toJson(ps);
//            } else {
//                salida = "{\"errorToken\":\"OK\"}";
//               // System.out.println("Error en token");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            salida = "{\"result\":\"error\"}";
        }
        //System.out.println("SALIDA -> " + salida);
        return Response.status(Response.Status.OK).entity(salida).build();
    }

  
    @POST
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePS(@FormParam("idPS") @DefaultValue("0") int id,
            @FormParam("nom") @DefaultValue("-") String nombre,
            @FormParam("fot") @DefaultValue("-") String foto,
            @FormParam("pre") @DefaultValue("0") double precio,
            @FormParam("des") @DefaultValue("-") String descripcion,
            @FormParam("idE") @DefaultValue("0") int idE
    ) {
        ControladorPS cps = new ControladorPS();
        ProductoServicio objPs = new ProductoServicio();
        Empresa em=new Empresa();
        objPs.setIdProductoServicio(id);
        objPs.setNombre(nombre);
        objPs.setFoto(foto);
        objPs.setPrecio(precio);
        objPs.setDescripcion(descripcion);
        em.setIdEmpresa(idE);
        objPs.setEmpresa(em);

        String out = null;
        try {
            cps.updateProSer(objPs);
            out = "{\"result\":\"OK\"}";

        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();

    }

    
    @POST
    @Path("activatePS")
    @Produces(MediaType.APPLICATION_JSON)
    public Response activatePS(
            @FormParam("id") @DefaultValue("0") int id
    ) {

        ControladorPS cps = new ControladorPS();

        String salida = null;
        try {
//            ControladorLogin cl = new ControladorLogin();
//            String token = cl.validarToken(idEmpresa);
//            if (token != "" || token != null) {
            cps.activatProSer(id);
            salida = "{\"result\":\"OK\"}";
//            } else {
//                salida = "{\"errorToken\":\"OK\"}";
//            }
        } catch (Exception e) {
            salida = "{\"result\":\"error\"}";
        }
        return Response.status(Response.Status.OK).entity(salida).build();

    }

//    @POST
//    @Path("activar")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response activar(
//            @FormParam("id") @DefaultValue("0") int id
//    ) {
//
//        ControladorPSMongo ctps = new ControladorPSMongo();
//        ProductoServicio objps = new ProductoServicio();
//        objps.setIdProductoServicio(id);
//
//        String salida = null;
//        try {
//            //ctps.
//            //salida = "{\"result\":\"OK\"}";
//        } catch (Exception e) {
//            salida = "{\"result\":\"error\"}";
//        }
//        return Response.status(Response.Status.OK).entity(salida).build();
//
//    }
    
    @GET
    @Path("getAllpsCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllpsCliente() {

        List<ProductoServicio> ps = new ArrayList<>();
        Gson json = new Gson();

        ControladorPS ctPS = new ControladorPS();

        String salida = null;
        try {
//            ControladorLogin cl = new ControladorLogin();
//            Empresa em = new Empresa();
//            String token = cl.validarToken(idEmpresa);

            ps = ctPS.getAllCliente();
//            if (token != "" || token != null) {
                salida = json.toJson(ps);
//            } else {
//                salida = "{\"errorToken\":\"OK\"}";
               // System.out.println("Error en token");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            salida = "{\"result\":\"error\"}";
        }
        //System.out.println("SALIDA -> " + salida);
        return Response.status(Response.Status.OK).entity(salida).build();
    }
    
}
