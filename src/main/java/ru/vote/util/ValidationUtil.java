package ru.vote.util;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.NonNull;
import ru.vote.model.AbstractModel;
import ru.vote.util.exeption.NotFoundException;

public class ValidationUtil {

    public static <T> T checkNotFoundWithId(T object, int id) {
        if (object == null) {
            throw new NotFoundException("Not found entity with id = " + id);
        }
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        if (!found) {
            throw new NotFoundException("Not found entity with id = " + id);
        }
    }

    public static <T> T checkNotFound(T object) {
        if(object == null) {
            throw new NotFoundException("Not found entity");
        }
        return object;
    }

    public static void checkNew(AbstractModel model) {
        if (!model.isNew()) {
            throw new IllegalArgumentException(model + "must be new");
        }
    }

    //http://stackoverflow.com/a/32728226/548473
    public static void assureIdConsistent(AbstractModel model, int id) {
        if (model.isNew()) {
            model.setId(id);
        } else if (model.getId() != id) {
            throw new IllegalArgumentException(model + " must be with id=" + id);
        }
    }

    //  https://stackoverflow.com/a/65442410/548473
    @NonNull
    public static Throwable getRootCause(@NonNull Throwable t) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(t);
        return rootCause != null ? rootCause : t;
    }

}
