package HttpCall;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import restShared.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AuthenticationRequest {
    private final String url = "http://localhost:1234/auth";
    private final Gson gson = new Gson();
    private final int NOTDEFINED = -1;

    public boolean login(String user, String password){
        LoginViewModel loginViewModel = new LoginViewModel(user, password);
        String queryPost = "/login";
        AccountResponse response = executeQueryPost(loginViewModel, queryPost);
        return response.isSuccess();
    }

    public boolean register(String email, String user, String password, String passwordCheck){
        RegisterViewModel registerViewModel = new RegisterViewModel(email , user, password, passwordCheck);
        String queryPost = "/register";
        AccountResponse response = executeQueryPost(registerViewModel, queryPost);
        return response.isSuccess();
    }

    private AccountResponse executeQueryPost(AccountModel account, String queryPost) {
        // Build the query for the REST service
        final String query = url + queryPost;

        // Execute the HTTP POST request
        HttpPost httpPost = new HttpPost(query);
        httpPost.addHeader("content-type", "application/json");
        StringEntity params;
        try {
            params = new StringEntity(gson.toJson(account));
            httpPost.setEntity(params);
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex);
        }
        return executeHttpUriRequest(httpPost);
    }

    private AccountResponse executeHttpUriRequest(HttpUriRequest httpUriRequest) {
        // Execute the HttpUriRequest
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpUriRequest)) {
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            return gson.fromJson(entityString, AccountResponse.class);
        } catch (IOException | JsonSyntaxException e) {
            System.out.println(e);
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setSuccess(false);
            return accountResponse;
        }
    }
}
