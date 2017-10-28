package fr.crafties.refactoring;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiPredicate;

class Application {

    /**
     * Get extension
     * @param country
     * @param region
     * @return
     */
    FileExtension getExtension(Country country, Region region) {
        FileExtension extension = null;
        switch (country) {
            case BELGIUM:
                return FileExtension.XLS;
            case UK:
                extension = FileExtension.XLSX;
                break;
            case FRANCE:
                if (region == Region.WESTERN_EUROPE) {
                    extension = FileExtension.CSV;
                }
            case CHINA:
            case JAPAN:
                if (region == Region.MIDDLE_EAST && country == Country.CHINA || region == Region.SOUTH_EAST_ASIA) {
                    extension = FileExtension.XLS;
                } else {
                    extension = FileExtension.CSV;
                }
                break;
        }

        if (region == Region.MIDDLE_EAST) {
            return FileExtension.CSV;
        }

        if (region == Region.NORTH_AMERICA || country == Country.UK) {
            extension = FileExtension.XML;
        }

        return extension;
    }

    FileExtension getExtensionV2(Country country, Region region) {
        LinkedHashMap<BiPredicate<Country, Region>, FileExtension> orderedSpecifications = new LinkedHashMap<>();
        orderedSpecifications.put((c, r) -> c.equals(Country.BELGIUM), FileExtension.XLS);
        orderedSpecifications.put((c, r) -> r.equals(Region.MIDDLE_EAST), FileExtension.CSV);
        orderedSpecifications.put((c, r) -> r.equals(Region.NORTH_AMERICA), FileExtension.XML);
        orderedSpecifications.put((c, r) -> c.equals(Country.UK), FileExtension.XML);
        orderedSpecifications.put((c, r) -> c.equals(Country.FRANCE) && r.equals(Region.SOUTH_EAST_ASIA), FileExtension.XLS);
        orderedSpecifications.put((c, r) -> c.equals(Country.FRANCE) && !r.equals(Region.SOUTH_EAST_ASIA), FileExtension.CSV);
        orderedSpecifications.put((c, r) -> c.equals(Country.CHINA) && (r.equals(Region.MIDDLE_EAST) || r.equals(Region.SOUTH_EAST_ASIA)), FileExtension.XLS);
        orderedSpecifications.put((c, r) -> c.equals(Country.CHINA) && !(r.equals(Region.MIDDLE_EAST) || r.equals(Region.SOUTH_EAST_ASIA)), FileExtension.CSV);
        orderedSpecifications.put((c, r) -> c.equals(Country.JAPAN) && r.equals(Region.SOUTH_EAST_ASIA), FileExtension.XLS);
        orderedSpecifications.put((c, r) -> c.equals(Country.JAPAN) && !r.equals(Region.SOUTH_EAST_ASIA), FileExtension.CSV);

        return orderedSpecifications.entrySet().stream()
            .filter(entry -> entry.getKey().test(country, region))
            .map(Map.Entry::getValue)
            .findFirst().orElse(null);
    }

}
