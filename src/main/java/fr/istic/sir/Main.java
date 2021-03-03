package fr.istic.sir;

import fr.istic.sir.jpa.dao.*;
import fr.istic.sir.jpa.entities.*;
import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(API_Application.class.getName());

    public static void main( String[] args ) throws ParseException {


        UndertowJaxrsServer ut = new UndertowJaxrsServer();

        API_Application app = new API_Application();

        ut.deploy(app);

        ut.start( Undertow.builder().addHttpListener(8080, "localhost") );

        logger.info("JAX-RS based micro-service running!");

    }

}
