package net.kprod.aalarmui.service;

import net.kprod.aalarmui.enums.EnumEventStatus;
import net.kprod.aalarmui.exception.ServiceException;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by kemkem on 3/1/18.
 */
@Service
public class ServiceRemoteImpl implements ServiceRemote {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Value("${remote.hostname}")
    private String remoteHostname;

    @Value("${remote.port}")
    private int port;

    @Value("${remote.user}")
    private String user;

    @Value("${remote.password}")
    private String password;

    @Value("${remote.route.state.current}")
    private String routeCurrentStatus;

    @Value("${remote.route.state.toggle}")
    private String routeStateToggle;


    @Override
    public EnumEventStatus getCurrentStatus() throws ServiceException {

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder()
                .nonPreemptive()
                .credentials(user, password)
                .build();

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(feature) ;

        Client client = ClientBuilder.newClient(clientConfig);

        Response response = client
                .target(remoteHostname + ":" + port)
                .path(routeCurrentStatus)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class);

        EnumEventStatus enumEventStatus = EnumEventStatus.unknown;
        if(response.getStatus() == HttpStatus.OK.value()) {
            String content = response.readEntity(String.class);

            try {
                enumEventStatus = EnumEventStatus.valueOf(content);
            } catch (IllegalArgumentException e) {
                LOG.error("Unknown event status [{}]", content);
                return null;
            }

            LOG.debug(content);
        } else {
            LOG.error("Error " + response.getStatus());
        }

        return enumEventStatus;
    }

    @Override
    public void toggleStatus() throws ServiceException {

    }
}
