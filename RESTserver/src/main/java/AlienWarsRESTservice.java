
import AlienWarsLogin.AlienwarsLogin;
import restShared.AccountDTO;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AlienWarsRESTservice {

    private AlienwarsLogin alienWarsLogin;

    public AlienWarsRESTservice() {
        alienWarsLogin = new AlienwarsLogin();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(AccountDTO accountDTO){
        if(alienWarsLogin.login(accountDTO.getUsername(), accountDTO.getPassword())){
            return Response.status(200).entity(RestResponseHelper.getSuccesResponse()).build();
        }
        return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(AccountDTO accountDTO){
        if(alienWarsLogin.register(accountDTO.getUsername(),accountDTO.getPassword(), accountDTO.getEmailRegister())){
            return Response.status(200).entity(RestResponseHelper.getSuccesResponse()).build();
        }else
            return Response.status(500).entity(RestResponseHelper.getErrorResponseString()).build();
    }
}
