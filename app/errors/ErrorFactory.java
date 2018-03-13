package errors;

import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

public class ErrorFactory {

    public static Result create(Error error) {
        return Results.status(error.getCode(), Json.toJson(error));
    }

    public static Result create(Integer errorCode, String errorMessage){
        Error error = new Error(errorCode, errorMessage);
        return Results.status(errorCode, Json.toJson(error));
    }


}
