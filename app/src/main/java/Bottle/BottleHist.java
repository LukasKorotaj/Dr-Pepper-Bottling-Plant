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

    public void setBottle_id(int bottle_id) {
        this.bottle_id = bottle_id;
    }

    public boolean isHealthy() {
        return healthy;
    }

    public void setIs_good(boolean healthy) {
        this.healthy = healthy;
    }

    public HashMap<CurrentMachine, Float> getStationStats() {
        return stationStats;
    }

    public void setStationStats(HashMap<CurrentMachine, Float> station_stats) {
        this.stationStats = station_stats;
    }

    public BottleErrors getError() {
        return error;
    }

    public void setError(BottleErrors error) {
        this.error = error;
    }
}
