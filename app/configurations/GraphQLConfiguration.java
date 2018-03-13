package configurations;

import GraphQLResolvers.EventResolver;
import GraphQLResolvers.RootQueryResolver;
import GraphQLResolvers.UserResolver;
import com.coxautodev.graphql.tools.SchemaParser;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import play.Environment;
import services.EventService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;

@Singleton
public class GraphQLConfiguration {

    private GraphQL graphQL;
    private final UserResolver userResolver;
    private final EventResolver eventResolver;

    @Inject
    public GraphQLConfiguration(UserResolver userResolver, EventResolver eventResolver) {
        this.userResolver = userResolver;
        this.eventResolver = eventResolver;
        this.graphQL = initializeGraphQL();
    }

    private GraphQL initializeGraphQL() {
        GraphQLSchema graphQLSchema = SchemaParser.newParser()
                .file("graphql/schema.graphqls")
                .resolvers(
                        new RootQueryResolver(),
                        userResolver,
                        eventResolver
                )
                .build()
                .makeExecutableSchema();

        return GraphQL.newGraphQL(graphQLSchema).build();
    }


    public GraphQL getGraphQL() {
        return graphQL;
    }
}
