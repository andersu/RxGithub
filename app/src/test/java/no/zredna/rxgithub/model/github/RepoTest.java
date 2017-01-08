package no.zredna.rxgithub.model.github;

import org.junit.Before;
import org.junit.Test;

import no.zredna.rxgithub.RxGitHubTest;

import static org.junit.Assert.*;

public class RepoTest extends RxGitHubTest {
    private Repo repo;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        repo = new Repo();
    }

    @Test
    public void getCreatedFormattedString() throws Exception {
        repo.setCreatedAt("2017-01-03T18:02:34Z");

        String expectedResult = "Jan 3, 2017";
        String createdFormattedString = repo.getCreatedFormattedString();

        assertEquals(expectedResult, createdFormattedString);
    }

    @Test
    public void getCreatedFormattedString_withInvalidCreatedAtString_returnsNull() throws Exception {
        repo.setCreatedAt("8792318791237809fjklse");

        String createdFormattedString = repo.getCreatedFormattedString();

        assertNull(createdFormattedString);
    }
}