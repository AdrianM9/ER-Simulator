package enums;

/**
 * Enum used to store the types of urgency a patient can have.
 * IMMEDIATE greater than URGENT greater than LESS_URGENT greater than NON_URGENT
 * NON_URGENT means it will not enter the emergency flows.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public enum Urgency {

    IMMEDIATE,
    URGENT,
    LESS_URGENT,
    NON_URGENT,
    NOT_DETERMINED,
}
