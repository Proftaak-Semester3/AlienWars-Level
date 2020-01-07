
import AlienWarsLogin.AlienwarsLogin;
import Logic.UserLogic;
import restShared.LoginViewModel;
import restShared.RegisterViewModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AlienWarsRESTservice {

    private UserLogic userLogic;

    public AlienWarsRESTservice() {
        userLogic = new UserLogic();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginViewModel loginViewModel){
        if(userLogic.login(loginViewModel.getUsername(), loginViewModel.getPassword())){
            return Response.status(200).entity(RestResponseHelper.getSuccesResponse()).build();
        }
        return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(RegisterViewModel registerViewModel){
        if(userLogic.register(registerViewModel.getUsername(), registerViewModel.getEmail(), registerViewModel.getPassword(), registerViewModel.getPasswordCheck())){
            return Response.status(200).entity(RestResponseHelper.getSuccesResponse()).build();
        }else
            return Response.status(500).entity(RestResponseHelper.getErrorResponseString()).build();
    }
}
