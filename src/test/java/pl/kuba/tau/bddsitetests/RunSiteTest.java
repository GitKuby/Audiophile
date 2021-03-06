package pl.kuba.tau.bddsitetests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:target/cucumber"},
        features = {"classpath:UserRegistration.feature", "classpath:InvalidLogin.feature",
            "classpath:CorrectLogin.feature"}
)
public class RunSiteTest {

}
