package com.damie.tpservice;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by damie on 03/10/2017.
 */

public class DoawnLoaderThread extends Thread {

    int total=10000000;
    AtomicInteger nbByte=new AtomicInteger(0);
    @Override
    public void run() {
        while (nbByte.incrementAndGet()<total){
            if(nbByte.get()%100==0){
                System.out.printf(((float)nbByte.get()/(float)total)*100+"*/* \n");
            }
        }
    }

    public AtomicInteger getNbByte() {
        return nbByte;
    }

    public static void main(String args[]){
    }
}
