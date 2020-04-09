package io.wildlabs.filemonster.attachable.service;

import io.wildlabs.filemonster.attachable.dao.FileDao;
import io.wildlabs.filemonster.attachable.model.AbstractFile;
import io.wildlabs.filemonster.attachable.model.File;
import io.wildlabs.filemonster.attachable.model.FileAttachable;
import io.wildlabs.filemonster.attachable.service.key.FileKeyGenerator;
import io.wildlabs.filemonster.core.Filesystem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FileServiceTest {
    @Mock
    private FileDao<AbstractFile> fileDao;

    @Mock
    private FileFactory<AbstractFile> fileFactory;

    @Mock
    private FileKeyGenerator fileKeyGenerator;

    @Mock
    private Filesystem filesystem;

    @InjectMocks
    private FileService<AbstractFile> testee;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void thatGetFileWorks() {
        // given
        String type = "profile_image";
        String classIdentifier = "ClassIdentifier";
        Long referenceId = 294L;

        FileAttachable fileAttachable = mock(FileAttachable.class);
        AbstractFile expectedFile = mock(AbstractFile.class);

        when(fileAttachable.getClassIdentifier()).thenReturn(classIdentifier);
        when(fileAttachable.getReferenceId()).thenReturn(referenceId);
        when(fileDao.findTopByClassIdentifierAndReferenceIdAndType(
                classIdentifier,
                referenceId,
                type
        )).thenReturn(Optional.of(expectedFile));

        // when
        Optional<AbstractFile> file = testee.getFile(fileAttachable, type);

        // then
        verify(fileDao).findTopByClassIdentifierAndReferenceIdAndType(
                classIdentifier,
                referenceId,
                type
        );
        assertEquals(expectedFile, file.get());
    }

    @Test
    public void thatGetFilesWorks() {
        // given
        String type = "profile_image";
        String classIdentifier = "ClassIdentifier";
        Long referenceId = 294L;

        FileAttachable fileAttachable = mock(FileAttachable.class);

        AbstractFile firstExpectedFile = mock(AbstractFile.class);
        AbstractFile secondExpectedFile = mock(AbstractFile.class);

        List<AbstractFile> expectedFiles = Arrays.asList(firstExpectedFile, secondExpectedFile);

        when(fileAttachable.getClassIdentifier()).thenReturn(classIdentifier);
        when(fileAttachable.getReferenceId()).thenReturn(referenceId);
        when(fileDao.findByClassIdentifierAndReferenceIdAndType(
                classIdentifier,
                referenceId,
                type
        )).thenReturn(expectedFiles);

        // when
        List<AbstractFile> files = testee.getFiles(fileAttachable, type);

        // then
        verify(fileDao).findByClassIdentifierAndReferenceIdAndType(
                classIdentifier,
                referenceId,
                type
        );
        assertEquals(expectedFiles, files);
    }

    @Test
    public void thatDeleteFileWorks() throws FileNotFoundException {
        // given
        String fileKey = "testFileKey";
        AbstractFile file = mock(AbstractFile.class);

        when(file.getFileKey()).thenReturn(fileKey);

        // when
        testee.deleteFile(file);

        // then
        verify(filesystem).delete(fileKey);
        verify(fileDao).delete(file);
    }

    @Test
    public void thatDeleteFileWorksIfFilesystemCantFindFile() throws FileNotFoundException {
        // given
        String fileKey = "testFileKey";
        AbstractFile file = mock(AbstractFile.class);

        doThrow(new FileNotFoundException()).when(filesystem).delete(fileKey);
        when(file.getFileKey()).thenReturn(fileKey);

        // when
        testee.deleteFile(file);

        // then
        verify(fileDao).delete(file);
    }

    @Test
    public void thatSetFileWorks() throws IOException {
        // given
        byte[] content = "Any String you want".getBytes();
        InputStream inputStream = new ByteArrayInputStream(content);
        String type = "profile_image";
        String classIdentifier = "ClassIdentifier";
        Long referenceId = 294L;
        String fileKey = "generatedFileKey";

        AbstractFile expectedFile = mock(AbstractFile.class);

        FileAttachable fileAttachable = mock(FileAttachable.class);
        when(fileAttachable.getClassIdentifier()).thenReturn(classIdentifier);
        when(fileAttachable.getReferenceId()).thenReturn(referenceId);

        when(fileDao.findTopByClassIdentifierAndReferenceIdAndType(
                classIdentifier,
                referenceId,
                type
        )).thenReturn(Optional.empty());

        when(fileKeyGenerator.generateFileKey(fileAttachable, type)).thenReturn(fileKey);
        when(fileFactory.createFile(classIdentifier, referenceId, fileKey, type)).thenReturn(expectedFile);
        when(fileDao.save(expectedFile)).thenReturn(expectedFile);

        // when
        AbstractFile file = testee.setFile(fileAttachable, type, inputStream);

        // then
        verify(fileDao).findTopByClassIdentifierAndReferenceIdAndType(
                classIdentifier,
                referenceId,
                type
        );
        verify(filesystem, never()).delete(anyString());
        verify(fileDao, never()).delete(any());
        verify(fileKeyGenerator).generateFileKey(fileAttachable, type);
        verify(filesystem).write(fileKey, content);
        verify(fileFactory).createFile(classIdentifier, referenceId, fileKey, type);
        verify(fileDao).save(expectedFile);

        assertEquals(expectedFile, file);
    }

    @Test
    public void thatSetFileDeletesOldFileIfAlreadyExist() throws IOException {
        // given
        byte[] content = "Any String you want".getBytes();
        InputStream inputStream = new ByteArrayInputStream(content);
        String type = "profile_image";
        String classIdentifier = "ClassIdentifier";
        Long referenceId = 294L;
        String fileKey = "generatedFileKey";

        AbstractFile alreadyExistingFile = mock(AbstractFile.class);
        AbstractFile expectedFile = mock(AbstractFile.class);

        FileAttachable fileAttachable = mock(FileAttachable.class);
        when(fileAttachable.getClassIdentifier()).thenReturn(classIdentifier);
        when(fileAttachable.getReferenceId()).thenReturn(referenceId);

        when(fileDao.findTopByClassIdentifierAndReferenceIdAndType(
                classIdentifier,
                referenceId,
                type
        )).thenReturn(Optional.of(alreadyExistingFile));

        when(alreadyExistingFile.getFileKey()).thenReturn(fileKey);
        when(fileKeyGenerator.generateFileKey(fileAttachable, type)).thenReturn(fileKey);
        when(fileFactory.createFile(classIdentifier, referenceId, fileKey, type)).thenReturn(expectedFile);
        when(fileDao.save(expectedFile)).thenReturn(expectedFile);

        // when
        AbstractFile file = testee.setFile(fileAttachable, type, inputStream);

        // then
        verify(fileDao).findTopByClassIdentifierAndReferenceIdAndType(
                classIdentifier,
                referenceId,
                type
        );
        verify(filesystem).delete(fileKey);
        verify(fileDao).delete(alreadyExistingFile);
        verify(fileKeyGenerator).generateFileKey(fileAttachable, type);
        verify(filesystem).write(fileKey, content);
        verify(fileFactory).createFile(classIdentifier, referenceId, fileKey, type);
        verify(fileDao).save(expectedFile);

        assertEquals(expectedFile, file);
    }
}
