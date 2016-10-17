package com.wesleyreisz.mycis411mostlymodernmusicstore;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.wesleyreisz.mycis411mostlymodernmusicstore.service.MockMusicService;
import com.wesleyreisz.mycis411mostlymodernmusicstore.home.model.Song;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.wesleyreisz.mycis411mostlymodernmusicstore", appContext.getPackageName());
    }

    @Test
    public void testFindAll(){
        MockMusicService service = new MockMusicService();
        List<Song> songs = service.findAll();
        Assert.assertNotNull(songs);

        boolean testResultFound=false;
        for(Song song:songs){
            if (song.getSongTitle().equalsIgnoreCase("Dark Horse")){
                testResultFound=true;
            }
        }
        Assert.assertEquals(true,testResultFound);
    }

    @Test
    public void testFindOne(){
        MockMusicService service = new MockMusicService();
        Song song = service.findOne("Dark Horse");
        Assert.assertEquals("Dark Horse", song.getSongTitle());
        Assert.assertEquals("Katy Perry", song.getArtistName());
        Assert.assertEquals("Single", song.getAlbumTitle());
    }
}
