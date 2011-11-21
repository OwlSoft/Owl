package at.owlsoft.owlet.ui.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A layoutmanager providing a grid with fixed columns and rows. If components
 * are smaller than the cell they will be positioned relative as specified by a
 * {@link CustomGridPosition} constraint.
 */
public class CustomGridLayout implements LayoutManager2
{
    /**
     * Where to position controls within a cell
     * 
     * @author Daniel Kuschny (dku2375)
     */
    public static enum CustomGridPosition
    {
        TopLeft, TopCenter, TopRight,

        MiddleLeft, MiddleCenter, MiddleRight,

        BottomLeft, BottomCenter
    }

    private Map<Component, Object> _constraints;
    private List<SizeDefinition>   _rowDefinitions;
    private List<SizeDefinition>   _colDefinitions;

    private int                    _hgap;
    private int                    _vgap;

    /**
     * Initializes a new instance of the {@link CustomGridLayout} class.
     */
    public CustomGridLayout()
    {
        _constraints = new HashMap<Component, Object>();
        _rowDefinitions = new ArrayList<CustomGridLayout.SizeDefinition>();
        _colDefinitions = new ArrayList<CustomGridLayout.SizeDefinition>();
    }

    /**
     * Initializes a new instance of the {@link CustomGridLayout} class.
     */
    public CustomGridLayout(int[] cols, int[] rows)
    {
        this();
        for (int row : rows)
        {
            addRowDefinition(row);
        }
        for (int col : cols)
        {
            addColDefinition(col);
        }
    }

    /**
     * Initializes a new instance of the {@link CustomGridLayout} class.
     */
    public CustomGridLayout(int[] cols, int[] rows, int hgap, int vgap)
    {
        this(cols, rows);
        _hgap = hgap;
        _vgap = vgap;
    }

    /**
     * Gets a list of all row definitions.
     * 
     * @return a list of all row definitions.
     */
    public List<SizeDefinition> getRowDefinitions()
    {
        return _rowDefinitions;
    }

    /**
     * Adds a new row definition to the list
     * 
     * @param size the row defintion
     */
    public void addRowDefinition(SizeDefinition size)
    {
        _rowDefinitions.add(size);
    }

    /**
     * Adds a new row definition to the list
     * 
     * @param size the size of the row
     */
    public void addRowDefinition(int size)
    {
        _rowDefinitions.add(new SizeDefinition(size));
    }

    /**
     * Gets a list of all column definitions.
     * 
     * @return a list of all column definitions.
     */
    public List<SizeDefinition> getColDefinitions()
    {
        return _colDefinitions;
    }

    /**
     * Adds a new column definition to the list
     * 
     * @param size the column defintion
     */
    public void addColDefinition(SizeDefinition size)
    {
        _colDefinitions.add(size);
    }

    /**
     * Adds a new column definition to the list
     * 
     * @param size the size of the new column defintion
     */
    public void addColDefinition(int size)
    {
        _colDefinitions.add(new SizeDefinition(size));
    }

    /**
     * A container for a size definition.
     * 
     * @author Daniel Kuschny (dku2375)
     */
    public static class SizeDefinition
    {
        private int _size;

        /**
         * Gets the size.
         * 
         * @return the size
         */
        public int getSize()
        {
            return _size;
        }

        /**
         * Sets the size.
         * 
         * @param size the size to set
         */
        public void setSize(int size)
        {
            _size = size;
        }

        /**
         * Initializes a new instance of the {@link SizeDefinition} class.
         * 
         * @param size
         */
        public SizeDefinition(int size)
        {
            super();
            _size = size;
        }
    }

    /**
     * @see java.awt.LayoutManager#addLayoutComponent(java.lang.String,
     *      java.awt.Component)
     */
    @Override
    public void addLayoutComponent(String name, Component comp)
    {
    }

    /**
     * @see java.awt.LayoutManager#removeLayoutComponent(java.awt.Component)
     */
    @Override
    public void removeLayoutComponent(Component comp)
    {
        _constraints.remove(comp);
    }

    /**
     * @see java.awt.LayoutManager#preferredLayoutSize(java.awt.Container)
     */
    @Override
    public Dimension preferredLayoutSize(Container parent)
    {
        synchronized (parent.getTreeLock())
        {
            Insets insets = parent.getInsets();

            int w = 0;
            int h = 0;

            for (SizeDefinition col : _colDefinitions)
            {
                w += col.getSize() + _hgap;
            }
            w -= _hgap;

            for (SizeDefinition row : _rowDefinitions)
            {
                h += row.getSize() + _vgap;
            }
            h -= _vgap;

            return new Dimension(insets.left + insets.right + w, insets.top
                    + insets.bottom + h);
        }
    }

