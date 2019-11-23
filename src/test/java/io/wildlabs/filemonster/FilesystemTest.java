package io.wildlabs.filemonster;

import io.wildlabs.filemonster.adapter.Adapter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FilesystemTest {
    @Mock
    private Adapter adapter;

    @InjectMocks
    private Filesystem testee;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void thatHasWorks() {
        // given
        String checkFileName = "testFile";
        boolean expectedResult = false;

        when(adapter.exists(checkFileName)).thenReturn(expectedResult);

        // when
        boolean fileExist = testee.has(checkFileName);

        // then
        assertFalse(fileExist);
        verify(adapter, times(1)).exists(checkFileName);
    }

    @Test
    public void thatReadWorks() throws FileNotFoundException {
        // given
        String fileName = "testFile";
        InputStream expectedInputStream = Mockito.mock(InputStream.class);

        when(adapter.read(fileName)).thenReturn(expectedInputStream);

        // when
        testee.read(fileName);

        // then
        verify(adapter, times(1)).read(fileName);
    }

    @Test
    public void thatRenameWorks() throws FileNotFoundException {
        // given
        String sourceKey = "testSourceKey";
        String targetKey = "testTargetKey";

        when(adapter.exists(sourceKey)).thenReturn(true);

        // when
        testee.rename(sourceKey, targetKey);

        // then
        verify(adapter).exists(sourceKey);
        verify(adapter).rename(sourceKey, targetKey);
    }

    @Test(expected = FileNotFoundException.class)
    public void thatRenameThrowsExceptionIfKeyDoesNotExist() throws FileNotFoundException {
        // given
        String sourceKey = "testSourceKey";
        String targetKey = "testTargetKey";

        when(adapter.exists(sourceKey)).thenReturn(false);

        // when
        testee.rename(sourceKey, targetKey);

        // then
        verify(adapter).exists(sourceKey);
    }

    @Test
    public void thatWriteWorks() {
        // given
        String expectedKey = "testKey";
        InputStream expectedInputStream = Mockito.mock(InputStream.class);

        // when
        testee.write(expectedKey, expectedInputStream);

        // then
        verify(adapter).write(expectedKey, expectedInputStream);
    }

    @Test
    public void thatDeleteWorks() throws FileNotFoundException {
        // given
        String key = "testSourceKey";
        when(adapter.exists(key)).thenReturn(true);

        // when
        testee.delete(key);

        // then
        verify(adapter).exists(key);
        verify(adapter).delete(key);
    }

    @Test(expected = FileNotFoundException.class)
    public void thatDeleteThrowsExceptionIfKeyDoesNotExist() throws FileNotFoundException {
        // given
        String key = "testSourceKey";
        when(adapter.exists(key)).thenReturn(false);

        // when
        testee.delete(key);

        // then
        verify(adapter).exists(key);
    }

    @Test
    public void thatGetKeysWorks() {
        // given
        List<String> expectedKeys = Collections.singletonList("expectedKey");
        when(adapter.getKeys()).thenReturn(expectedKeys);

        // when
        List<String> keys = testee.getKeys();

        // then
        assertEquals(expectedKeys, keys);
        verify(adapter).getKeys();
    }
}
