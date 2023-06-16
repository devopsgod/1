package by.vstu.utils;

import javax.servlet.http.HttpServletRequest;

public final class Requests {

    private Requests() {
    }

    public static String getRemoteAddr(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    public static String getRequestUri(HttpServletRequest request) {
        if (request.getScheme() != null) {
            return request.getScheme()
                    + "://"
                    + request.getServerName()
                    + ":" + request.getServerPort() + request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        } else {
            return "";
        }
    }

    public static String getRequestMethod(HttpServletRequest request) {
        return request.getMethod();
    }
}
