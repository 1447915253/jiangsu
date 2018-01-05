package com.rmkj.microcap.modules.corps.bean;

/**
 * Created by 123 on 2016/11/22.
 */
public class Percent3 {
    private double percentBuBing = 0.002;
    private double percentQiBing = 0.005;
    private double percentPaoBing = 0.011;

    public Percent3() {
    }

    public Percent3(double percentBuBing, double percentQiBing, double percentPaoBing) {
        this.percentBuBing = percentBuBing;
        this.percentQiBing = percentQiBing;
        this.percentPaoBing = percentPaoBing;
    }
    public Percent3(double percentBuBing, double percentQiBing) {
        this.percentBuBing = percentBuBing;
        this.percentQiBing = percentQiBing;
    }


    public double getPercentBuBing() {
        return percentBuBing;
    }

    public void setPercentBuBing(double percentBuBing) {
        this.percentBuBing = percentBuBing;
    }

    public double getPercentQiBing() {
        return percentQiBing;
    }

    public void setPercentQiBing(double percentQiBing) {
        this.percentQiBing = percentQiBing;
    }

    public double getPercentPaoBing() {
        return percentPaoBing;
    }

    public void setPercentPaoBing(double percentPaoBing) {
        this.percentPaoBing = percentPaoBing;
    }
}
