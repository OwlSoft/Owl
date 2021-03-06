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

public class Tag implements ITag
{
    private static final long serialVersionUID = 6248847425658132480L;

    private String            value;
    private TagType           tagType;
    private Medium            medium;

    public Tag()
    {
    }

    public Tag(String value, TagType tagType, Medium medium)
    {
        super();
        this.value = value;
        this.tagType = tagType;
        this.medium = medium;
    }

    @Override
    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    @Override
    public TagType getTagType()
    {
        return tagType;
    }

    public void setTagType(TagType tagType)
    {
        this.tagType = tagType;
    }

    @Override
    public Medium getMedium()
    {
        return medium;
    }

    public void setMedium(Medium medium)
    {
        this.medium = medium;
    }

}
