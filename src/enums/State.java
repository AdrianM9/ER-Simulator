package enums;

/**
 * All the patient's states based on the queue they are in or its hospitalization status.
 * The string values are needed for printing the output messages.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public enum State {

    TRIAGE_QUEUE("in triage queue"),
    EXAMINATIONS_QUEUE("in examinations queue"),
    INVESTIGATIONS_QUEUE("in investigations queue"),
    HOSPITALIZED_CARDIO("hospitalized by cardiologist"),
    HOSPITALIZED_ERPHYSICIAN("hospitalized by erphysician"),
    HOSPITALIZED_GASTRO("hospitalized by gastroenterologist"),
    HOSPITALIZED_SURGEON("hospitalized by general surgeon"),
    HOSPITALIZED_INTERNIST("hospitalized by internist"),
    HOSPITALIZED_NEURO("hospitalized by neurologist"),
    OPERATED_CARDIO("operated by cardiologist"),
    OPERATED_ERPHYSICIAN("operated by erphysician"),
    OPERATED_SURGEON("operated by general surgeon"),
    OPERATED_NEURO("operated by neurologist"),
    HOME_CARDIO("sent home by cardiologist"),
    HOME_ERPHYSICIAN("sent home by erphysician"),
    HOME_GASTRO("sent home by gastroenterologist"),
    HOME_SURGEON("sent home by general surgeon"),
    HOME_INTERNIST("sent home by internist"),
    HOME_NEURO("sent home by neurologist"),
    HOME_DONE_TREATMENT("sent home after treatment"),
    OTHER_HOSPITAL("transferred to other hospital");

    private String value;

    /**
     * Constructor for the enum's constants based on the value.
     *
     * @param value the constant's value
     */
    State(String value) {
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
