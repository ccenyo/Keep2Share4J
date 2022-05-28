package org.github.commands;

import org.github.views.DefaultView;

import java.util.HashMap;
import java.util.Map;

public abstract class PostCommand<T extends DefaultView> extends Command<T>{

    @Override
    protected Method getMethod() {
        return Method.POST;
    }

    @Override
    protected Map<String, Object> getParamsInBody() {
        return  new HashMap<>();
    }

    @Override
    protected Map<String, Object> getBodyInJson() {
        return null;
    }

    @Override
    protected Map<String, Object> getParamsInLink() {
        return new HashMap<>();
    }

    @Override
    protected Map<String, Object> getHeaders() {
        return new HashMap<>();
    }

}
