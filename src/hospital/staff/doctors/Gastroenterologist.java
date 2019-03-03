package hospital.staff.doctors;

import enums.IllnessType;

/**
 * Doctor's extended class designed for representing a gastroenterologist.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class Gastroenterologist extends Doctor {

    private static final double C1 = 0.5;

    /**
     * Gastroenterologist constructor based on a Doctor object and an id.
     * Creates a copy of the doctor given an parameter and adds additional information.
     *
     * @param doctor the Doctor object containing basic information
     * @param id the doctor's id
     */
    public Gastroenterologist(Doctor doctor, int id) {
        // Setting the doctor's id.
        this.id = id;

        //Copying the doctor's information from the doctor given as parameter.
        type = doctor.getType();
        isSurgeon = doctor.isSurgeon;
        maxForTreatment = doctor.maxForTreatment;

        // Setting the list of illnesses the doctor can treat.
        treatableIllnesses.add(IllnessType.ABDOMINAL_PAIN);
        treatableIllnesses.add(IllnessType.ALLERGIC_REACTION);
        treatableIllnesses.add(IllnessType.FOOD_POISONING);

        // Setting the doctor's C1 and C2 values.
        consultationAmeliorationFactor = C1;
    }
}
