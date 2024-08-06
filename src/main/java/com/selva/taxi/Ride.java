package com.selva.taxi;

public class Ride
{
    private int rideId;

    private Taxi taxi;

    private Person person;

    private int startingPoint;

    private int endingPoint;

    private int destination = 1;

    private int fare;

    public Ride( int rideId, Taxi taxi, Person person, int startingPoint, int endingPoint,int destination,int fare)
    {
        this.rideId = rideId;
        this.taxi = taxi;
        this.person = person;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.destination = destination;
        this.fare = fare;
    }

    public int getRideId()
    {
        return rideId;
    }

    public Taxi getTaxi()
    {
        return taxi;
    }

    public Person getPerson()
    {
        return person;
    }

    public int getStartingPoint()
    {
        return startingPoint;
    }

    public int getEndingPoint()
    {
        return endingPoint;
    }

    public void setRideid(int id)
    {
        this.rideId = id;
    }

    public void setTaxi(Taxi taxi)
    {
        this.taxi = taxi;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public void setStartingPoint(int startingPoint)
    {
        this.startingPoint = startingPoint;
    }

    public void setEndingPoint(int endingPoint)
    {
        this.endingPoint = endingPoint;
    }

    public int getDestination()
    {
        return destination;
    }

    public void setDestination(int destination)
    {
        this.destination = destination;
    }

    public int getFare()
    {
        return fare;
    }

    public void setFare(int fare)
    {
        this.fare = fare;
    }
}
