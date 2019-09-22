package 第十二天_集合list;

class Cat {
	private String name;
	private int age;

	public Cat(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (obj == null) {// 是否为空
//			return false;
//		}
//		if (this == obj) {// 是否为本身
//			return true;
//
//		}
//		Class objname = obj.getClass();
//		if (objname != Cat.class) {// 判断是否是同一类
//			return false;
//
//		}
//		Cat b = (Cat) obj;
//		String bname = b.getName();
//		int bage = b.getAge();
//		boolean nameEqual = false;
//		if (name == null) {
//			if (bname == null) {
//				nameEqual = true;
//			}
//
//		}
//		else {
//			nameEqual =name.equals(bname);
//		}
//		return nameEqual && (age == bage); 
//
//	}
//
}