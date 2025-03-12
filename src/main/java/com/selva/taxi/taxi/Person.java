package com.selva.taxi.taxi;

public class Person
{
    private int id;

    private String name;


    public Person( String name )
    {
        this.name = name;
    }


    public int getPersonId() {
        return id;
    }

    public void setPersonId(int id) {
        this.id = id;
    }

    public String getPersonName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
