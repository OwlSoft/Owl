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
public package at.owlsoft.owl.model.media;

import java.util.Date;

public class Book extends Medium implements IBook
{
    private static final long serialVersionUID = -4334382438346892118L;

    private String            _author;
    private String            _title;
    private String            _abstract;
    private int               _pages;
    private String            _isbn10;
    private String            _isbn13;

    public Book()
    {
        super();
    }

    public Book(int mediumID, String publisher, String name, Date entryDate,
            Date publishedDate, String author, String title, String abstract1,
            int pages, String isbn10, String isbn13)
    {
        super(mediumID, publisher, name, entryDate, publishedDate);
        _author = author;
        _title = title;
        _abstract = abstract1;
        _pages = pages;
        _isbn10 = isbn10;
        _isbn13 = isbn13;
    }

    @Override
    public String getAuthor()
    {
        return _author;
    }

    public void setAuthor(String author)
    {
        _author = author;
    }

    @Override
    public String getTitle()
    {
        return _title;
    }

    public void setTitle(String title)
    {
        _title = title;
    }

    @Override
    public String getAbstract()
    {
        return _abstract;
    }

    public void setAbstract(String abstract1)
    {
        _abstract = abstract1;
    }

    @Override
    public int getPages()
    {
        return _pages;
    }

    public void setPages(int pages)
    {
        _pages = pages;
    }

    @Override
    public String getIsbn10()
    {
        return _isbn10;
    }

    public void setIsbn10(String isbn10)
    {
        _isbn10 = isbn10;
    }

    @Override
    public String getIsbn13()
    {
        return _isbn13;
    }

    public void setIsbn13(String isbn13)
    {
        _isbn13 = isbn13;
    }

}
