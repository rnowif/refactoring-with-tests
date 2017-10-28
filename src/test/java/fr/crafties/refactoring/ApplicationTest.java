package fr.crafties.refactoring;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    public void should_do_the_same_as_legacy() {
        Application application = new Application();
        Stream.of(Country.values()).forEach(country -> {
            Stream.of(Region.values()).forEach(region -> {
                FileExtension legacyValue = application.getExtension(country, region);
                FileExtension newValue = application.getExtensionV2(country, region);
                assertEquals(legacyValue, newValue);
            });
        });
    }

}