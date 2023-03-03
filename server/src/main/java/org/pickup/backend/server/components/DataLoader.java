package org.pickup.backend.server.components;

import org.pickup.backend.server.models.*;
import org.pickup.backend.server.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;

@Profile("dev-data-load")// Run when flagged in dev-data-load mode
@Component
public class DataLoader implements ApplicationRunner {

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
    JdbcTemplate jdbcTemplate;


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


    public DataLoader() {}
    @Override
    public void run(ApplicationArguments args) {

        // Check if months table exists and drop if present.

        jdbcTemplate.execute("DROP TABLE IF EXISTS db_months");
        jdbcTemplate.execute("CREATE TABLE db_months (month DATE)");

        // Insert months from '2022-01-01' to 36 months from that date.
        LocalDate startDate = LocalDate.of(2021, 1, 1);
        for (int i=0; i<48; i++) {
            LocalDate iterDate = startDate.plusMonths(i);
            String sql =
                    "INSERT INTO db_months (month) values ('" +
                            iterDate.format(DateTimeFormatter.ISO_DATE) +
                            "')";
            jdbcTemplate.execute(sql);
        }
        System.out.println("Standing data loaded.");


        System.out.println("Starting main data load...");

        // Communities
        List<Community> newCommunities = new ArrayList<>();

        newCommunities.add(new Community(
                "30th Glasgow Scout Group",
                "Volunteering’s not just about giving back – it goes both ways. It improves your wellbeing, gives you skills for the future, and helps you make new friends (and memories).",
                false,
                "https://pbs.twimg.com/profile_banners/523418837/1664179789/1500x500",
                "https://pbs.twimg.com/profile_images/1574310253553979394/zyz6JiBt_400x400.jpg",
                "2021-01-08"
        ));

        newCommunities.add(new Community(
                "Ashton Primary School",
                "Our school cares about the environment, and we run a school litter picking programme to clean up our local area!",
                true,
                "https://images.pexels.com/photos/301926/pexels-photo-301926.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                "https://www.shutterstock.com/image-vector/vector-logo-school-260nw-427910128.jpg",
                "2021-07-23"
        ));

        newCommunities.add(new Community(
                "North Coast Beach Volunteers",
                "We clean up our beautiful beach once a month. Come join us to keep our beach clean!",
                false,
                "https://images.pexels.com/photos/1045113/pexels-photo-1045113.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                "https://images.pexels.com/photos/1000445/pexels-photo-1000445.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                "2022-02-02"
        ));

        newCommunities.add(new Community(
                "Plickton Neighbourhood Watch",
                "Somewhere else scout group",
                false,
                "https://images.pexels.com/photos/280221/pexels-photo-280221.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                "https://www.ourwatch.org.uk/sites/default/files/documents/2020-02/NHW_Roundel_Logo-Badge_CMYK_Final.jpg",
                "2022-10-16"
        ));

        communityRepository.saveAll(newCommunities);
        System.out.println(String.format("%s communities loaded.", newCommunities.size()));

        // Create standing options for events

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


        for (Community community : communityRepository.findAll()) {
            // Users
            List<User> users = new ArrayList<>();

            // Create admin user
            HashMap<String, String> apiAdminData = getUser();
            users.add(new User(
                    community.getId(),
                    apiAdminData.get("username"),
                    apiAdminData.get("email"),
                    apiAdminData.get("picture"),
                    true,
                    community.getCreateDate()
            ));

            // Create random number of users between 0 and 3 for each day between
            // community create date and today
            for (int i=0; i < DAYS.between(community.getCreateDateLocalDate(), LocalDate.now()) ; i++) {
                LocalDate loopDate = community.getCreateDateLocalDate().plusDays(i);

                for (int j=0; j < (int)(Math.random() * (2)); i++) {
                    HashMap<String, String> apiUserData = getUser();
                    users.add(new User(
                            community.getId(),
                            apiUserData.get("username"),
                            apiUserData.get("email"),
                            apiUserData.get("picture"),
                            false,
                            loopDate.format(DateTimeFormatter.ISO_DATE)
                    ));
                }
                userRepository.saveAll(users);
            }
            System.out.printf("%s users loaded for community %s.%n", users.size(), community.getId());


            // Events
            List<Event> events = new ArrayList<>();

            // Create one event per month from the community create date and 6 months from today
            for (int i=1;
                 i < MONTHS.between(community.getCreateDateLocalDate(), LocalDate.now().plusMonths(6));
                 i++)
            {
                LocalDate eventDate = LocalDate.of(
                        (int)community.getCreateDateLocalDate().plusMonths(i).getYear(),
                        (int)community.getCreateDateLocalDate().plusMonths(i).getMonthValue(),
                        1 + (int)(Math.random() * 28)
                );

                int locationIndex1 = (int) (Math.random() * locationValues.size());
                int cleanTypeIndex1 = (int) (Math.random() * locationValues.size());

                String eventName1 = locationValues.get(locationIndex1) + " " + cleanTypeValues.get(cleanTypeIndex1);

                HashMap<String, Double> apiCoordsData = getCoords();

                String textBodyJson = "{\"1\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, \", \"2\":\"sed do eiusmod tempor incididunt ut labore et dolore magna aliqua\"}";

                Event event = new Event(
                        community.getId(),
                        eventName1,
                        eventName1 + " description.",
                        textBodyJson,
                        apiCoordsData.get("lat") + ", " + apiCoordsData.get("long"),
                        eventDate.format(DateTimeFormatter.ISO_DATE) + "T09:00:00",
                        eventDate.format(DateTimeFormatter.ISO_DATE) + "T15:00:00",
                        "https://images.unsplash.com/photo-1588803103006-2822e4b2619d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80"
                );
                if (eventDate.isBefore(LocalDate.now())) {
                    event.setImgAfterLink("https://images.unsplash.com/photo-1507525428034-b723cf961d3e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1746&q=80");
                }
                events.add(event);
            }
            eventRepository.saveAll(events);
            System.out.println(String.format("%s events loaded for community %s.",events.size(), community.getId()));
        }

        // Event comments

        List<Event> savedEvents = eventRepository.findAll();
        Map<Integer, String> commentChoices = new HashMap<>();
        commentChoices.put(0, "First");
        commentChoices.put(1, "Second");
        commentChoices.put(2, "Third");

        List<EventComment> eventComments  = new ArrayList<>();
        for (Event event : savedEvents) {
            List<User> communityUsers = userRepository.findByCommunityId(event.getCommunityId());
            for (int i=0; i<3; i++) {
                int userIndex = (int)(Math.random() * (communityUsers.size()));
                eventComments.add(new EventComment(
                        event.getId(),
                        communityUsers.get(userIndex).getId(),
                        LocalDateTime.parse(event.getEventDateTimeStart()).minusDays(3-i).format(DateTimeFormatter.ISO_DATE_TIME),
                        commentChoices.get(i)
                ));
            }
        }
        eventCommentRepository.saveAll(eventComments);

        System.out.println(String.format("%s events have comments added.", savedEvents.size()));

        // Litter

        System.out.println("Starting litter data load...");

        LitterType litterType1 = new LitterType("General");
        litterTypeRepository.save(litterType1);

        List<User> users = userRepository.findAll();
        int userLoopCount = 0;

        for (User user : users) {
            userLoopCount++;
            if ((int)(Math.random() * (5)) % 5 == 0) {
                List<Event> events = eventRepository.findByCommunityId(user.getCommunity_id());
                for (Event e : events) {
                    if (LocalDateTime.parse(e.getEventDateTimeEnd()).isBefore(LocalDateTime.now())) {
                        List<Litter> listLitter = new ArrayList<>();
                        for (int i=0; i < 100 + (int)(Math.random() * (200 - 100) + 1); i++) {
                            Litter litter = new Litter(
                                    user.getCommunity_id(),
                                    e.getId(),
                                    user.getId(),
                                    litterType1.getId(),
                                    e.getEventDateTimeStart()
                            );
                            listLitter.add(litter);
                        }
                        litterRepository.saveAll(listLitter);
                    }
                }
            }

            if (users.size() >= 10 && userLoopCount % (users.size()/10) == 0) {
                System.out.println(String.format("Litter load is %s%s complete.", (int)((userLoopCount * 100.00f)/users.size()), "%"));
            }
        }

        System.out.println(String.format("%s items of litter loaded.", litterRepository.count()));

        System.out.println("Data Load Complete.");

    }
}