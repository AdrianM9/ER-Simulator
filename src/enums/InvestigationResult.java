package enums;

/**
 * Actions that need to be performed upon a patient after an investigation.
 * Can be used by the objects representing doctors/nurses/er_technicians or the queues.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public enum InvestigationResult {

    HOSPITALIZE("hospitalize"),
    NOT_DIAGNOSED("not diagnosed"),
    OPERATE("operate"),
    TREATMENT("treatment");

    private String value;

    /**
     * Constructor for the enum's constants based on the value.
     *
     * @param value the constant's value
     */
    InvestigationResult(String value) {
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
