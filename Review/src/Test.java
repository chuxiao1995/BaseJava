
public class Test {
	public static void main(String[] args) {
		Dog d =new Dog();
		String name = "lv";
		d.setName(name);
		System.out.println(d.getName());
		
	}

}

class Dog{
	private String name;
	private String color;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		name = "lv1";
	}
	
}
