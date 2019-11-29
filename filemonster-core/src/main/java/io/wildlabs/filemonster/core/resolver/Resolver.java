package io.wildlabs.filemonster.core.resolver;

import io.wildlabs.filemonster.core.exception.ResolveException;

public interface Resolver {
    String resolve(String key) throws ResolveException;
}
