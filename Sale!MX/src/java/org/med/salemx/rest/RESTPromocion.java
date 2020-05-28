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
import org.med.salemx.controller.ControllerPromocion;
import org.med.salemx.model.Promocion;
import org.med.salemx.model.ProductoServicio;
import org.med.salemx.model.Promociones;
import org.med.salemx.model.Sucursal;
/**
 *
 * @author Carlos
 */
@Path("promocion")
public class RESTPromocion  extends  Application{
    //Ver promociones activas
    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("token")String t,@QueryParam("idEmpresa") int iE){
        ControllerPromocion cp=new ControllerPromocion();
        ControllerLoginE c1=new ControllerLoginE();
        List<Promocion> promociones=null;
        Gson gson=new Gson();
        String out=null;
        try {
            if(c1.searchToken(t).size()>=1){
                promociones=cp.selectAll(iE);
                out=gson.toJson(promociones);
            }else{
                 out = "{\"error\":\"null\"}";
            }
        } catch (Exception e) {
             e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @GET
    @Path("getAll2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll2(@QueryParam("token")String t,@QueryParam("idEmpresa") int iE){
        ControllerPromocion cp=new ControllerPromocion();
        ControllerLoginE c1=new ControllerLoginE();
        List<Promocion> promociones=null;
        Gson gson=new Gson();
        String out=null;
        try {
            if(c1.searchToken(t).size()>=1){
                promociones=cp.selectAll2(iE);
                out=gson.toJson(promociones);
            }else{
                 out = "{\"error\":\"null\"}";
            }
        } catch (Exception e) {
             e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    //Insertar Promocion
    @POST
    @Path("insertPromo")
    @Produces(MediaType.APPLICATION_JSON) //Produce una aplicacion JSON
    //CONSUMMER EL VA A A CONSUMIR UNA APLICACION JSON

    public Response insert(@FormParam("titulo") @DefaultValue("-") String titulo,
            @FormParam("fechaInicio") @DefaultValue("-") String finicio,
            @FormParam("fechaTermino") @DefaultValue("-")String ftermino,
            @FormParam("imagen") @DefaultValue("-") String imagen,
            @FormParam("descripcion") @DefaultValue("-") String descripcion,
            @FormParam("precio") double precio,
            @FormParam("descuento") double descuento,
            @FormParam("idPS") int idPS,
            @FormParam("idS") int idS,
            @FormParam("token") String t
    ) {
        Promocion objPromo = new Promocion();
        ProductoServicio ps=new ProductoServicio();
        Sucursal s=new Sucursal();
        objPromo.setTitulo(titulo);
        objPromo.setFechaInicioAsString(finicio);
        objPromo.setFechaTerminoAsString(ftermino);
        objPromo.setImagen(imagen);
        objPromo.setDescripcion(descripcion);
        objPromo.setPrecio(precio);
        objPromo.setDescuento(descuento);
        ps.setIdProductoServicio(idPS);
        s.setIdSucursal(idS);
        objPromo.setProductoServicio(ps);
        objPromo.setSucursal(s);

        ControllerPromocion cs = new ControllerPromocion();
        ControllerLoginE c1 = new ControllerLoginE();
        String out = null;
        try {
            if (c1.searchToken(t).size() >= 1) {
                int ng = cs.insert(objPromo);
                //System.out.println(ng);
                //out = "{\"result\":\"OK\"}"; 
                out = "{\"result\":" + ng + "}";
            }
            else
            {
                out = "{\"error\":\"null\"}";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();

    }
    @POST
    @Path("updatePromo")
    @Produces(MediaType.APPLICATION_JSON) //Produce una aplicacion JSON
    public Response updatePromo(@FormParam("idPromocion") @DefaultValue("0")int idPromo,
            @FormParam("titulo") @DefaultValue("-") String titulo,
            @FormParam("fechaInicio") @DefaultValue("-") String finicio,
            @FormParam("fechaTermino") @DefaultValue("-")String ftermino,
            @FormParam("imagen") @DefaultValue("-") String imagen,
            @FormParam("descripcion") @DefaultValue("-") String descripcion,
            @FormParam("precio") double precio,
            @FormParam("descuento") double descuento,
            @FormParam("idPS") int idPS,
            @FormParam("idS") int idS,
            @FormParam("token") String t
    ) {
        Promocion objPromo = new Promocion();
        ProductoServicio ps=new ProductoServicio();
        Sucursal s=new Sucursal();
        objPromo.setIdPromocion(idPromo);
        objPromo.setTitulo(titulo);
        objPromo.setFechaInicioAsString(finicio);
        objPromo.setFechaTerminoAsString(ftermino);
        objPromo.setImagen(imagen);
        objPromo.setDescripcion(descripcion);
        objPromo.setPrecio(precio);
        objPromo.setDescuento(descuento);
        ps.setIdProductoServicio(idPS);
        s.setIdSucursal(idS);
        objPromo.setProductoServicio(ps);
        objPromo.setSucursal(s);

        ControllerPromocion cs = new ControllerPromocion();
        ControllerLoginE c1 = new ControllerLoginE();
        String out = null;
        try {
            if (c1.searchToken(t).size() >= 1) {
                cs.update(objPromo);
                //System.out.println(ng);
                out = "{\"result\":\"OK\"}"; 
                //out = "{\"result\":" + ng + "}";
            }
            else
            {
                out = "{\"error\":\"null\"}";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();

    }
    
    @POST
    @Path("deletePromo")
    @Produces(MediaType.APPLICATION_JSON) //Produce una aplicacion JSON
    public Response deletePromo(@FormParam("idPromocion") @DefaultValue("0")int idPromo,
            @FormParam("token") String t
    ) {
        Promocion objPromo = new Promocion();
       
        objPromo.setIdPromocion(idPromo);
       
        ControllerPromocion cs = new ControllerPromocion();
        ControllerLoginE c1 = new ControllerLoginE();
        String out = null;
        try {
            if (c1.searchToken(t).size() >= 1) {
                cs.delete(idPromo);
                //System.out.println(ng);
                out = "{\"result\":\"OK\"}"; 
                //out = "{\"result\":" + ng + "}";
            }
            else
            {
                out = "{\"error\":\"null\"}";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();

    }
    
    @POST
    @Path("activatePromo")
    @Produces(MediaType.APPLICATION_JSON) //Produce una aplicacion JSON
    public Response activatePromo(@FormParam("idPromocion") @DefaultValue("0")int idPromo,
            @FormParam("token") String t
    ) {
        Promocion objPromo = new Promocion();
       
        objPromo.setIdPromocion(idPromo);
       
        ControllerPromocion cs = new ControllerPromocion();
        ControllerLoginE c1 = new ControllerLoginE();
        String out = null;
        try {
            if (c1.searchToken(t).size() >= 1) {
                cs.activate(idPromo);
                //System.out.println(ng);
                out = "{\"result\":\"OK\"}"; 
                //out = "{\"result\":" + ng + "}";
            }
            else
            {
                out = "{\"error\":\"null\"}";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @GET
    @Path("getAllCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCliente(@QueryParam("token")String t){
        ControllerPromocion cp=new ControllerPromocion();
        ControllerLoginC c1=new ControllerLoginC();
        List<Promocion> promociones=null;
        Gson gson=new Gson();
        String out=null;
        try {
            if(c1.searchToken(t).size()>=1){
                promociones=cp.selectAllCliente();
                out=gson.toJson(promociones);
            }else{
                 out = "{\"error\":\"null\"}";
            }
        } catch (Exception e) {
             e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @GET
    @Path("getAllA")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllA(@QueryParam("p") int ip){
        ControllerPromocion cp=new ControllerPromocion();
        List<Promociones> promociones=null;
        Gson gson=new Gson();
        String out=null;
        try {
                promociones=cp.selectAllClienteA(ip);
                out=gson.toJson(promociones);

        } catch (Exception e) {
             e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }    
    
    @GET
    @Path("getAllAS")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAS(@QueryParam("s") int is){
        ControllerPromocion cp=new ControllerPromocion();
        List<Promociones> promociones=null;
        Gson gson=new Gson();
        String out=null;
        try {
                promociones=cp.selectAllArduino(is);
                out=gson.toJson(promociones);

        } catch (Exception e) {
             e.printStackTrace();
            out = "{\"error:\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }    
    
}