    /**
     * @see java.awt.LayoutManager#minimumLayoutSize(java.awt.Container)
     */
    @Override
    public Dimension minimumLayoutSize(Container parent)
    {
        return preferredLayoutSize(parent);
    }

    /**
     * @see java.awt.LayoutManager#layoutContainer(java.awt.Container)
     */
    @Override
    public void layoutContainer(Container parent)
    {
        synchronized (parent.getTreeLock())
        {
            int ncomponents = parent.getComponentCount();
            int nrows = _rowDefinitions.size();
            int ncols = _colDefinitions.size();

            if (ncomponents == 0)
            {
                return;
            }

            int y = 0;

            // iterate over grid
            for (int row = 0; row < nrows; row++)
            {
                // start row on x = 0
                SizeDefinition rowDefinition = _rowDefinitions.get(row);
                int x = 0;
                for (int col = 0; col < ncols; col++)
                {
                    SizeDefinition colDefinition = _colDefinitions.get(col);
                    // have a component?
                    int index = (row * ncols) + col;
                    if (index < ncomponents)
                    {
                        Component c = parent.getComponent(index);
                        Dimension preferedSize = c.getPreferredSize();
                        Object constraint = _constraints.containsKey(c) ? _constraints
                                .get(c) : null;
                        if (constraint == null)
                        {
                            constraint = CustomGridPosition.TopLeft;
                        }

                        // use prefered size if possible, make control fit to
                        // cell
                        int w = preferedSize.width > colDefinition.getSize() ? colDefinition
                                .getSize() : preferedSize.width;
                        int h = preferedSize.height > rowDefinition.getSize() ? rowDefinition
                                .getSize() : preferedSize.height;

                        // calculate real position
                        int realX = x;
                        int realY = y;
                        if (preferedSize.width < colDefinition.getSize()
                                || preferedSize.height < rowDefinition
                                        .getSize())
                        {
                            // calculate y
                            if (constraint == CustomGridPosition.TopLeft
                                    || constraint == CustomGridPosition.TopCenter
                                    || constraint == CustomGridPosition.TopRight)
                            {
                                realY = y;
                            }
                            else if (constraint == CustomGridPosition.MiddleLeft
                                    || constraint == CustomGridPosition.MiddleCenter
                                    || constraint == CustomGridPosition.MiddleRight)
                            {
                                realY = y + ((rowDefinition.getSize() - h) / 2);
                            }
                            else
                            {
                                realY = y + rowDefinition.getSize() - h;
                            }

                            // calculate x
                            if (constraint == CustomGridPosition.TopLeft
                                    || constraint == CustomGridPosition.MiddleLeft
                                    || constraint == CustomGridPosition.BottomLeft)
                            {
                                realX = x;
                            }
                            else if (constraint == CustomGridPosition.TopCenter
                                    || constraint == CustomGridPosition.MiddleCenter
                                    || constraint == CustomGridPosition.BottomCenter)
                            {
                                realX = x + ((colDefinition.getSize() - w) / 2);
                            }
                            else
                            {
                                realX = x + colDefinition.getSize() - w;
                            }

                            c.setBounds(realX, realY, w, h);
                        }
                    }
                    // move along x
                    x += _colDefinitions.get(col).getSize() + _hgap;
                }

                // move along y
                y += rowDefinition.getSize() + _vgap;
            }
        }
    }

    /**
     * @see java.awt.LayoutManager2#addLayoutComponent(java.awt.Component,
     *      java.lang.Object)
     */
    @Override
    public void addLayoutComponent(Component comp, Object constraints)
    {
        _constraints.put(comp, constraints);
    }

    /**
     * @see java.awt.LayoutManager2#maximumLayoutSize(java.awt.Container)
     */
    @Override
    public Dimension maximumLayoutSize(Container target)
    {
        return null;
    }

    /**
     * @see java.awt.LayoutManager2#getLayoutAlignmentX(java.awt.Container)
     */
    @Override
    public float getLayoutAlignmentX(Container target)
    {
        return 0;
    }

    /**
     * @see java.awt.LayoutManager2#getLayoutAlignmentY(java.awt.Container)
     */
    @Override
    public float getLayoutAlignmentY(Container target)
    {
        return 0;
    }

    /**
     * @see java.awt.LayoutManager2#invalidateLayout(java.awt.Container)
     */
    @Override
    public void invalidateLayout(Container target)
    {
    }
}