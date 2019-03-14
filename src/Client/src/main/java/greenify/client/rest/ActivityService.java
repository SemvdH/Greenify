package greenify.client.rest;

import greenify.common.ActivityDTO;
import greenify.common.ApplicationException;
import greenify.common.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivityService {

        Logger logger = LoggerFactory.getLogger(greenify.server.service.ActivityService.class);
        @Autowired
        UserRepository userRepository;

        /**
         * Adds the activity to the history of the user and adds the score.
         * @param name the name of the activity
         * @return a activityDTO of activity.
         */
        public ActivityDTO addActivity(String name) {

        }

}
