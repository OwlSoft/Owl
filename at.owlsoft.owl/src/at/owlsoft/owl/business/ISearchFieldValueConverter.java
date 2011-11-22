package at.owlsoft.owl.business;

import at.owlsoft.owl.model.ISearchField;

public interface ISearchFieldValueConverter
{
    <T> T convert(ISearchField searchField);
}
