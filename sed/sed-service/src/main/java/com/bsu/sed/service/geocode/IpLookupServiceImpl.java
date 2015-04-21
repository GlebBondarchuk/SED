package com.bsu.sed.service.geocode;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @author gbondarchuk
 */
@Service
public class IpLookupServiceImpl implements IpLookupService {

    private static final String PATH_TO_IP_DATABASE_FILE = "/geobase/GeoLiteCity.dat";

    @Override
    public String getCountry(String ip) {
        File file = new File("temp.dat");
        try (InputStream inputStream = getClass().getResourceAsStream(PATH_TO_IP_DATABASE_FILE);
             OutputStream outputStream = new FileOutputStream(file)) {

            IOUtils.copy(inputStream, outputStream);

            LookupService lookup = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);

            Location locationServices = lookup.getLocation(ip);
            if (locationServices == null) {
                return "";
            }
            return locationServices.countryName;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(file.exists()) {
                file.delete();
            }
        }
        return "";
    }
}
