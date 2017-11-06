package codesprinters.bdd;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.*;

/**
 * Created by Michal on 05.06.2017.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        plugin={"pretty", "html:target/cucumber"},
        features="src/test/java/codesprinters/bdd/features/"
)
public class Runner {
}
