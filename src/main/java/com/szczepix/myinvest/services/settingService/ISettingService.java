package com.szczepix.myinvest.services.settingService;

import com.szczepix.myinvest.models.SettingModel;

public interface ISettingService {

    void init();
    SettingModel getSettings();
}
