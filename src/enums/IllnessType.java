package enums;

/**
 * All the illnesses that can affect the patients.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public enum IllnessType {

    ABDOMINAL_PAIN("abdominal pain"),
    ALLERGIC_REACTION("allergic reaction"),
    BROKEN_BONES("broken bones"),
    BURNS("burns"),
    CAR_ACCIDENT("car accident"),
    CUTS("cuts"),
    FOOD_POISONING("food poisoning"),
    HEART_ATTACK("heart attack"),
    HEART_DISEASE("heart disease"),
    HIGH_FEVER("high fever"),
    PNEUMONIA("pneumonia"),
    SPORT_INJURIES("sport injuries"),
    STROKE("stroke");

    private String value;

    /**
     * Constructor for the enum's constants based on the value.
     *
     * @param value the constant's value
     */
    IllnessType(String value) {
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
