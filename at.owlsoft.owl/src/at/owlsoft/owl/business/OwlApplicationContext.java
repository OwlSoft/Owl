package at.owlsoft.owl.business;

import at.owlsoft.owl.usecases.ExtensionController;
import at.owlsoft.owl.usecases.MediumExemplarSearchController;
import at.owlsoft.owl.usecases.MediumSearchController;
import at.owlsoft.owl.usecases.MessageController;
import at.owlsoft.owl.usecases.RentalController;
import at.owlsoft.owl.usecases.ReservationController;
import at.owlsoft.owl.usecases.ReturnController;

public class OwlApplicationContext
{
    private ConfigurationController _configurationController;

    public ConfigurationController getConfigurationController()
    {
        if (_configurationController == null)
        {
            _configurationController = new ConfigurationController(this);
        }
        return _configurationController;
    }

    private SearchFieldDefinitionController _clientSearchFieldDefinitionController;

    public SearchFieldDefinitionController getClientSearchFieldDefinitionController()
    {
        if (_clientSearchFieldDefinitionController == null)
        {
            _clientSearchFieldDefinitionController = new SearchFieldDefinitionController(
                    this);
        }
        return _clientSearchFieldDefinitionController;
    }

    private SearchFieldDefinitionController _serverSearchFieldDefinitionController;

    public SearchFieldDefinitionController getServerSearchFieldDefinitionController()
    {
        if (_serverSearchFieldDefinitionController == null)
        {
            _serverSearchFieldDefinitionController = new SearchFieldDefinitionController(
                    this,
                    SearchFieldDefinitionControllerConfigFiles.NoGuiMapping);
        }
        return _serverSearchFieldDefinitionController;
    }

    private SystemUserSearchController _systemUserSearchController;

    public SystemUserSearchController getSystemUserSearchController()
    {
        if (_systemUserSearchController == null)
        {
            _systemUserSearchController = new SystemUserSearchController(this);
        }
        return _systemUserSearchController;

    }

    private ExtensionController _extensionController;

    public ExtensionController getExtensionController()
    {
        if (_extensionController == null)
        {
            _extensionController = new ExtensionController(this);
        }
        return _extensionController;
    }

    private MediumExemplarSearchController _mediumExemplarSearchController;

    public MediumExemplarSearchController getMediumExemplarSearchController()
    {
        if (_mediumExemplarSearchController == null)
        {
            _mediumExemplarSearchController = new MediumExemplarSearchController(
                    this);
        }
        return _mediumExemplarSearchController;
    }

    private MediumSearchController _mediumSearchController;

    public MediumSearchController getMediumSearchController()
    {
        if (_mediumSearchController == null)
        {
            _mediumSearchController = new MediumSearchController(this);
        }
        return _mediumSearchController;
    }

    private RentalController _rentalController;

    public RentalController getRentalController()
    {
        if (_rentalController == null)
        {
            _rentalController = new RentalController(this);
        }
        return _rentalController;
    }

    private ReservationController _reservationController;

    public ReservationController getReservationController()
    {
        if (_reservationController == null)
        {
            _reservationController = new ReservationController(this);
        }
        return _reservationController;
    }

    private ReturnController _returnController;

    public ReturnController getReturnController()
    {
        if (_returnController == null)
        {
            _returnController = new ReturnController(this);
        }
        return _returnController;
    }

    private RentalSearchController _rentalSearchController;

    public RentalSearchController getRentalSearchController()
    {
        if (_rentalSearchController == null)
        {
            _rentalSearchController = new RentalSearchController(this);
        }
        return _rentalSearchController;
    }

    private AuthenticationController _authenticationController;

    public AuthenticationController getAuthenticationController()
    {
        if (_authenticationController == null)
        {
            _authenticationController = new AuthenticationController(this);
        }
        return _authenticationController;
    }

    private MessageController _messageController;

    public MessageController getMessageController()
    {
        if (_messageController == null)
        {
            _messageController = new MessageController(this);
        }
        return _messageController;
    }

    private OverdueCheckController _overdueCheckController;

    public OverdueCheckController getOverdueCheckController()
    {
        if (_overdueCheckController == null)
        {
            _overdueCheckController = new OverdueCheckController(this);
        }
        return _overdueCheckController;
    }
}
