package samples.java8;

import gov.fcc.sample.bo.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class StreamExamplesTest {
	private List<Person> persons= Arrays.asList(new Person("justin", 30),new Person("kevin", 20),new Person("kevin", 10));
	@Before
	public void setup(){
		
	}
	
	@Test
	public void wirteFirstStreamSample(){
		List<String> list= Arrays.asList("justin","kevin","dan");
		list.sort(( d1,  d2)->d1.compareTo(d2));
		list.forEach(s->System.out.println(s));
	}
	@Test
	public void filterAgeGreaterThan10(){
		List<Person> olderThan10 =persons.stream().filter(p->p.getAge()>10)
		.collect(Collectors.toList());
		olderThan10.forEach(p->System.out.println(p.toString()));
	}
	@Test
	public void shouldGroupByAge(){
		Map<Integer, List<Person>> peopleByAge = persons.stream().collect(Collectors.groupingBy(Person::getAge));
		System.out.println("Grouped by age: " + peopleByAge);
	}
}
