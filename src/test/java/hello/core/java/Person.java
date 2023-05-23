package hello.core.java;

import lombok.ToString;

@ToString
public class Person {

	private String name;
	private int age;
	private String birth;

	public static Person ofCreatePerson(String name, int age ,String birth) {
		Person person = new Person();
		person.name = name;
		person.addAge(age);
		person.birth = birth;
		System.out.println("person = " + System.identityHashCode(person));
		return person;
	}
	
	private void addAge(int age) {
		this.age = age + 1;
	}
}
