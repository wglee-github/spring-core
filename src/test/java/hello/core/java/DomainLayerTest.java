package hello.core.java;

public class DomainLayerTest {

	public static void main(String[] args) {
		Person person = Person.ofCreatePerson("이우기", 39, "840223");
		System.out.println("person = " + person);
		
		Person person2 = Person.ofCreatePerson("김민애", 36, "870821");
		System.out.println("person2 = " + person2);
	}
	
}
