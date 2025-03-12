package com.selva.taxi.taxi;

import com.selva.taxi.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Ride
{
    private int rideId;

    private int taxiId;

    private String personName;

    private Taxi taxi;

    private Person person;

    private int startingPoint;

    private int endingPoint;

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public int getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(int taxiId) {
        this.taxiId = taxiId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getKmtravelled() {
        return Kmtravelled;
    }

    public void setKmtravelled(String kmtravelled) {
        Kmtravelled = kmtravelled;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private int destination = 1;

    private String Kmtravelled;

    private int fare;

    private String status;


    public Ride(Taxi taxi, Person person, int startingPoint, int endingPoint, int destination, int fare)
    {

        this.taxi = taxi;
        this.person = person;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.destination = destination;
        this.fare = fare;
    }

    public Ride(int rideId, int taxiId, String personName, int startingPoint, int endingPoint, String kmtravelled, int fare, String status) {
        this.rideId = rideId;
        this.taxiId = taxiId;
        this.personName = personName;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        Kmtravelled = kmtravelled;
        this.fare = fare;
        this.status = status;
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
