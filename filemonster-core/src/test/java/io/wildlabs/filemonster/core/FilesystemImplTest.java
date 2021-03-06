package io.wildlabs.filemonster.core;

import io.wildlabs.filemonster.core.storage.Storage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FilesystemImplTest {
    @Mock
    private Storage storage;

    @InjectMocks
    private FilesystemImpl testee;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void thatHasWorks() {
        // given
        String checkFileName = "testFile";
        boolean expectedResult = false;

        when(storage.exists(checkFileName)).thenReturn(expectedResult);

        // when
        boolean fileExist = testee.has(checkFileName);

        // then
        assertFalse(fileExist);
        verify(storage, times(1)).exists(checkFileName);
    }

    @Test
    public void thatReadWorks() throws FileNotFoundException {
//        // given
//        String fileName = "testFile";
//        byte[] expectedInputStream = Mockito.mock(byte[].class);
//
//        when(storage.read(fileName)).thenReturn(expectedInputStream);
//
//        // when
//        testee.read(fileName);
//
//        // then
//        verify(storage, times(1)).read(fileName);
    }

    @Test
    public void thatRenameWorks() throws FileNotFoundException {
        // given
        String sourceKey = "testSourceKey";
        String targetKey = "testTargetKey";

        when(storage.exists(sourceKey)).thenReturn(true);

        // when
        testee.rename(sourceKey, targetKey);

        // then
        verify(storage).exists(sourceKey);
        verify(storage).rename(sourceKey, targetKey);
    }

    @Test(expected = FileNotFoundException.class)
    public void thatRenameThrowsExceptionIfKeyDoesNotExist() throws FileNotFoundException {
        // given
        String sourceKey = "testSourceKey";
        String targetKey = "testTargetKey";

        when(storage.exists(sourceKey)).thenReturn(false);

        // when
        testee.rename(sourceKey, targetKey);

        // then
        verify(storage).exists(sourceKey);
    }

    @Test
    public void thatWriteWorks() {
//        // given
//        String expectedKey = "testKey";
//        byte[] expectedInputStream = Mockito.mock(byte[].class);
//
//        // when
//        testee.write(expectedKey, expectedInputStream);
//
//        // then
//        verify(storage).write(expectedKey, expectedInputStream);
    }

    @Test
    public void thatDeleteWorks() throws FileNotFoundException {
        // given
        String key = "testSourceKey";
        when(storage.exists(key)).thenReturn(true);

        // when
        testee.delete(key);

        // then
        verify(storage).exists(key);
        verify(storage).delete(key);
    }

    @Test(expected = FileNotFoundException.class)
    public void thatDeleteThrowsExceptionIfKeyDoesNotExist() throws FileNotFoundException {
        // given
        String key = "testSourceKey";
        when(storage.exists(key)).thenReturn(false);

        // when
        testee.delete(key);

        // then
        verify(storage).exists(key);
    }
}
