package at.owlsoft.owlet.data;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import at.owlsoft.owlet.NotImplementedException;

public class Binding
{
    private static final Logger    logger                   = Logger.getLogger(Binding.class);

    private static final String    ADD_CHANGE_EVENT_NAME    = "addPropertyChangeListener";
    private static final String    REMOVE_CHANGE_EVENT_NAME = "addPropertyChangeListener";

    // source / dataContext
    private DataContextManager     _dataContextManager;
    private String                 _sourcePropertyName;

    private PropertyChangeListener _sourceListener;

    private Object                 _dataContext;
    private Method                 _addSourceListener;
    private Method                 _removeSourceListener;

    private Method                 _getSourceProperty;
    private Method                 _setSourceProperty;

    // target / ui
    private Component              _target;
    private String                 _targetPropertyName;

    private PropertyChangeListener _targetListener;

    private Method                 _getTargetProperty;
    private Method                 _setTargetProperty;

    private BindingMode            _mode;

    // general
    private BindingState           _state;
    private boolean                _ignoreUpdateEvents;
    private IValueConverter        _converter;

    private Class<?>               _sourceType;
    private Class<?>               _targetType;

    //
    // getter/setter
    //

    public String getSourcePropertyName()
    {
        return _sourcePropertyName;
    }

    public void setSourcePropertyName(String sourcePropertyName)
    {
        _sourcePropertyName = sourcePropertyName;
    }

    public String getTargetPropertyName()
    {
        return _targetPropertyName;
    }

    public void setTargetPropertyName(String targetPropertyName)
    {
        _targetPropertyName = targetPropertyName;
    }

    public Component getTarget()
    {
        return _target;
    }

    public void setTarget(Component target)
    {
        _target = target;
    }

    public BindingMode getMode()
    {
        return _mode;
    }

    public void setMode(BindingMode mode)
    {
        _mode = mode;
    }

    public BindingState getState()
    {
        return _state;
    }

    public IValueConverter getConverter()
    {
        return _converter;
    }

    public void setConverter(IValueConverter converter)
    {
        _converter = converter;
    }

    public Binding(DataContextManager manager, Class<?> sourceType,
            Class<?> targetType)
    {
        this(manager, BindingMode.TwoWay, null, null, null, sourceType,
                targetType);
    }

