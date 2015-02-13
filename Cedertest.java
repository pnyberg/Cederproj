public class Cedertest {
	public static void main(String[] args) {
		Cederman person1 = new Cederman(0, 0, 0, Cederpanel.generateName());
		Cederman person2 = new Cederman(0, 0, 0, Cederpanel.generateName());

		Cederman newPerson = Cederpanel.generateNewCederman(person1, person2);

		System.out.println(newPerson.getName());
	}
}