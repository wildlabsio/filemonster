package io.wildlabs.filemonster.core;

import io.wildlabs.filemonster.core.resolver.Resolver;

import java.io.FileNotFoundException;

public class ResolvableFilesystemImpl implements ResolvableFilesystem {
    private final Filesystem filesystem;
    private final Resolver resolver;

    public ResolvableFilesystemImpl(Filesystem filesystem, Resolver resolver) {
        this.filesystem = filesystem;
        this.resolver = resolver;
    }

    @Override
    public String resolve(String key) {
        return resolver.resolve(key);
    }

    @Override
    public boolean has(String key) {
        return filesystem.has(key);
    }

    @Override
    public void rename(String sourceKey, String targetKey) throws FileNotFoundException {
        filesystem.rename(sourceKey, targetKey);
    }

    @Override
    public byte[] read(String key) throws FileNotFoundException {
        return filesystem.read(key);
    }

    @Override
    public void write(String key, byte[] content) {
        filesystem.write(key, content);
    }

    @Override
    public void delete(String key) throws FileNotFoundException {
        filesystem.delete(key);
    }
}
