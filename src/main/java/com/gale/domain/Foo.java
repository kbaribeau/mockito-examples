package com.gale.domain;

public class Foo {
	
	private Bar bar;

	public Bar getBar() {
		return bar;
	}
	
	public String getBarName() {
		return bar.getName();
	}
	
	public void wuzzle() {
		Bar newBar = new Bar();
		newBar.setName("wuzzle");
		bar.wuzzle(newBar);
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}

	public String foo() {
		return "foo!";
	}

	public void setBarName(String newName) {
		bar.setName(newName);
	}
}
