package org.pickup.backend.server.components;

import org.pickup.backend.server.models.*;
import org.pickup.backend.server.repositories.*;
import org.pickup.backend.server.utils.RawJsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.*;

@Profile("dev")// Run when flagged in dev mode
@Component
public class DataLoaderNew implements ApplicationRunner {

    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    LitterTypeRepository litterTypeRepository;
    @Autowired
    LitterRepository litterRepository;
    @Autowired
    EventCommentRepository eventCommentRepository;
    @Autowired
    EmailSignupRepository emailSignupRepository;


    public HashMap<String, String> getUser() {

//        Pulls from API url, saves for access
        String url = "https://randomuser.me/api/";
        RestTemplate restTemplate = new RestTemplate();

        Object userAPIData = restTemplate.getForObject(url, Object.class);

        HashMap<String, List> fullApiResult = (HashMap<String, List>) userAPIData;
        HashMap<String, HashMap<String, String>> resultForHashmapItems = (HashMap<String, HashMap<String, String>>) fullApiResult.get("results").get(0);

//        Get Name
        HashMap<String, String> nameHash = resultForHashmapItems.get("name");
        String firstName = nameHash.get("first");
        String lastName = nameHash.get("last");

//        Get Email
        HashMap<String, String> resultForEmailItems = (HashMap<String, String>) fullApiResult.get("results").get(0);
        String email = resultForEmailItems.get("email");

//        Get Picture
        HashMap<String, String> pictureHash = resultForHashmapItems.get("picture");
        String picture = pictureHash.get("medium");

//        Save as Hashmap for returning to app
        HashMap<String, String> userResult = new HashMap<>();
        userResult.put("username", firstName + "_" + lastName);
        userResult.put("email", email);
        userResult.put("picture", picture);

        return userResult;
    }


    public HashMap<String, Double> getCoords() {

//        Pulls from API url, saves for access
        String url = "https://random-data-api.com/api/v2/addresses";
        RestTemplate restTemplate = new RestTemplate();

        Object userAPIData = restTemplate.getForObject(url, Object.class);

//        Get latitude and longitude
        HashMap<String, Double> fullApiResult = (HashMap<String, Double>) userAPIData;
        Double latVal = fullApiResult.get("latitude");
        Double longVal = fullApiResult.get("longitude");

//        Save as Hashmap for returning to app
        HashMap<String, Double> coordsResult = new HashMap<>();
        coordsResult.put("lat", latVal);
        coordsResult.put("long", longVal);

        return coordsResult;
    }


