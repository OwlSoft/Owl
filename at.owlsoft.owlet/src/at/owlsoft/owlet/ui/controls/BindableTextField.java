package at.owlsoft.owlet.ui.controls;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class BindableTextField extends JTextField
{
    private static final long serialVersionUID = -1332444392898116507L;

    public BindableTextField()
    {
        super();
        addChangeListener();
    }

    public BindableTextField(Document doc, String text, int columns)
    {
        super(doc, text, columns);
        addChangeListener();
    }

    public BindableTextField(int columns)
    {
        super(columns);
        addChangeListener();
    }

    public BindableTextField(String text, int columns)
    {
        super(text, columns);
        addChangeListener();
    }

    public BindableTextField(String text)
    {
        super(text);
        addChangeListener();
    }

    private void addChangeListener()
    {
        getDocument().addDocumentListener(new DocumentListener()
        {

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                fireChange();

            }

            private void fireChange()
            {
                String s = getText() == null || getText().isEmpty() ? " " : "";
                firePropertyChange("text", s, getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                fireChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {
                fireChange();
            }
        });
    }
}
