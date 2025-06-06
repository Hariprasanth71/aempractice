package com.mysite.core.models;

import com.google.gson.Gson;
import org.apache.http.HttpConnection;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.osgi.resource.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ExternalDataModel {

    private String id;
    private String name;
    private String username;
    private String email;
    private String number;
    private String website;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private String lat;
    private String lng;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public String getWebsite() {
        return website;
    }

    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    @PostConstruct
    public void init(){
        String apiUrl = "https://jsonplaceholder.typicode.com/users";
        fetchExternalData(apiUrl);
    }

    private void fetchExternalData(String apiUrl){
        try{
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode()!= 200 ){
                throw new RuntimeException("Failed : HTTP Error Code " + connection.getResponseCode());
            }

            Gson gson = new Gson();
            ExternalData data = gson.fromJson(new InputStreamReader(connection.getInputStream()),ExternalData.class);
            if(data.getId() != null){
                this.id = data.getId();
            }
            if(data.getName() != null){
                this.name = data.getName();
            }
            if(data.getUsername() != null) {
                this.username = data.getUsername();
            }
            this.email = data.getEmail();
            this.number = data.getPhone();
            this.website = data.getWebsite();
            if (data.getAddress() != null){
                this.street = data.getAddress().getStreet();
                this.city = data.getAddress().getCity();
                this.suite = data.getAddress().getSuite();
                this.zipcode = data.getAddress().getZipcode();
                if( data.getAddress().getGeo() != null){
                    this.lat = data.getAddress().getGeo().getLat();
                    this.lng = data.getAddress().getGeo().getlong();
                }
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private class ExternalData {
        private String id;
        private String name;
        private String username;
        private String email;
        private String phone;
        private String website;
        private Address address;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public String getWebsite() {
            return website;
        }

        public Address getAddress() {
            return address;
        }

        public class Address {
            private String street;
            private String suite;
            private String city;
            private String zipcode;
            private Geo geo;

            public String getStreet() {
                return street;
            }

            public String getSuite() {
                return suite;
            }

            public String getCity() {
                return city;
            }

            public String getZipcode() {
                return zipcode;
            }

            public Geo getGeo() {
                return geo;
            }

            public class Geo {
                private String lat;
                private String lng;

                public String getlong() {
                    return lng;
                }

                public String getLat(){
                    return lat;
                }
            }

        }
    }
}
