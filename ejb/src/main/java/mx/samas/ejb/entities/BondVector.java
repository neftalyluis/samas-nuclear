/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author neftaly
 */
@Entity
public class BondVector extends AssetVector implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private Double dirtyPrice;
    private Double yield;
    private Double couponRate;
    /**
    * "Spread" significa sobretasa versus 
    * tasa de referencia en bonos revisables;
    * para bonos tasa fija es = 0.0 . 
    */
    private Double spread;
    private Double amountOutstanding;
    private String gradeMoodys;
    private String gradeSP;
    private String gradeHR;
    private String gradeFitch;
    private Double faceValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BondVector)) {
            return false;
        }
        BondVector other = (BondVector) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.BondVector[ id=" + id + " ]";
    }


    /**
     * @return the gradeMoodys
     */
    public String getGradeMoodys() {
        return gradeMoodys;
    }

    /**
     * @param gradeMoodys the gradeMoodys to set
     */
    public void setGradeMoodys(String gradeMoodys) {
        this.gradeMoodys = gradeMoodys;
    }

    /**
     * @return the gradeSP
     */
    public String getGradeSP() {
        return gradeSP;
    }

    /**
     * @param gradeSP the gradeSP to set
     */
    public void setGradeSP(String gradeSP) {
        this.gradeSP = gradeSP;
    }

    /**
     * @return the gradeHR
     */
    public String getGradeHR() {
        return gradeHR;
    }

    /**
     * @param gradeHR the gradeHR to set
     */
    public void setGradeHR(String gradeHR) {
        this.gradeHR = gradeHR;
    }

    /**
     * @return the gradeFitch
     */
    public String getGradeFitch() {
        return gradeFitch;
    }

    /**
     * @param gradeFitch the gradeFitch to set
     */
    public void setGradeFitch(String gradeFitch) {
        this.gradeFitch = gradeFitch;
    }

    /**
     * @return the dirtyPrice
     */
    public Double getDirtyPrice() {
        return dirtyPrice;
    }

    /**
     * @param dirtyPrice the dirtyPrice to set
     */
    public void setDirtyPrice(Double dirtyPrice) {
        this.dirtyPrice = dirtyPrice;
    }

    /**
     * @return the yield
     */
    public Double getYield() {
        return yield;
    }

    /**
     * @param yield the yield to set
     */
    public void setYield(Double yield) {
        this.yield = yield;
    }

    /**
     * @return the couponRate
     */
    public Double getCouponRate() {
        return couponRate;
    }

    /**
     * @param couponRate the couponRate to set
     */
    public void setCouponRate(Double couponRate) {
        this.couponRate = couponRate;
    }

    /**
     * @return the spread
     */
    public Double getSpread() {
        return spread;
    }

    /**
     * @param spread the spread to set
     */
    public void setSpread(Double spread) {
        this.spread = spread;
    }

    /**
     * @return the amountOutstanding
     */
    public Double getAmountOutstanding() {
        return amountOutstanding;
    }

    /**
     * @param amountOutstanding the amountOutstanding to set
     */
    public void setAmountOutstanding(Double amountOutstanding) {
        this.amountOutstanding = amountOutstanding;
    }

    /**
     * @return the faceValue
     */
    public Double getFaceValue() {
        return faceValue;
    }

    /**
     * @param faceValue the faceValue to set
     */
    public void setFaceValue(Double faceValue) {
        this.faceValue = faceValue;
    }



}
