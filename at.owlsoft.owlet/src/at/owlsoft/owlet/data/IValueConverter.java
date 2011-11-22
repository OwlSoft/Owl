package at.owlsoft.owlet.data;

public interface IValueConverter
{
    Object convert(Object value, Class<?> targetType) throws Exception;

    Object convertBack(Object value, Class<?> targetType) throws Exception;
}
