package com.techcode.pro.entity;



public class StudentBuilder {

  private Integer id;
  private String name;
  private Integer rollnumber;
  private Byte gender;
  private String class_;

  public static StudentBuilder newBuilder() { 
  	return new StudentBuilder(); 
  }

  public StudentBuilder id(Integer value) {
  	this.id = value;
  	return this;
  }

  public StudentBuilder name(String value) {
  	this.name = value;
  	return this;
  }

  public StudentBuilder rollnumber(Integer value) {
  	this.rollnumber = value;
  	return this;
  }

  public StudentBuilder gender(Byte value) {
  	this.gender = value;
  	return this;
  }

  public StudentBuilder class_(String value) {
  	this.class_ = value;
  	return this;
  }

  public Student build() { 
  	Student result = new Student(); 
  	
  	result.setId(id);
  	result.setName(name);
  	result.setRollnumber(rollnumber);
  	result.setGender(gender);
  	result.setClass_(class_); 
  	return result;
  }

  public static StudentBuilder buildUpon(Student original) {
  	StudentBuilder builder = newBuilder();
  	builder.id(original.getId());
  	builder.name(original.getName());
  	builder.rollnumber(original.getRollnumber());
  	builder.gender(original.getGender());
  	builder.class_(original.getClass_());
  	return builder;
  }
}