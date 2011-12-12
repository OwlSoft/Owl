package at.owlsoft.owl.model.messaging;

import java.io.Serializable;

public interface IMessageEventStrings extends Serializable
{
    public static String NEW_RENTAL_OVERDUE       = "NEW_RENTAL_OVERDUE";
    public static String NEW_EXTERNAL_RENTAL      = "NEW_EXTERNAL_RENTAL";
    public static String RESERVED_MEDIUM_RETURNED = "RESERVED_MEDIUM_RETURNED";

}
