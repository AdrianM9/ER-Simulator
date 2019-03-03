# ER-Simulator
Simulates different situations which happens inside an emergency room and manages the the patients and the hospital's staff.

--- General logic:
	The program starts by reading the information about the hospital and storing
that information in an InputReader object. Then a management of the hospital is
needed, so a HospitalManager object is created based on the input. Then the
simulation starts.
	For every round the patients which come at the hospital in the current round
are moved into the triage queue where they are sorted and then the nurses
calculates their urgencies, but a nurse can calculate only one urgency. All the
patients with the urgency calculated are then moved into the examination queue
and then starts their examination. A list of doctors who can treat the patient's
illness is created for every patient. Then, for every patient, the first doctor
in the list takes action over patient. If the patient hasn't been at
investigation, the doctor can send him to investigation or send him home with
treatment. If the patient has been already at investigation, the doctor can send
him home, can hospitalize or operate him. That doctor is then moved at the end
of every other list he is found in and a connection between him and the patient
he treated is made (the patient is added to the doctor's hospitalized patients
list and the doctor is set as the doctor who is responsible for the patient).
Then the patients the doctors sent to investigations are added in the
investigation queue and then they are investigated and sent back in the
examination queue. All the three queues sort the patients before working with
patients placed within them.
	After this stage, the observers are notified, the round number is increased
and the next round is simulated.

--- enums package
	The package contains the enums needed for interpreting values of different
fields.

--- hospital package
	This package contains all the information and the majority of used methods,
like the ones performed by doctors, nurses, ER technicians or even patients. The
hospital's queues are also here.

	--- hospital.patients package
		Here we have everything we need to manage the patients in the hospital.

		--- hospital.patients.comparators package
			Here we have two classes based on the singleton pattern. They
		implement the Comparator interface and allow to compare urgencies and
		patients.

	--- hospital.queues package
		Here are the three hospital's queues where the patients can be placed. 
		They are implemented using a list and a sort option which sorts the
	patients using the PatientComparator instance.

	--- hospital.staff package
		Here are the doctors, nurses and ER Technicians. The classes contains
	the methods specific for every kind of staff member.

		--- hospital.staff.doctors package
			Here we found all the doctors. There is a basic class Doctor and
		particular classes for every kind of doctor.

--- services package
	This package contains the classes responsible for reading the input,
printing the output and simulate the events. The InputReader class stores
the data from the input file. The HospitalManager class is the core of the
program. Here are simulated the rounds by moving the patients from a queue
to another one or by hospitalizing them or sending home. The program's logic
is in this class. It also uses the Observable pattern, being the notifier
for the observers.

--- services.outputWriter package
	In this package can be found the classes responsible with the program's
output. Every class is an observer, so they use the Observable pattern, being
the observers.
