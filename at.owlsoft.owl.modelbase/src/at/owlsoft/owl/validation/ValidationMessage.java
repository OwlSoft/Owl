package at.owlsoft.owl.validation;

import java.io.Serializable;

/**
 * Stores ValidationMessag with ValidationStatus.
 */
public class ValidationMessage implements Serializable
{
    private static final long       serialVersionUID = -5801870197119319414L;
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
