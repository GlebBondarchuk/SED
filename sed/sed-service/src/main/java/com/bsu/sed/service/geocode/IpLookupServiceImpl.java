package com.bsu.sed.service.geocode;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * @author gbondarchuk
 */
@Service
public class IpLookupServiceImpl implements IpLookupService {
    @Override
    public String getCountry(String ip) {
        try {
            File file = new File(
                    "E:\\Diplom\\Software Engineering Department\\trunk\\sed\\sed-service\\src\\main\\resources\\geobase\\GeoLiteCity.dat");

            LookupService lookup = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);

            Location locationServices = lookup.getLocation(ip);
            if(locationServices == null) {
                return "";
            }
            return locationServices.countryName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
