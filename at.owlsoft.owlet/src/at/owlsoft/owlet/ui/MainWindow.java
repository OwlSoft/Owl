package at.owlsoft.owlet.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import at.owlsoft.owlet.ui.rental.CreateRentalView;
import at.owlsoft.owlet.ui.reservation.CreateReservationView;
import at.owlsoft.owlet.ui.search.SearchMediumView;

public class MainWindow extends JFrame
{
    private static final String    TITLE_TEMPLATE   = "Owlsoft Owlet - %s";
    private static final long      serialVersionUID = 3947781234498915049L;

    private Map<String, OwletView> _accessibleViews;
    private JPanel                 _viewBox;
    private OwletView              _currentView;

    public MainWindow()
    {
        _accessibleViews = new HashMap<String, OwletView>();

        // NOTE: As soon we have an authentication system,
        // we need to check for accessible actions
        registerView(new CreateRentalView());
        registerView(new CreateReservationView());
        registerView(new SearchMediumView());

        initializeComponents();
    }

    private void registerView(OwletView view)
    {
        _accessibleViews.put(view.getKey(), view);
    }

    private void initializeComponents()
    {
        ActionListener changeViewAction = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                openView(e.getActionCommand());
            }
        };

        //
        // fileMenu
        //
        JMenu fileMenu = new JMenu("File");

        for (OwletView owletView : _accessibleViews.values())
        {
            JMenuItem item = new JMenuItem(owletView.getTitle());
            item.setActionCommand(owletView.getKey());
            item.addActionListener(changeViewAction);
            fileMenu.add(item);
        }

        //
        // mainMenuBar
        //
        JMenuBar mainMenu = new JMenuBar();
        mainMenu.add(fileMenu);

        //
        // _viewBox
        //
        _viewBox = new JPanel();
        //
        // MainWindow
        //
        setLayout(new BorderLayout());
        add(mainMenu, BorderLayout.NORTH);
        add(_viewBox, BorderLayout.CENTER);
        setMinimumSize(new Dimension(500, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultTitle();
    }

    private void openView(String viewId)
    {
        if (!_accessibleViews.containsKey(viewId))
        {
            return;
        }

        if (_currentView != null)
        {
            if (!_currentView.onViewClosing())
            {
                return;
            }
        }

        _viewBox.removeAll();

        if (_currentView != null)
        {
            _currentView.onViewClosed();
        }

        OwletView view = _accessibleViews.get(viewId);
        if (view.onViewOpening())
        {
            _viewBox.add(_accessibleViews.get(viewId));
            _viewBox.validate();
            _viewBox.repaint();
            view.onViewOpened();
            setTitle(String.format(TITLE_TEMPLATE, view.getTitle()));
        }
        else
        {
            setDefaultTitle();
        }
    }

    private void setDefaultTitle()
    {
        setTitle(String.format(TITLE_TEMPLATE, "Dashboard"));
    }
}
