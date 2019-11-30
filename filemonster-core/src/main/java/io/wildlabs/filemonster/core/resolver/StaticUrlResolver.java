package io.wildlabs.filemonster.core.resolver;

import io.wildlabs.filemonster.core.exception.ResolveException;

public class StaticUrlResolver implements Resolver {
    private final String prefix;

    public StaticUrlResolver(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String resolve(String key) throws ResolveException {
        try {
            return prefix + "/" + key.trim() + "/";
        } catch (Exception e) {
            throw new ResolveException(e);
        }
    }
}
