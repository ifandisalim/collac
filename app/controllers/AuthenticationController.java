package controllers;

import errors.ErrorFactory;
import errors.JsonError;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import representations.AuthenticationRequest;
import services.AuthenticationService;

import javax.inject.Inject;

public class AuthenticationController extends Controller{

    private final FormFactory formFactory;
    private final AuthenticationService authenticationService;

    @Inject
    public AuthenticationController(FormFactory formFactory, AuthenticationService authenticationService) {
        this.formFactory = formFactory;
        this.authenticationService = authenticationService;
    }

    public Result authenticate() {
        Form<AuthenticationRequest> authenticationRequestForm = formFactory.form(AuthenticationRequest.class).bindFromRequest();
        if(authenticationRequestForm.hasErrors()) {
            return ErrorFactory.create(new JsonError(Http.Status.BAD_REQUEST,
                    "Validation error occurs",
                    authenticationRequestForm.errorsAsJson())
            );
        }

        AuthenticationRequest authenticationRequest = authenticationRequestForm.get();

        return authenticationService.authenticateByEmailAndPassword(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()
        );
    }

}
