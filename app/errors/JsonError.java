package errors;

import com.fasterxml.jackson.databind.JsonNode;



public class JsonError extends Error {

    private JsonNode jsonError;

    public JsonError(JsonNode jsonError) {
        this.jsonError = jsonError;
    }

    public JsonError(Integer code, String message, JsonNode jsonError) {
        super(code, message);
        this.jsonError = jsonError;
    }

    public JsonNode getJsonError() {
        return jsonError;
    }

    public void setJsonError(JsonNode jsonError) {
        this.jsonError = jsonError;
    }
}
