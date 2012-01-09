package at.owlsoft.owl.communication.ejb;

import java.util.List;
import java.util.UUID;

import javax.ejb.Remote;

import at.owlsoft.owl.model.ISearchField;
import at.owlsoft.owl.model.ISearchFieldCategory;
import at.owlsoft.owl.model.media.IMedium;

@Remote
public interface SearchApiRemote extends IApiBase
{
    public static final String JNDI_NAME = "owl/SearchApiRemote";

    List<ISearchFieldCategory> getSearchFieldCategories();

    ISearchField addNewSearchField(UUID uniqueId);

    void removeSearchField(UUID uniqueId);

    void setSearchFieldData(UUID uniqueId, String key, String value);

    List<IMedium> search();
}
