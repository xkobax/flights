package com.kmsb.flights.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name="state_vectors")
@Table(name="state_vectors")
public class StateVector implements Serializable {

    private static final long serialVersionUID = 4740611220099080739L;

    @Transient
    private Double longitude;
    @Transient
    private Double altitude;
    @Transient
    private Double latitude;
    @Transient
    private Double velocity;
    @Transient
    private Double heading;
    @Transient
    private Double verticalRate;
    @Transient
    private Double lastVelocityUpdate;
    @Transient
    private Double lastPositionUpdate;
    @Transient
    private Set<Integer> serials;

    //fields for mapping
    @Id
    private String icao24;
    private String originCountry;
    private String callsign;
    private boolean onGround;

    public StateVector() {
    }

    public StateVector(String icao24) {
        this.icao24 = icao24;
        this.serials = null;
    }

    /**
     * @return altitude in meters. Can be {@code null}.
     */
    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    /**
     * @return longitude in ellipsoidal coordinates (WGS-84) and degrees. Can be {@code null}
     */
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return latitude in ellipsoidal coordinates (WGS-84) and degrees. Can be {@code null}
     */
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return over ground in m/s. Can be {@code null} if information not present
     */
    public Double getVelocity() {
        return velocity;
    }

    public void setVelocity(Double velocity) {
        this.velocity = velocity;
    }

    /**
     * @return in decimal degrees (0 is north). Can be {@code null} if information not present
     */
    public Double getHeading() {
        return heading;
    }

    public void setHeading(Double heading) {
        this.heading = heading;
    }

    /**
     * @return in m/s, incline is positive, decline negative. Can be {@code null} if information not present.
     */
    public Double getVerticalRate() {
        return verticalRate;
    }

    public void setVerticalRate(Double verticalRate) {
        this.verticalRate = verticalRate;
    }

    /**
     * @return ICAO24 address of the transmitter in hex string representation.
     */
    public String getIcao24() {
        return icao24;
    }

    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }

    /**
     * @return callsign of the vehicle. Can be {@code null} if no callsign has been received.
     */
    public String getCallsign() { return callsign;	}

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    /**
     * @return true if aircraft is on ground (sends ADS-B surface position reports).
     */
    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    /**
     * @return  seconds since epoch of last velocity report. Can be {@code null} if there was no velocity report received by OpenSky within 15s before.
     */
    public Double getLastVelocityUpdate() {
        return lastVelocityUpdate;
    }

    public void setLastVelocityUpdate(Double lastVelocityUpdate) {
        this.lastVelocityUpdate = lastVelocityUpdate;
    }

    /**
     * @return seconds since epoch of last position report. Can be {@code null} if there was no position report received by OpenSky within 15s before.
     */
    public Double getLastPositionUpdate() {
        return lastPositionUpdate;
    }

    public void setLastPositionUpdate(Double lastPositionUpdate) {
        this.lastPositionUpdate = lastPositionUpdate;
    }

    public void addSerial(int serial) {
        if (this.serials == null) {
            this.serials = new HashSet<>();
        }
        this.serials.add(serial);
    }

    /**
     * @return serial numbers of sensors which received messages from the vehicle within the validity period of this state vector. {@code null} if information is not present, i.e., there was no filter for a sensor in the request
     */
    public Set<Integer> getSerials() {
        return this.serials;
    }

    /**
     * @return the country inferred through the ICAO24 address
     */
    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StateVector that = (StateVector) o;

        if (onGround != that.onGround) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (altitude != null ? !altitude.equals(that.altitude) : that.altitude != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (velocity != null ? !velocity.equals(that.velocity) : that.velocity != null) return false;
        if (heading != null ? !heading.equals(that.heading) : that.heading != null) return false;
        if (verticalRate != null ? !verticalRate.equals(that.verticalRate) : that.verticalRate != null) return false;
        if (lastVelocityUpdate != null ? !lastVelocityUpdate.equals(that.lastVelocityUpdate) : that.lastVelocityUpdate != null)
            return false;
        if (lastPositionUpdate != null ? !lastPositionUpdate.equals(that.lastPositionUpdate) : that.lastPositionUpdate != null)
            return false;
        if (serials != null ? !serials.equals(that.serials) : that.serials != null) return false;
        if (icao24 != null ? !icao24.equals(that.icao24) : that.icao24 != null) return false;
        if (originCountry != null ? !originCountry.equals(that.originCountry) : that.originCountry != null)
            return false;
        return !(callsign != null ? !callsign.equals(that.callsign) : that.callsign != null);

    }

    @Override
    public int hashCode() {
        int result = longitude != null ? longitude.hashCode() : 0;
        result = 31 * result + (altitude != null ? altitude.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (velocity != null ? velocity.hashCode() : 0);
        result = 31 * result + (heading != null ? heading.hashCode() : 0);
        result = 31 * result + (verticalRate != null ? verticalRate.hashCode() : 0);
        result = 31 * result + (lastVelocityUpdate != null ? lastVelocityUpdate.hashCode() : 0);
        result = 31 * result + (lastPositionUpdate != null ? lastPositionUpdate.hashCode() : 0);
        result = 31 * result + (serials != null ? serials.hashCode() : 0);
        result = 31 * result + (icao24 != null ? icao24.hashCode() : 0);
        result = 31 * result + (originCountry != null ? originCountry.hashCode() : 0);
        result = 31 * result + (callsign != null ? callsign.hashCode() : 0);
        result = 31 * result + (onGround ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StateVector{" +
                "longitude=" + longitude +
                ", altitude=" + altitude +
                ", latitude=" + latitude +
                ", velocity=" + velocity +
                ", heading=" + heading +
                ", verticalRate=" + verticalRate +
                ", lastVelocityUpdate=" + lastVelocityUpdate +
                ", lastPositionUpdate=" + lastPositionUpdate +
                ", serials=" + serials +
                ", icao24='" + icao24 + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", callsign='" + callsign + '\'' +
                ", onGround=" + onGround +
                '}';
    }
}