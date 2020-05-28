package org.med.salemx.rest;



import org.med.salemx.controller.ControllerCliente;
import org.med.salemx.model.Cliente;
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

/**
 *
 * @author angel
 */
@Path("cliente")
public class RESTCliente extends Application {

    public static final int STATUS_ACTIVO = 1;
    public static final int STATUS_INACTIVO = 0;
    public static final String Rol_Admin = "Empresa";
    public static final String Rol_Cliente = "Cliente";

    @POST
    @Path("insert")
    @Produces(MediaType.APPLICATION_JSON)

    public Response insert(
            @FormParam("nom") @DefaultValue("-") String nombre,
            @FormParam("apepa") @DefaultValue("-") String apepaterno,
            @FormParam("apema") @DefaultValue("-") String apematerno,
            @FormParam("gen") @DefaultValue("-") String genero,
            @FormParam("dom") @DefaultValue("-") String domicilio,
            @FormParam("tel") @DefaultValue("-") String telefono,
            @FormParam("usuario") @DefaultValue("-") String usuario,
            @FormParam("contra") @DefaultValue("-") String pass,
            @FormParam("correo") @DefaultValue("-") String email
    //,@FormParam("t") @DefaultValue("-") String token
    ) {

        ControllerCliente ctrlP = new ControllerCliente();
        Cliente objP = new Cliente();
        objP.setNombre(nombre);
        objP.setApellidoPaterno(apepaterno);
        objP.setApellidoMaterno(apematerno);
        objP.setGenero(genero);
        objP.setDomicilio(domicilio);
        objP.setTelefono(telefono);
        objP.setNombreUsuario(usuario);
        objP.setCorreo(email);
        objP.setContrasenia(pass);
        objP.setEstatus(STATUS_ACTIVO);
        objP.setRol(Rol_Cliente);

        String salida = null;
        try {
//            if (ctrlP.validarToken(token) != null) {

            int out = ctrlP.insertCliente(objP);
            salida = "{\"result\":\"" + out + "\"}";
//            } else {
//                salida = "{\"result\":\"No Token\"}";
//            }
            //Si lanza 1 es que funciona y si es 0 hay error
        } catch (Exception e) {
            salida = "{\"result\":\"error\"}";
        }
        return Response.status(Response.Status.OK).entity(salida).build();
    }

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllac() {
        //,@QueryParam("t") @DefaultValue("-") String token) {
        List<Cliente> cliente = null;
        Gson json = new Gson();

        ControllerCliente ctrlP = new ControllerCliente();

        String salida = null;
        try {
//            if (ctrlP.validarToken(token) != null) {
            cliente = ctrlP.selectAct();
            salida = json.toJson(cliente);
//            } else {
//                salida = "{\"result\":\"No Token\"}";
//            }
        } catch (Exception e) {
            e.printStackTrace();
            salida = "{\"result\":\"error\"}";
        }
        return Response.status(Response.Status.OK).entity(salida).build();
    }

    @GET
    @Path("getAllNo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIn() {
        //,@QueryParam("t") @DefaultValue("-") String token) {

        List<Cliente> cliente = null;
        Gson json = new Gson();

        ControllerCliente ctrlP = new ControllerCliente();

        String salida = null;
        try {
//            if (ctrlP.validarToken(token) != null) {
            cliente = ctrlP.selectInac();
            salida = json.toJson(cliente);
//            } else {
//                salida = "{\"result\":\"No Token\"}";
//            }
        } catch (Exception e) {
            e.printStackTrace();
            salida = "{\"result\":\"error\"}";
        }
        return Response.status(Response.Status.OK).entity(salida).build();
    }

