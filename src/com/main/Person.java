package com.main;
    public class Person{
        String name;
        Integer sex;
        Person child;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Integer getSex() {
            return sex;
        }
        public void setSex(Integer sex) {
            this.sex = sex;
        }
        public Person getChild() {
            return child;
        }
        public void setChild(Person child) {
            this.child = child;
        }
        
    }