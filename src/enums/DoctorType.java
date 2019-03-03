package enums;

/**
 * All types of doctors available in the hospital.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public enum DoctorType {

    CARDIOLOGIST("Cardiologist"),
    ER_PHYSICIAN("ERPhysician"),
    GASTROENTEROLOGIST("Gastroenterologist"),
    GENERAL_SURGEON("General Surgeon"),
    INTERNIST("Internist"),
    NEUROLOGIST("Neurologist");

    private String value;

    /**
     * Constructor for the enum's constants based on the value.
     *
     * @param value the constant's value
     */
    DoctorType(String value) {
        this.value = value;
    }

    /**
     * Getter for the constant's value.
     *
     * @return the constant's value
     */
    public String getValue() {
        return value;
    }
}
