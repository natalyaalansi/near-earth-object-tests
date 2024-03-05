package gov.nasa.jpl.cneos.testdata;

public class TestData {
    public class DateRange {
        public static final String nearFuture = "Near future (within 60 days)";
        public static final String recentPast = "Recent past (within 60 days)";
        public static final String plusMinus30days = "plus/minus 30 days";
        public static final String Future = "Future (within a year)";
        public static final String Past = "Past (within a year)";
        public static final String FutureOnly = "Future only";
        public static final String PastOnly = "Past only";
        public static final String All = "All available data";
    }

    public class DistMax {
        public static final String le1Ld = "Nominal dist. <= 1LD";
        public static final String le5Ld = "Nominal dist. <= 5LD";
        public static final String le10Ld = "Nominal dist. <= 10LD";
        public static final String le005Au = "Nominal dist. <= 0.05au";
        public static final String le01Au = "Nominal dist. <= 0.1au";
        public static final String le02Au = "Nominal dist. <= 0.2au";
    }

    public class HMax {
        public static final String noLimit = "no H limit";
        public static final String le14 = "H <= 14";
        public static final String le16 = "H <= 16";
        public static final String le18 = "H <= 18";
        public static final String le20 = "H <= 20";
        public static final String le22 = "H <= 22";
        public static final String le24 = "H <= 24";
        public static final String le26 = "H <= 26";
    }
}
