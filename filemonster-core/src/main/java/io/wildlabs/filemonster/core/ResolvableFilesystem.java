package io.wildlabs.filemonster.core;

public interface ResolvableFilesystem extends Filesystem {
    String resolve(String key);
}
