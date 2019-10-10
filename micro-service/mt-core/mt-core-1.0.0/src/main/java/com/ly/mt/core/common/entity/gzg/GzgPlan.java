package com.ly.mt.core.common.entity.gzg;

import java.io.Serializable;

public class GzgPlan implements Serializable {
    private String id;

    private String planName;

    private String one;

    private String two;

    private String three;

    private String four;

    private String five;

    private String six;

    private String seven;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one == null ? null : one.trim();
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two == null ? null : two.trim();
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three == null ? null : three.trim();
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four == null ? null : four.trim();
    }

    public String getFive() {
        return five;
    }

    public void setFive(String five) {
        this.five = five == null ? null : five.trim();
    }

    public String getSix() {
        return six;
    }

    public void setSix(String six) {
        this.six = six == null ? null : six.trim();
    }

    public String getSeven() {
        return seven;
    }

    public void setSeven(String seven) {
        this.seven = seven == null ? null : seven.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", planName=").append(planName);
        sb.append(", one=").append(one);
        sb.append(", two=").append(two);
        sb.append(", three=").append(three);
        sb.append(", four=").append(four);
        sb.append(", five=").append(five);
        sb.append(", six=").append(six);
        sb.append(", seven=").append(seven);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}