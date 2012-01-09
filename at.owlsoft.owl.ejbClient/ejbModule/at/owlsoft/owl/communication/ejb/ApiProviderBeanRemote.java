package at.owlsoft.owl.communication.ejb;

import javax.ejb.Remote;

@Remote
public interface ApiProviderBeanRemote
{
    RentalApiRemote createRentalApi();

    ReservationApiRemote createReservationApi();

    SearchApiRemote createSearchApi();

    SystemUserApiRemote createSystemUserApi();

    ConfigurationApiRemote createConfigurationApi();

    AuthenticationApiRemote createAuthenticationApi();

    MessagingApiRemote createMessagingApi();
}
