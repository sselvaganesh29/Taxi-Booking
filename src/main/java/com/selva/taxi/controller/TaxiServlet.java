package com.selva.taxi.controller;

import com.selva.taxi.taxi.*;
import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TaxiServlet extends HttpServlet
{

    TaxiManager taxiManager = TaxiManager.INSTANCE;
    RideManager rideManager = RideManager.INSTANCE;





protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{


    JSONObject jsonrespose = new JSONObject();

    String path = request.getServletPath();

    String taxiName = request.getParameter("taxiName");

    String taxiNo = request.getParameter("taxiNo");

    String startingPoint = request.getParameter("startingPoint");

    String endingPoint = request.getParameter("endingPoint");

    String personName = request.getParameter("personName");

    String encryptedToken = request.getHeader("token");

    response.setContentType("application/json");

    PrintWriter out = response.getWriter();

    String key = "myownkey12345678";

    SecretKey secretKey = new SecretKeySpec(key.getBytes(),"AES");



    try {

        String decryptedToken = EncryptDecrypt.decrypt(encryptedToken,secretKey);

       String userName = JSONtoken.getUsername(decryptedToken);


        switch (path) {



            case "/addTaxi":

                if (taxiName != null && taxiNo != null && userName != null) {
                    Taxi taxi = new Taxi(UserDBAccess.getUserId(userName),Integer.parseInt(taxiNo), taxiName);
                   if( taxiManager.addTaxi(taxi) ) {
                       jsonrespose.put("status", "Your taxi details added successfully");
                   }
                   else {
                       jsonrespose.put("status", "something went wrong to add our taxi");
                   }
                } else {
                    jsonrespose.put("status", "Enter the details properly");
                }

                break;

            case "/createRide":

                boolean isExist = taxiManager.getTaxiByNo(Integer.parseInt(taxiNo));

                Taxi taxi = new Taxi();

                if (isExist) {

                    if (startingPoint != null && endingPoint != null) {

                        int res = taxi.createRide(UserDBAccess.getUserId(userName),Integer.parseInt(taxiNo), Integer.parseInt(startingPoint), Integer.parseInt(endingPoint));
                        if ( res == 1)
                            jsonrespose.put("status", "your ride booked successfully");
                        else if ( res == 2)
                            jsonrespose.put("status", "your not booked something went wrong");

                    }
                    else
                    {
                        jsonrespose.put("status", "Missing parameters");
                    }

                } else {
                    jsonrespose.put("status", "Taxi doesn't exist");
                }
                break;

            default:
                jsonrespose.put("status","error");
                jsonrespose.put("message","invalid request");


        }
    }
    catch ( Exception e )
    {
        jsonrespose.put("Exception occurs "," "+e.getMessage());
    }

    PrintWriter writer = response.getWriter();

    writer.write(jsonrespose.toString());

    writer.flush();

 }

 //update taxi
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        JSONObject jsonresponse = new JSONObject();

        String path = request.getServletPath();

        String taxiNo = request.getParameter("taxiNo");

        String newTaxiName = request.getParameter("newTaxiName");

        String rideStatus = request.getParameter("rideStatus");

        String rideId = request.getParameter("rideId");

        response.setContentType("application/json");

        PrintWriter out = response.getWriter();

        try
        {
            switch (path) {

                case "/updateTaxi":

                if (taxiNo != null && newTaxiName != null) {
                    int taxiNumber = Integer.parseInt(taxiNo);
                    boolean isexist = taxiManager.getTaxiByNo(taxiNumber);
                    boolean isupdate = taxiManager.updateTaxi(taxiNumber, newTaxiName);
                    if ( isexist ) {
                        if (isupdate) {
                            jsonresponse.put("status :", newTaxiName + " updated Successfully ");
                        } else {
                            jsonresponse.put("status", "not updated");
                        }
                    }
                    else
                        {
                            jsonresponse.put("status","taxi not found");
                        }
                } else {
                    jsonresponse.put("status","parameter missing");
                }
                break;

                case "/updateRideStatus":

                    if ( rideStatus != null && rideId != null ) {

                      boolean check =   rideManager.setRideStatus(Integer.parseInt(rideId),rideStatus);

                      if ( check )
                      {
                          jsonresponse.put("status","Ride status updated to :"+rideStatus);
                      }
                      else
                      {
                          jsonresponse.put("status","some thing went wrong");
                      }

                    }
                    else
                    {
                        jsonresponse.put("status","parameter missing");
                    }
                        break;

                default:
                    jsonresponse.put("status","Invalid request");
            }
        }
        catch (Exception e)
        {
            jsonresponse.put("status " , " "+e.getMessage());
        }

        response.getWriter().write(jsonresponse.toString());
        response.getWriter().flush();


    }

    // Delete Taxi
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        JSONObject jsonresponse = new JSONObject();

        String path = request.getServletPath();

        String taxiNo = request.getParameter("taxiNo");

        String rideId = request.getParameter("rideId");

        response.setContentType("application/json");

        try
        {
            switch (path) {

                case "/deleteTaxi":

                    if (taxiNo != null) {
                        int taxiNumber = Integer.parseInt(taxiNo);
                        boolean isexist = taxiManager.getTaxiByNo(taxiNumber);
                        boolean isdelete = taxiManager.deleteTaxi(taxiNumber);
                        if ( isexist ) {
                            if (isdelete) {
                                jsonresponse.put("status :" ,"deleted Successfully ");
                            } else {
                                jsonresponse.put("status", "not deleted");
                            }
                        }
                        else
                        {
                            jsonresponse.put("status","taxi not found");
                        }
                    } else {
                        jsonresponse.put("status","parameter missing");
                    }
                    break;

                case "/deleteRide":

                    int isRideDelete = rideManager.deleteRide(Integer.parseInt(rideId));

                    if ( isRideDelete == 1 )
                    {
                        jsonresponse.put("status","ride deleted successfully");
                    }
                    else if ( isRideDelete == 2)
                    {
                        jsonresponse.put("status","ride cant deleted!!!!!");
                    }
                    else if ( isRideDelete == 3 )
                    {
                        jsonresponse.put("status","This ride doesn't exist");
                    }

                    break;

                default:
                    jsonresponse.put("status","Invalid request");
            }
        }
        catch (Exception e)
        {
            jsonresponse.put("status "," "+e.getMessage());
        }

        response.getWriter().write(jsonresponse.toString());
        response.getWriter().flush();


    }

    // Get Taxi Details by taxiNo & get all taxi
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        JSONObject jsonresponse = new JSONObject();

        JSONArray jsonArray  = new JSONArray();

         String path = request.getServletPath();

             try {

                 switch (path) {

                     case "/getAllTaxi":

                         List<Taxi> taxiList = taxiManager.getAllTaxi();
                          if (!taxiList.isEmpty()) {

                              for ( Taxi t : taxiList)
                              {
                                  JSONObject jsonObject = new JSONObject();

                                 jsonObject.put("Taxi",t.getTaxino());
                                 jsonObject.put("Taxi name",t.getTaxiName());

                                 jsonArray.put(jsonObject);
                              }

                              jsonresponse.put("Taxis ",jsonArray);
                          }
                          else {
                              jsonresponse.put("status", "Taxi is empty");
                          }

                         break;

                     default:
                         jsonresponse.put("status", "Invalid request");
                 }

             } catch (Exception e) {
                 jsonresponse.put("status", " " + e.getMessage());
             }
        response.getWriter().write(jsonresponse.toString());
        response.getWriter().flush();
         }





    }





