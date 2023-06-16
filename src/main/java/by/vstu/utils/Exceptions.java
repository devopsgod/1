package by.vstu.utils;

import com.google.common.base.Throwables;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class Exceptions {

    private Exceptions() {
    }

    public static String getRootMessage(Throwable t) {
        Throwable r = Throwables.getRootCause(t);
        if ((r != null) && (r != t)) {
            return getMessage(t) + " / " + getMessage(r);
        } else {
            return getMessage(t);
        }
    }

    private static String getMessage(Throwable t) {
        String message = t.getMessage();

        if (t instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException e = (MethodArgumentTypeMismatchException) t;
            message += "(class: " + e.getParameter().getContainingClass().getSimpleName() + ", parameter: " + e.getParameter() + ", name: " + e.getName() + ")";
        }

        return message;
    }

    public static String getClass(Throwable t) {
        return t.getClass().getCanonicalName();
    }

    public static String getStackTrace(Throwable t) {
        StringWriter errors = new StringWriter();
        t.printStackTrace(new PrintWriter(errors));
        return errors.toString().replaceAll("\n", "<br>");
    }
}
