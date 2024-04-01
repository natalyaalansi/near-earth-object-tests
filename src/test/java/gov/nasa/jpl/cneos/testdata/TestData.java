package gov.nasa.jpl.cneos.testdata;

public class TestData {
    public class DateRange {
        public static final String NEAR_FUTURE = "Near future (within 60 days)";
        public static final String RECENT_PAST = "Recent past (within 60 days)";
        public static final String PLUS_MINUS_30DAYS = "plus/minus 30 days";
        public static final String FUTURE = "Future (within a year)";
        public static final String PAST = "Past (within a year)";
        public static final String FUTURE_ONLY = "Future only";
        public static final String PAST_ONLY = "Past only";
        public static final String ALL = "All available data";
    }

    public class DistMax {
        public static final String LE1LD = "Nominal dist. <= 1LD";
        public static final String LE5LD = "Nominal dist. <= 5LD";
        public static final String LE10LD = "Nominal dist. <= 10LD";
        public static final String LE005AU = "Nominal dist. <= 0.05au";
        public static final String LE01AU = "Nominal dist. <= 0.1au";
        public static final String LE02AU = "Nominal dist. <= 0.2au";
    }

    public class HMax {
        public static final String NO_LIMIT = "no H limit";
        public static final String LE14 = "H <= 14";
        public static final String LE16 = "H <= 16";
        public static final String LE18 = "H <= 18";
        public static final String LE20 = "H <= 20";
        public static final String LE22 = "H <= 22";
        public static final String LE24 = "H <= 24";
        public static final String LE26 = "H <= 26";
    }
}
