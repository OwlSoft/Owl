package at.owlsoft.owl.usecases;

/**
 * Stores ValidationMessag with ValidationStatus.
 */
public class ValidationMessage
{

    private ValidationMessageStatus _status;
    private String                  _message;

    public ValidationMessage(String message, ValidationMessageStatus status)
    {
        this._message = message;
        this._status = status;
    }

    public ValidationMessageStatus getStatus()
    {
        return _status;
    }

    public String getMessage()
    {
        return _message;
    }

}
