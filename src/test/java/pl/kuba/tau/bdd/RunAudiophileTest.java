package pl.kuba.tau.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:target/cucumber"},
        features = {"classpath:ArtistsByRegex.feature",
                "classpath:DeleteArtists.feature"}
)
public class RunAudiophileTest {
}
