package fr.crafties.refactoring;

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
            case BELIGUM:
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

}
