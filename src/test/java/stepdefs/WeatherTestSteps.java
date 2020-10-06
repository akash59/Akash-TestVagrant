package stepdefs;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import pages.common.PageHeader;
import pages.main.NDTVMainPage;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class WeatherTestSteps extends BaseTest
{

    private final NDTVMainPage ndtvMainPage;

    // No need to define the constructor now as we are using lombak -> @AllArgsConstructor during execution



}