package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket) {
        if (ticket.getOutTime() == null || ticket.getOutTime().before(ticket.getInTime())) {
            throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
        }

        long inTimeMillis = ticket.getInTime().getTime();
        long outTimeMillis = ticket.getOutTime().getTime();
        long durationMillis = outTimeMillis - inTimeMillis;

        // Convertir la durée en heures (arrondi supérieur)
        int durationHours = (int) Math.ceil(durationMillis / 3600000.0);

        switch (ticket.getParkingSpot().getParkingType()) {
            case CAR:
                ticket.setPrice(durationHours * Fare.CAR_RATE_PER_HOUR);
                break;
            case BIKE:
                ticket.setPrice(durationHours * Fare.BIKE_RATE_PER_HOUR);
                break;
            default:
                throw new IllegalArgumentException("Unknown Parking Type");
        }
    }
}
