
import AlienWarsLogin.AlienwarsLogin;
import restShared.AccountDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AlienWarsRESTservice {

    private AlienwarsLogin seaBattleLogin;

    public AlienWarsRESTservice() {
        seaBattleLogin = new AlienwarsLogin();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(AccountDTO accountDTO){
        if(seaBattleLogin.login(accountDTO.getUsername(), accountDTO.getPassword())){
            return Response.status(200).entity(RestResponseHelper.getSuccesResponse()).build();
        }
        return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
    }
}