    public Binding(DataContextManager manager, BindingMode mode,
            Component target, String sourcePropertyName,
            String targetPropertyName, Class<?> sourceType, Class<?> targetType)
    {
        // properties
        _mode = mode;
        _target = target;
        _sourcePropertyName = sourcePropertyName;
        _targetPropertyName = targetPropertyName;
        _sourceType = sourceType;
        _targetType = targetType;

        _state = BindingState.Unbound;
        _dataContextManager = manager;
        _dataContextManager
                .addDataContextChangeListener(new IDataContextChangedListener()
                {
                    @Override
                    public void dataContextChange(DataContextChangeEvent evt)
                    {
                        if (isParentOrTargetItself(evt.getSource()))
                        {
                            rebind();
                        }
                    }
                });

        _sourceListener = new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
                if (_sourcePropertyName.equals(evt.getPropertyName()))
                {
                    updateTarget();
                }
            }
        };

        _targetListener = new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
                if (_targetPropertyName.equals(evt.getPropertyName()))
                {
                    updateSource();
                }
            }
        };
    }

    private boolean isParentOrTargetItself(Component source)
    {
        Component current = source;
        while (current != null)
        {
            if (_target.equals(current))
            {
                return true;
            }
            current = current.getParent();
        }
        return false;
    }

    private Method setter(Object obj, String propertyName, Class<?> t)
            throws SecurityException, NoSuchMethodException
    {
        Method setter = obj.getClass().getMethod(setterName(propertyName), t);
        return setter;
    }

    private Method getter(Object obj, String propertyName, Class<?> t)
            throws SecurityException, NoSuchMethodException
    {
        Method getter = obj.getClass().getMethod(getterName(propertyName));
        if (getter.getReturnType() == null || t.equals(getter))
        {
            throw new IllegalArgumentException("Invalid getter signature");
        }
        return getter;
    }

    private Method remover(Object obj) throws SecurityException,
            NoSuchMethodException
    {
        Method remover = obj.getClass().getMethod(REMOVE_CHANGE_EVENT_NAME,
                PropertyChangeListener.class);
        return remover;
    }

    private Method adder(Object obj) throws SecurityException,
            NoSuchMethodException
    {
        Method adder = obj.getClass().getMethod(ADD_CHANGE_EVENT_NAME,
                PropertyChangeListener.class);
        return adder;
    }

    private String getterName(String targetPropertyName)
    {
        return "get" + targetPropertyName.substring(0, 1).toUpperCase()
                + targetPropertyName.substring(1);
    }

    private String setterName(String targetPropertyName)
    {
        return "set" + targetPropertyName.substring(0, 1).toUpperCase()
                + targetPropertyName.substring(1);
    }

    public void bind()
    {
        if (_state == BindingState.Bound)
        {
            return;
        }
        rebind();
    }

    public void rebind()
    {
        try
        {
            _ignoreUpdateEvents = true;
            unbind();

            // load property methods
            try
            {
                if (_mode == BindingMode.TwoWay
                        || _mode == BindingMode.OneWayToSource)
                {
                    _getTargetProperty = getter(_target, _targetPropertyName,
                            _targetType);
                }
                if (_mode == BindingMode.TwoWay || _mode == BindingMode.OneWay)
                {
                    _setTargetProperty = setter(_target, _targetPropertyName,
                            _targetType);
                }
            }
            catch (Exception e)
            {
                logger.error(
                        "Could not load property methods of binding target", e);
                unbind();
                _state = BindingState.TargetPropertyFault;
                return;
            }

            // try to get new context
            _dataContext = _dataContextManager.lookup(_target);
            if (_dataContext == null)
            {
                logger.info("No datacontext found, no binding available");
                _getSourceProperty = null;
                _setSourceProperty = null;
                unbind();
                return;
            }

            // hook into change event of ui
            if (_mode == BindingMode.OneWayToSource
                    || _mode == BindingMode.TwoWay)
            {
                _target.addPropertyChangeListener(_targetListener);
                logger.trace("Hooked into change event of target");
            }

            // load property methods
            try
            {
                if (_mode == BindingMode.TwoWay || _mode == BindingMode.OneWay)
                {
                    _getSourceProperty = getter(_dataContext,
                            _sourcePropertyName, _sourceType);
                    _addSourceListener = adder(_dataContext);
                    _removeSourceListener = remover(_dataContext);
                }

                if (_mode == BindingMode.TwoWay
                        || _mode == BindingMode.OneWayToSource)
                {
                    _setSourceProperty = setter(_dataContext,
                            _sourcePropertyName, _sourceType);
                }
                logger.trace("Loaded property accessors from dataContext");
            }
            catch (Exception e)
            {
                logger.info("Could not load methods from datacontext", e);
                _getSourceProperty = null;
                _setSourceProperty = null;
                _addSourceListener = null;
                _removeSourceListener = null;
                unbind();
                _state = BindingState.SourcePropertyFault;
                return;
            }

            // hook into change event of context
            if (_mode == BindingMode.OneWay || _mode == BindingMode.TwoWay)
            {
                try
                {
                    _addSourceListener.invoke(_dataContext, _sourceListener);
                    logger.trace("Hooked into change event of dataContext");
                }
                catch (Exception e)
                {
                    logger.info("Could add binding to datacontext", e);
                    unbind();
                    return;
                }
            }

            _state = BindingState.Bound;
        }
        finally
        {
            _ignoreUpdateEvents = false;
        }
    }

    public void unbind()
    {
        logger.trace("Unbinding " + toString());
        if (_dataContext != null && _removeSourceListener != null)
        {
            // unregister from old dataContext
            try
            {
                logger.trace("unregister listener from old dataContext");
                _removeSourceListener.invoke(_dataContext, _sourceListener);
            }
            catch (Exception e)
            {
                logger.error(
                        "Could not unregister listener from old dataContext "
                                + toString(), e);
            }
        }

        _target.removePropertyChangeListener(_targetListener);

        _getTargetProperty = null;
        _setTargetProperty = null;

        _getSourceProperty = null;
        _setSourceProperty = null;
        _addSourceListener = null;
        _removeSourceListener = null;
        _state = BindingState.Unbound;

        logger.trace("Unbound " + toString());

    }

    private void updateTarget()
    {
        if (_ignoreUpdateEvents || _getSourceProperty == null
                || _setTargetProperty == null || _state != BindingState.Bound)
        {
            return;
        }

        try
        {
            _ignoreUpdateEvents = true;
            Object sourceValue = _getSourceProperty.invoke(_dataContext);
            if (_converter != null)
            {
                Class<?> targetType = _setTargetProperty.getParameterTypes()[0];
                sourceValue = _converter.convert(sourceValue, targetType);
            }

            _setTargetProperty.invoke(_target, sourceValue);
        }
        catch (NotImplementedException e)
        {
            logger.warn("Could not update target, value converter is not implemented");
        }
        catch (Exception e)
        {
            logger.error("Could not update target", e);
        }
        finally
        {
            _ignoreUpdateEvents = false;
        }
    }

    private void updateSource()
    {
        if (_ignoreUpdateEvents || _getTargetProperty == null
                || _setSourceProperty == null || _state != BindingState.Bound)
        {
            return;
        }

        try
        {
            _ignoreUpdateEvents = true;
            Object targetValue = _getTargetProperty.invoke(_target);
            if (_converter != null)
            {
                Class<?> sourceType = _setSourceProperty.getParameterTypes()[0];
                targetValue = _converter.convertBack(targetValue, sourceType);
            }

            _setSourceProperty.invoke(_dataContext, targetValue);
        }
        catch (NotImplementedException e)
        {
            logger.warn("Could not update target, value converter is not implemented");
        }
        catch (Exception e)
        {
            logger.error("Could not update target", e);
        }
        finally
        {
            _ignoreUpdateEvents = false;
        }
    }
}
