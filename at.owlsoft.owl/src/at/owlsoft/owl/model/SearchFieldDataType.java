package at.owlsoft.owl.model;

public enum SearchFieldDataType
{
    DateTime, String, Integer, Undefinied;

    public static SearchFieldDataType Convert(String toConvert)
    {
        toConvert = toConvert.toLowerCase();
        if (toConvert.equals("datetime"))
        {
            return DateTime;

        }
        else if (toConvert.equals("string"))
        {
            return String;
        }
        else if (toConvert.equals("integer"))
        {
            return Integer;
        }

        return Undefinied;

    }
}
