package org.pickup.backend.server.components;

import org.pickup.backend.server.models.*;
import org.pickup.backend.server.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;

import java.util.Locale;

@Profile("dev")// Run when flagged in dev mode
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
    EmailSignupRepository emailSignupRepository;

    public DataLoader () {}
    @Override
    public void run(ApplicationArguments args) {
        eventCommentRepository.deleteAll();
        litterRepository.deleteAll();
        litterTypeRepository.deleteAll();
        userRepository.deleteAll();
        eventRepository.deleteAll();
        communityRepository.deleteAll();

        Community test1 = new Community(
                "Scouts",
                "1st Somewhere scout group",
                false,
                "img_her_link_1",
                "img_logo_link_1");
        communityRepository.save(test1);
        Community test2 = new Community(
                "Not Scouts",
                "Somewhere else scout group",
                true,
                "img_her_link_1",
                "img_logo_link_1"
        );
        communityRepository.save(test2);
        Community test3 = new Community(
                "Definitely Not Scouts",
                "Somewhere else scout group",
                false,
                "img_her_link_1",
                "img_logo_link_1"
        );
        communityRepository.save(test3);


        User user1 = new User(
                test1.getId(),
                "user_name_1",
                "user1@gg.com",
                "img_link",
                false);
        User user2 = new User(
                test1.getId(),
                "user_name_2",
                "user2@gg.com",
                "img_link",
                false);

        userRepository.save(user1);
        userRepository.save(user2);

        Event event1 = new Event(
                test1.getId(),
                "Beach pickup",
                "Beach pickup description",
                "some texty text",
                "some location",
                "some datetime",
                "some before link");

        Event event2 = new Event(
                test1.getId(),
                "Forest pickup",
                "Forest pickup description",
                "some text text",
                "some location",
                "some datetime",
                "some before link");
        event2.setImgAfterLink("some after link");

        eventRepository.save(event1);
        eventRepository.save(event2);

        LitterType can = new LitterType("Can");

        litterTypeRepository.save(can);

        Litter litter1 = new Litter(
                test1,
                event1,
                user1,
                can,
                "2023-02-27 15:15:30"
        );

        Litter litter2 = new Litter(
                test1,
                null,
                user2,
                can,
                "2023-02-27 15:20:10"
        );
        litterRepository.save(litter1);
        litterRepository.save(litter2);

        EventComment comment1 = new EventComment(
                event1.getId(),
                user1.getId(),
                "2023-02-27 15:34:12",
                "first"
        );
        eventCommentRepository.save(comment1);

        EmailSignup signup1 = new EmailSignup(
                "kelsiesmurphy@gmail.com",
                "2023-02-27 15:42:20"
        );
        emailSignupRepository.save(signup1);
    }
}
