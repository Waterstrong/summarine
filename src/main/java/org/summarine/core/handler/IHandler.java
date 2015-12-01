package org.summarine.core.handler;

import java.util.Map;

public interface IHandler {
    Map<String, Object> convert(String resource);
}
