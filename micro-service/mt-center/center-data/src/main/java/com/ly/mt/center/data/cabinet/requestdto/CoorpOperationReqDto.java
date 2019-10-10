package com.ly.mt.center.data.cabinet.requestdto;

import com.ly.mt.center.data.basic.entity.BasicUserRole;
import com.ly.mt.center.data.cabinet.entity.*;

public class CoorpOperationReqDto {
    private CabinetInfo cabinetInfo;
    private CabinetStore cabinetStore;
    private CabinetStoreProperty cabinetStoreProperty;
    private CabinetContract cabinetContract;
    private CabinetBussinessCoorperation cabinetBussinessCoorperation;
    private BasicUserRole basicUserRole;

    public CabinetInfo getCabinetInfo() {
        return cabinetInfo;
    }

    public void setCabinetInfo(CabinetInfo cabinetInfo) {
        this.cabinetInfo = cabinetInfo;
    }

    public CabinetStore getCabinetStore() {
        return cabinetStore;
    }

    public void setCabinetStore(CabinetStore cabinetStore) {
        this.cabinetStore = cabinetStore;
    }

    public CabinetStoreProperty getCabinetStoreProperty() {
        return cabinetStoreProperty;
    }

    public void setCabinetStoreProperty(CabinetStoreProperty cabinetStoreProperty) {
        this.cabinetStoreProperty = cabinetStoreProperty;
    }

    public CabinetContract getCabinetContract() {
        return cabinetContract;
    }

    public void setCabinetContract(CabinetContract cabinetContract) {
        this.cabinetContract = cabinetContract;
    }

    public CabinetBussinessCoorperation getCabinetBussinessCoorperation() {
        return cabinetBussinessCoorperation;
    }

    public void setCabinetBussinessCoorperation(CabinetBussinessCoorperation cabinetBussinessCoorperation) {
        this.cabinetBussinessCoorperation = cabinetBussinessCoorperation;
    }

    public BasicUserRole getBasicUserRole() {
        return basicUserRole;
    }

    public void setBasicUserRole(BasicUserRole basicUserRole) {
        this.basicUserRole = basicUserRole;
    }
}
