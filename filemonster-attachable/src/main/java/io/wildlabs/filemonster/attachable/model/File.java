package io.wildlabs.filemonster.attachable.model;

public interface File {
    Long getReferenceId();

    void setReferenceId(Long referenceId);

    String getClassIdentifier();

    void setClassIdentifier(String classIdentifier);

    String getFileKey();

    void setFileKey(String fileKey);

    String getType();

    void setType(String type);
}
