package hospital.patients.comparators;

import enums.Urgency;

import java.util.Comparator;

/**
 * Comparator class used for comparing urgencies.
 * The class is based on the singleton design.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class UrgencyComparator implements Comparator<Urgency> {

    private static UrgencyComparator instance = null;

    private UrgencyComparator() {
    }

    /**
     * Getter for the single one instance of the class.
     *
     * @return the single one instance
     */
    public static UrgencyComparator getInstance() {
        if (instance == null) {
            instance = new UrgencyComparator();
        }

        return instance;
    }

    /**
     * Compares the two urgencies.
     * Returns -1, 0 or 1 as the former urgency is greater than, equal to, or less than the latter
     * urgency because they should be sorted in descending order.
     *
     * @param o1 the former urgency
     * @param o2 the latter urgency
     * @return value corresponding to the comparision
     */
    @Override
    public int compare(Urgency o1, Urgency o2) {
        if (o1 == o2) {
            return 0;
        }

        switch (o1) {
            case IMMEDIATE:
                return -1;

            case URGENT:
                if (o2 == Urgency.IMMEDIATE) {
                    return 1;
                } else {
                    return -1;
                }

            case LESS_URGENT:
                if ((o2 == Urgency.IMMEDIATE) || (o2 == Urgency.URGENT)) {
                    return 1;
                } else {
                    return -1;
                }

            case NON_URGENT:
                if ((o2 == Urgency.IMMEDIATE) || (o2 == Urgency.URGENT)
                        || (o2 == Urgency.LESS_URGENT)) {
                    return 1;
                } else {
                    return -1;
                }

            case NOT_DETERMINED:
                return 1;

            default:
                return 0;
        }
    }
}
