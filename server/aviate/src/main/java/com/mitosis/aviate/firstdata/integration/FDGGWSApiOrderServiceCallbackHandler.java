
/**
 * FDGGWSApiOrderServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.mitosis.aviate.firstdata.integration;

    /**
     *  FDGGWSApiOrderServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class FDGGWSApiOrderServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public FDGGWSApiOrderServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public FDGGWSApiOrderServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for fDGGWSApiOrder method
            * override this method for handling normal response from fDGGWSApiOrder operation
            */
           public void receiveResultfDGGWSApiOrder(
        		   com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.FDGGWSApiOrderResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from fDGGWSApiOrder operation
           */
            public void receiveErrorfDGGWSApiOrder(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for fDGGWSApiAction method
            * override this method for handling normal response from fDGGWSApiAction operation
            */
           public void receiveResultfDGGWSApiAction(
        		   com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.FDGGWSApiActionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from fDGGWSApiAction operation
           */
            public void receiveErrorfDGGWSApiAction(java.lang.Exception e) {
            }
                


    }
    