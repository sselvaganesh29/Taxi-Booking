package com.selva.taxi;

import java.util.Optional;

public class Main
{
    public static void main(String[] args)
    {

        AppProperties.loadProperties();

        String taxi1Person = AppProperties.getString("taxi1Person","NO KEY");
        Optional<String> Taxi1Name = AppProperties.getOptionalString("taxi1Name");
        String taxi2Name = AppProperties.getString("taxi2Name","NO KEY");

        String taxi1Name = Taxi1Name.orElse("NO KEY");




        int rideId = AppProperties.getInt("rideId",0 );
        Optional<Integer> pId = AppProperties.getInt("personId");
        Optional<Integer> sp = AppProperties.getInt("startingPoint");
        Optional<Integer> en = AppProperties.getInt("endingPoint");
        Optional<Integer> Taxi1No = AppProperties.getInt("taxi1No");
        Optional<Integer> Taxi2No =AppProperties.getInt("taxi2No");

       

        int taxi1No = Taxi1No.orElse(0);
        int taxi2No = Taxi2No.orElse(0);
        int personId = pId.orElse(0);
        int startingPoint = sp.orElse(0);
        int endingPoint = en.orElse(0);


        Taxi taxi1 = new Taxi(taxi1No, taxi1Name);
        Taxi taxi2 = new Taxi(taxi2No, taxi2Name);
        Taxi taxi3 = new Taxi(3, "Tata");
        Taxi taxi5 = new Taxi(6, "Ford");

        TaxiManager taximanager = TaxiManager.INSTANCE;

        taximanager.addTaxi(taxi1);
        taximanager.addTaxi(taxi2);
        taximanager.addTaxi(taxi3);
        taximanager.addTaxi(taxi5);

        taximanager.updateTaxi(taxi1No,"rolls");
        taximanager.updateTaxi(3,"Nano");

        taximanager.deleteTaxi(3);



        taxi1.createRide(rideId, new Person(personId, taxi1Person), startingPoint, endingPoint);
        taxi2.createRide(1000, new Person(26,"selva"), 2, 9);
        taxi5.createRide(1002,new Person(36,"suwetha"),1,5 );

        taximanager.getTaxiDetails(6);

        taximanager.getAllTaxi();


        System.out.println("-----------------");

    }
}