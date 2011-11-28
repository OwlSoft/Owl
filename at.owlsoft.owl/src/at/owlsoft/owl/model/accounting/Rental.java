/*
 * This file is part of OwlSoft Owl.
 *
 *  OwlSoft Owl is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  alphaTab is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with alphaTab.  If not, see <http://www.gnu.org/licenses/>.
 */
public package at.owlsoft.owl.model.accounting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Rental extends Activity implements IRental
{
    private static final long     serialVersionUID = 7711071715379325765L;
    private Date                  _endDate;
    private List<FilingExtension> _filingExtensions;

    public Rental()
    {
        _filingExtensions = new ArrayList<FilingExtension>();
    }

    public Rental(Date endDate)
    {
        super();
        _endDate = endDate;
        _filingExtensions = new ArrayList<FilingExtension>();
    }

    @Override
    public Date getEndDate()
    {
        return _endDate;
    }

    public void setEndDate(Date endDate)
    {
        _endDate = endDate;
    }

    @Override
    public int getFilingExtensionCount()
    {
        return _filingExtensions.size();
    }

    @Override
    public FilingExtension getFilingExtension(int index)
    {
        return _filingExtensions.get(index);
    }

    @Override
    public List<FilingExtension> getFilingExtensions()
    {
        return Collections.unmodifiableList(_filingExtensions);
    }

    public void clearFilingExtensions()
    {
        _filingExtensions.clear();
    }

    public void addFilingExtension(FilingExtension filingExtension)
    {
        filingExtension.setRental(this);
        _filingExtensions.add(filingExtension);
    }

    public void removeFilingExtension(FilingExtension filingExtension)
    {
        _filingExtensions.remove(filingExtension);
    }

    public void updateEndDate(int days)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(getStartDate());
        c.add(Calendar.DATE, days);
        setEndDate(c.getTime());
    }
}
