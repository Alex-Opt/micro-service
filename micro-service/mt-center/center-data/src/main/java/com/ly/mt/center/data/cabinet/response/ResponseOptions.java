package com.ly.mt.center.data.cabinet.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResponseOptions {
    private List<Option> store_type;
    private List<Option> forecast_flow;
    private List<Option> per_capita_expense;
    private List<Option> decorate_type;
    private List<Option> is_chain;
    private List<Option> use_area;
    private List<Option> is_deposit;
    private List<Option> is_sign_contract;
    private List<Map<String,Integer>> basic_area;

    public List<Option> getIs_sign_contract() {
        return is_sign_contract;
    }

    public void setIs_sign_contract(List<Option> is_sign_contract) {
        this.is_sign_contract = is_sign_contract;
    }

    public ResponseOptions(){
        use_area = new ArrayList<>();
        store_type = new ArrayList<>();
        forecast_flow = new ArrayList<>();
        per_capita_expense = new ArrayList<>();
        decorate_type = new ArrayList<>();
        is_chain = new ArrayList<>();
        is_deposit = new ArrayList<>();
        is_sign_contract = new ArrayList<>();
    }

    public List<Option> getStore_type() {
        return store_type;
    }

    public void setStore_type(List<Option> store_type) {
        this.store_type = store_type;
    }

    public List<Option> getForecast_flow() {
        return forecast_flow;
    }

    public void setForecast_flow(List<Option> forecast_flow) {
        this.forecast_flow = forecast_flow;
    }

    public List<Option> getPer_capita_expense() {
        return per_capita_expense;
    }

    public void setPer_capita_expense(List<Option> per_capita_expense) {
        this.per_capita_expense = per_capita_expense;
    }

    public List<Option> getDecorate_type() {
        return decorate_type;
    }

    public void setDecorate_type(List<Option> decorate_type) {
        this.decorate_type = decorate_type;
    }

    public List<Option> getIs_chain() {
        return is_chain;
    }

    public void setIs_chain(List<Option> is_chain) {
        this.is_chain = is_chain;
    }

    public List<Option> getUse_area() {
        return use_area;
    }

    public void setUse_area(List<Option> use_area) {
        this.use_area = use_area;
    }

    public List<Option> getIs_deposit() {
        return is_deposit;
    }

    public void setIs_deposit(List<Option> is_deposit) {
        this.is_deposit = is_deposit;
    }

    public List<Map<String, Integer>> getBasic_area() {
        return basic_area;
    }

    public void setBasic_area(List<Map<String, Integer>> basic_area) {
        this.basic_area = basic_area;
    }
}
