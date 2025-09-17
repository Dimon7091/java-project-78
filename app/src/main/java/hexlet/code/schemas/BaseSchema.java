package hexlet.code.shemas;

abstract class BaseSchema<T> {
    public abstract boolean isValid(T value);
}
