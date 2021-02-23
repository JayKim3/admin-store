package com.study.adminstore.service;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import com.maxmind.geoip.timeZone;
import com.study.adminstore.AdminStoreApplicationTests;
import org.junit.Test;

import java.io.IOException;


public class IpTest extends AdminStoreApplicationTests {

    @Test
    public void IpCountryTest() {
        try {
        final LookupService cl = new LookupService("/Users/jaykim/GeoLiteCity.dat", LookupService.GEOIP_MEMORY_CACHE);
        // Location l1 = cl.getLocation("xxx.xxx.xxx.xxx"); // IP 입력
        final Location l2 = cl.getLocation("196.50.28.0"); // IP 입력
        System.out.println("countryCode: " + l2.countryCode);
        System.out.println("countryName: " + l2.countryName);
        System.out.println("region: " + l2.region);
        System.out.println("regionName: " + regionName.regionNameByCode(l2.countryCode, l2.region));
        System.out.println("city: " + l2.city); System.out.println("postalCode: " + l2.postalCode);
        System.out.println("latitude: " + l2.latitude); System.out.println("longitude: " + l2.longitude);
//        System.out.println("distance: " + l2.distance(l1));
//        System.out.println("distance: " + l1.distance(l2));
        System.out.println("metro code: " + l2.metro_code);
        System.out.println("area code: " + l2.area_code);
        System.out.println("timezone: " + timeZone.timeZoneByCountryAndRegion(l2.countryCode, l2.region)); cl.close();
        }
        catch (final IOException e) {
            System.out.println("IO Exception"); e.printStackTrace();
            }
        }

}
