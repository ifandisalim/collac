package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import configurations.GraphQLConfiguration;
import graphql.ExecutionResult;
import models.User;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import javax.inject.Inject;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private GraphQLConfiguration graphQLConfiguration;

    @Inject
    public HomeController(GraphQLConfiguration graphQLConfiguration) {
        this.graphQLConfiguration = graphQLConfiguration;
    }


    public Result index() {


        return ok("This server is up.");
    }


    public Result graphQL() {
        // Convert POST body to JSON
        JsonNode graphQLQueryJson = request().body().asJson();

        // Get the value for key query and convert to String
        String graphQLQuery = graphQLQueryJson.get("query").asText();
        // Execute query
        ExecutionResult queryResult = graphQLConfiguration.getGraphQL().execute(graphQLQuery);
        return ok(Json.toJson(queryResult));
    }

}
