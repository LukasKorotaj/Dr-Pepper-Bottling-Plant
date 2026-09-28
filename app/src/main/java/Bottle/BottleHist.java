package Bottle;

import java.util.HashMap;

import Bottle.Status.CurrentMachine;
import Bottle.Status.BottleErrors;

/**
 * BottleHist
 */
public class BottleHist {

	private int bottle_id;
	private boolean healthy;
	private HashMap<CurrentMachine, Float> stationStats;
	private BottleErrors error;

	public BottleHist(int bottle_id) {
	    this.bottle_id = bottle_id;
		this.healthy = true;
		this.stationStats = new HashMap<CurrentMachine, Float>();
	}


    public int getBottle_id() {
        return bottle_id;
    }

    public boolean isHealthy() {
        return healthy;
    }

    public HashMap<CurrentMachine, Float> getStationStats() {
        return stationStats;
    }

    public void regTimeForMachine(CurrentMachine m, float time) {
        this.stationStats.put(m, time);
    }

    public BottleErrors getError() {
        return error;
    }

    public void setError(BottleErrors error) {
        this.healthy = false;
        this.error = error;
    }
}
