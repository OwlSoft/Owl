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
}
