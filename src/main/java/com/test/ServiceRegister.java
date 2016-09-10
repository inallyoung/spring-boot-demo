package com.test;

import com.test.abstractions.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inal on 10.09.2016.
 */
public class ServiceRegister implements Service {

    private List<Service> services = new ArrayList<Service>();

    public void register(Service service) {
        services.add(service);
    }

    @Override
    public void init() throws Exception {
        for (Service service : services) {
            service.init();
        }
    }

    @Override
    public void stop() throws Exception {
        for (Service service : services) {
            try {
                service.stop();
            } catch (Exception e) {
                System.out.println("Error when stopped service " + e.getMessage());
            }
        }
    }
}
