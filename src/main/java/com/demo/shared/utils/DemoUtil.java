package com.demo.shared.utils;


import org.springframework.stereotype.Component;

@Component
public class DemoUtil {

    private static final double EARTH_RADIUS_KM = 6371.0;
    private static final double EARTH_RADIUS_MILES = 3958.8;

    /**
     * Calculate distance between two points in kilometers
     */
    public double distanceInKm(double lat1, double lon1, double lat2, double lon2) {
        return calculateDistance(lat1, lon1, lat2, lon2, EARTH_RADIUS_KM);
    }

    /**
     * Calculate distance between two points in miles
     */
    public double distanceInMiles(double lat1, double lon1, double lat2, double lon2) {
        return calculateDistance(lat1, lon1, lat2, lon2, EARTH_RADIUS_MILES);
    }

    // Haversine formula
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2, double radius) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return radius * c;
    }
}
