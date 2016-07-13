package org.gurpsdomain;

public enum Difficulty {
	E  ("Easy"),
	A  ("Average"),
	H  ("Hard"),
	VH ("Very Hard");

	public String name;

	Difficulty(String name) {
		this.name = name;
	}
}