    public DataLoaderNew () {}
    @Override
    public void run(ApplicationArguments args) {

        Community community1 = new Community(
                "30th Glasgow Scout Group",
                "Volunteering’s not just about giving back – it goes both ways. It improves your wellbeing, gives you skills for the future, and helps you make new friends (and memories).",
                false,
                "https://pbs.twimg.com/profile_banners/523418837/1664179789/1500x500",
                "https://pbs.twimg.com/profile_images/1574310253553979394/zyz6JiBt_400x400.jpg");
        communityRepository.save(community1);

        Community community2 = new Community(
                "Ashton Primary School",
                "Our school cares about the environment, and we run a school litter picking programme to clean up our local area!",
                true,
                "https://images.pexels.com/photos/301926/pexels-photo-301926.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                "https://www.shutterstock.com/image-vector/vector-logo-school-260nw-427910128.jpg"
        );
        communityRepository.save(community2);

        Community community3 = new Community(
                "North Coast Beach Volunteers",
                "We clean up our beautiful beach once a month. Come join us to keep our beach clean!",
                false,
                "https://images.pexels.com/photos/1045113/pexels-photo-1045113.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                "https://images.pexels.com/photos/1000445/pexels-photo-1000445.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
        );
        communityRepository.save(community3);

        Community community4 = new Community(
                "Plickton Neighbourhood Watch",
                "Somewhere else scout group",
                false,
                "https://images.pexels.com/photos/280221/pexels-photo-280221.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                "https://www.ourwatch.org.uk/sites/default/files/documents/2020-02/NHW_Roundel_Logo-Badge_CMYK_Final.jpg"
        );
        communityRepository.save(community4);


        List<Community> communities = communityRepository.findAll();

        ArrayList<String> locationValues = new ArrayList<>();
        locationValues.add("Beach");
        locationValues.add("School");
        locationValues.add("River");
        locationValues.add("City");
        locationValues.add("Village");
        locationValues.add("Town");
        locationValues.add("Coast");

        ArrayList<String> cleanTypeValues = new ArrayList<>();
        cleanTypeValues.add("clean");
        cleanTypeValues.add("litter pick");
        cleanTypeValues.add("pickup");
        cleanTypeValues.add("plicking");
        cleanTypeValues.add("tidy");
        cleanTypeValues.add("volunteering");
        cleanTypeValues.add("spring clean");
        cleanTypeValues.add("collection");

        List<String> yearOptions = Arrays.asList("2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026");
        List<String> monthOptions = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        List<String> dayOptions = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30");


        for (Community community : communities) {
            for (int i = 0; i < community.getId() * 30; i++) {

                HashMap<String, String> apiUserData = getUser();

                User user = new User(
                        community.getId(),
                        apiUserData.get("username"),
                        apiUserData.get("email"),
                        apiUserData.get("picture"),
                        false);
                userRepository.save(user);
            }

            for (int i = 0; i < 12; i++) {
                int locationIndex1 = (int) (Math.random() * locationValues.size());
                int cleanTypeIndex1 = (int) (Math.random() * locationValues.size());

                String eventName1 = locationValues.get(locationIndex1) + " " + cleanTypeValues.get(cleanTypeIndex1);

                int yearIndex1 = (int) (Math.random() * yearOptions.size());
                int monthIndex1 = (int) (Math.random() * monthOptions.size());
                int dayIndex1 = (int) (Math.random() * dayOptions.size());

                String date1 = yearOptions.get(yearIndex1) + "-" + monthOptions.get(monthIndex1) + "-" + dayOptions.get(dayIndex1);

                HashMap<String, Double> apiCoordsData = getCoords();

                String textBodyJson = "{\"1\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, \", \"2\":\"sed do eiusmod tempor incididunt ut labore et dolore magna aliqua\"}";

                Event event = new Event(
                        community.getId(),
                        eventName1,
                        eventName1 + " description",
                        textBodyJson,
                        apiCoordsData.get("lat") + ", " + apiCoordsData.get("long"),
                        date1 + " 09:00:00",
                        date1 + " 15:00:00",
                        "https://images.unsplash.com/photo-1588803103006-2822e4b2619d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80"
                );
                if (i % 2 == 0) {
                    event.setImgAfterLink("https://images.unsplash.com/photo-1507525428034-b723cf961d3e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1746&q=80");
                }
                eventRepository.save(event);
            }
        }
        System.out.println("Data Load Complete");

//            HashMap<String, Double> apiCoordsData = getCoords();
//
//            int locationIndex1 = (int)(Math.random() * locationValues.size());
//            int cleanTypeIndex1 = (int)(Math.random() * locationValues.size());
//
//            String eventName1 = locationValues.get(locationIndex1) + " " + cleanTypeValues.get(cleanTypeIndex1);
//
//            int yearIndex1 = (int)(Math.random() * yearOptions.size());
//            int monthIndex1 = (int)(Math.random() * monthOptions.size());
//            int dayIndex1 = (int)(Math.random() * dayOptions.size());
//
//            String date1 = yearOptions.get(yearIndex1)+ "-" + monthOptions.get(monthIndex1) + "-" + dayOptions.get(dayIndex1);
//
//            Event event = new Event(
//                    community.getId(),
//                    eventName1,
//                    eventName1 + " description",
//                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
//                    apiCoordsData.get("lat") + ", " + apiCoordsData.get("long"),
//                    date1 + " 09:00:00",
//                    date1 + " 15:00:00",
//                    "https://images.unsplash.com/photo-1588803103006-2822e4b2619d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80"
//            );
//            eventRepository.save(event);
//
//            int locationIndex2 = (int)(Math.random() * locationValues.size());
//            int cleanTypeIndex2 = (int)(Math.random() * locationValues.size());
//
//            String eventName2 = locationValues.get(locationIndex2) + " " + cleanTypeValues.get(cleanTypeIndex2);
//
//            int yearIndex2 = (int)(Math.random() * yearOptions.size());
//            int monthIndex2 = (int)(Math.random() * monthOptions.size());
//            int dayIndex2 = (int)(Math.random() * dayOptions.size());
//
//            String date2 = yearOptions.get(yearIndex2)+ "-" + monthOptions.get(monthIndex2) + "-" + dayOptions.get(dayIndex2);
//
//
//            Event event2 = new Event(
//                    community.getId(),
//                    eventName2,
//                    eventName2 + " description",
//                    "Lorem ipsum dolor sit amet, consectetur adipiscing ",
//                    apiCoordsData.get("lat") + ", " + apiCoordsData.get("long"),
//                    date2 + " 12:00:00",
//                    "https://images.unsplash.com/photo-1588803103006-2822e4b2619d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80");
//            event2.setImgAfterLink("https://images.unsplash.com/photo-1507525428034-b723cf961d3e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1746&q=80");
//            eventRepository.save(event2);
//        }
    }
}