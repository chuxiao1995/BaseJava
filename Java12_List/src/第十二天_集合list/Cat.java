package ��ʮ����_����list;

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
//		if (obj == null) {// �Ƿ�Ϊ��
//			return false;
//		}
//		if (this == obj) {// �Ƿ�Ϊ����
//			return true;
//
//		}
//		Class objname = obj.getClass();
//		if (objname != Cat.class) {// �ж��Ƿ���ͬһ��
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