package helpers;

import com.fasterxml.jackson.databind.JsonNode;
import play.data.Form;

public class FormValidationHelper {

    public JsonNode validateFormErrors(Form form) {
        if(form.hasErrors()) {
            return form.errorsAsJson();
        }

        return null;
    }
}
