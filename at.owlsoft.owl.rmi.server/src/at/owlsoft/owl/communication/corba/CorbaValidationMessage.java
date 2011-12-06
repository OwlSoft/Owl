package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;

import at.owlsoft.owl.corbamodel.validation.CorbaValidationMessageStatus;
import at.owlsoft.owl.corbamodel.validation.ICorbaValidationMessagePOA;
import at.owlsoft.owl.validation.ValidationMessage;

public class CorbaValidationMessage extends ICorbaValidationMessagePOA
{
    private POA               _rootPOA;
    private ValidationMessage _message;

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;
    }

    public void setValidationMessage(ValidationMessage validationMessage)
    {
        _message = validationMessage;
    }

    @Override
    public CorbaValidationMessageStatus getStatus()
    {
        switch (_message.getStatus())
        {
            case Error:
                return CorbaValidationMessageStatus.Error;
            case Warning:
                return CorbaValidationMessageStatus.Warning;
            default:
                throw new RuntimeException("ValidationMessageStatus not known.");
        }
    }

    @Override
    public String getMessage()
    {
        return _message.getMessage();
    }

}