    @POST
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @FormParam("nom") @DefaultValue("-") String nombre,
            @FormParam("apepa") @DefaultValue("-") String apepaterno,
            @FormParam("apema") @DefaultValue("-") String apematerno,
            @FormParam("gen") @DefaultValue("-") String genero,
            @FormParam("dom") @DefaultValue("-") String domicilio,
            @FormParam("tel") @DefaultValue("-") String telefono,
            @FormParam("usuario") @DefaultValue("-") String usuario,
            @FormParam("contra") @DefaultValue("-") String pass,
            @FormParam("correo") @DefaultValue("-") String email,
            @FormParam("id") @DefaultValue("0") int id
    //,@FormParam("t") @DefaultValue("-") String token
    ) {

        ControllerCliente ctrlP = new ControllerCliente();
        Cliente objP = new Cliente();
        objP.setIdCliente(id);
        objP.setNombre(nombre);
        objP.setApellidoPaterno(apepaterno);
        objP.setApellidoMaterno(apematerno);
        objP.setGenero(genero);
        objP.setDomicilio(domicilio);
        objP.setTelefono(telefono);
        objP.setNombreUsuario(usuario);
        objP.setCorreo(email);
        objP.setContrasenia(pass);
        objP.setEstatus(STATUS_ACTIVO);
        objP.setRol(Rol_Cliente);

        String salida = null;
        try {
//            if (ctrlP.validarToken(token) != null) {
            int out = ctrlP.updateCliente(objP);
            salida = "{\"result\":\"" + out + "\"}";
//            } else {
//                salida = "{\"result\":\"No Token\"}";
//            }
        } catch (Exception e) {
            salida = "{\"result\":\"error\"}";
        }
        return Response.status(Response.Status.OK).entity(salida).build();
    }

    @POST
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @FormParam("id") @DefaultValue("0") int id
    //,@FormParam("t") @DefaultValue("-") String token
    ) {

        ControllerCliente ctrlP = new ControllerCliente();
        int idCliente = id;
        String salida = null;
        try {
//            if (ctrlP.validarToken(token) != null) {
            int out = ctrlP.deleteCliente(idCliente);
            salida = "{\"result\":\"" + out + "\"}";
//            } else {
//                salida = "{\"result\":\"No Token\"}";
//            }
        } catch (Exception e) {
            salida = "{\"result\":\"error\"}";
        }
        return Response.status(Response.Status.OK).entity(salida).build();

    }

    @POST
    @Path("activar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response activarCliente(
            @FormParam("id") @DefaultValue("0") int id
    ) {

        ControllerCliente ctrlP = new ControllerCliente();
        int idCliente = id;
        String salida = null;
        try {
//            if (ctrlP.validarToken(token) != null) {
            int out = ctrlP.activarCliente(idCliente);
            salida = "{\"result\":\"" + out + "\"}";

//            } else {
//                salida = "{\"result\":\"No Token\"}";
//            }
        } catch (Exception e) {
            salida = "{\"result\":\"error\"}";
        }
        return Response.status(Response.Status.OK).entity(salida).build();

    }

    @GET
    @Path("searchAc")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchAc(@QueryParam("s") @DefaultValue("") String s) {
        List<Cliente> cliente = null;
        Gson json = new Gson();

        ControllerCliente ctrlP = new ControllerCliente();

        String salida = null;
        try {
            //            if (ctrlP.validarToken(token) != null) {
            cliente = ctrlP.searchAct(s);
            salida = json.toJson(cliente);
            //            } else {
//                salida = "{\"result\":\"No Token\"}";
//            }
        } catch (Exception e) {
            e.printStackTrace();
            salida = "{\"result\":\"error\"}";
        }
        return Response.status(Response.Status.OK).entity(salida).build();
    }

    @GET
    @Path("searchIn")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchIn(@QueryParam("s") @DefaultValue("") String s) {
        List<Cliente> cliente = null;
        Gson json = new Gson();

        ControllerCliente ctrlP = new ControllerCliente();

        String salida = null;
        try {
            //            if (ctrlP.validarToken(token) != null) {
            cliente = ctrlP.searchInac(s);
            salida = json.toJson(cliente);
            //            } else {
//                salida = "{\"result\":\"No Token\"}";
//            }
        } catch (Exception e) {
            e.printStackTrace();
            salida = "{\"result\":\"error\"}";
        }
        return Response.status(Response.Status.OK).entity(salida).build();
    }

}






























//    
//    @POST
//    @Path("validarLToken")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response buscar(@FormParam("u") @DefaultValue(" ") String u,
//            @FormParam("c") @DefaultValue(" ") String c) {
//        ControllerCliente ch = new ControllerCliente();
//        List<Cliente> usuarios = null;
//        Gson gson = new Gson();
//        String out = null;
//        try {
//            usuarios = ch.generarTokenAlmacenar(u, c);
//
//            out = gson.toJson(usuarios);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            out = "{\"error:\"" + e.toString() + "\"}";
//        }
//        return Response.status(Response.Status.OK).entity(out).build();
//    }
//}
