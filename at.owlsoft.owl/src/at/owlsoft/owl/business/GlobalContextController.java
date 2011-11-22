package at.owlsoft.owl.business;

public class GlobalContextController
{
    private static ISearchFieldValueConverter _searchFieldValueConverter;

    public static ISearchFieldValueConverter getSearchFieldValueConverter()
    {
        if (_searchFieldValueConverter == null)
        {
            _searchFieldValueConverter = new SearchFieldDefinitionController();
        }
        return _searchFieldValueConverter;
    }

    private static SystemUserSearchController _systemUserSearchController;

    public static SystemUserSearchController getSystemUserSearchController()
    {
        if (_systemUserSearchController == null)
        {
            _systemUserSearchController = new SystemUserSearchController();
        }
        return _systemUserSearchController;

    }

}
