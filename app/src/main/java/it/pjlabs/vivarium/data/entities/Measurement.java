package it.pjlabs.vivarium.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Pj on 10/12/16.
 */

public class Measurement {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date_m")
    @Expose
    private Date dateM;
    @SerializedName("value")
    @Expose
    private Double value;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The dateM
     */
    public Date getDateM() {
        return dateM;
    }

    /**
     *
     * @param dateM
     * The date_m
     */
    public void setDateM(Date dateM) {
        this.dateM = dateM;
    }

    /**
     *
     * @return
     * The value
     */
    public Double getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", dateM=" + dateM +
                ", value=" + value +
                '}';
    }
}
