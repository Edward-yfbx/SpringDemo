package com.yfbx.demo;

import com.yfbx.demo.bean.BrowserMarket;
import com.yfbx.demo.bean.TempResponse;
import com.yfbx.demo.bean.Temperature;
import com.yfbx.demo.db.DbUtil;
import com.yfbx.demo.json.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BrowserMarketCtrl {

    @RequestMapping(value = "/getBrowserMarket", method = RequestMethod.GET)
    @ResponseBody
    public String getBrowserMarket() {
        List<BrowserMarket> all = DbUtil.findAll(BrowserMarket.class);
        return JsonUtils.success("成功", all);
    }


    @RequestMapping(value = "/getAvgTemp", method = RequestMethod.GET)
    @ResponseBody
    public String getAvgTemp() {
        String sql = "SELECT DISTINCT city FROM temperature";
        List<String> cityNames = DbUtil.exeQuery(sql);
        List<Temperature> all = DbUtil.findAll(Temperature.class);

        List<List<Temperature>> data = new ArrayList<>();
        for (String cityName : cityNames) {
            List<Temperature> temps = new ArrayList<>();
            for (Temperature temp : all) {
                if (temp.getCity().equals(cityName)) {
                    temps.add(temp);
                }
            }
            data.add(temps);
        }
        return JsonUtils.success("成功", data);
    }


    @RequestMapping(value = "/getAvgTemp2", method = RequestMethod.GET)
    @ResponseBody
    public String getAvgTemp2() {
        String sql = "SELECT DISTINCT city FROM temperature";
        List<String> cityNames = DbUtil.exeQuery(sql);
        List<Temperature> all = DbUtil.findAll(Temperature.class);

        List<TempResponse> data = new ArrayList<>();
        for (String cityName : cityNames) {
            TempResponse temps = new TempResponse();
            List<Float> list = new ArrayList<>();
            for (Temperature temp : all) {
                if (temp.getCity().equals(cityName)) {
                    list.add(temp.getTemperature());
                }
            }
            temps.setName(cityName);
            temps.setData(list);
            data.add(temps);
        }
        return JsonUtils.toJson(data);
    }


}
