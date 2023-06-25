import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Talk {
    private final String title;
    private final int duration; // in minutes

    public Talk(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }
}

class Session {
    private final List<Talk> talks = new ArrayList<>();
    int remainingDuration;

    public void addTalk(Talk talk) {
        talks.add(talk);
        remainingDuration -= talk.getDuration();
    }

    public boolean hasEnoughDuration(Talk talk) {
        return remainingDuration >= talk.getDuration();
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public int getRemainingDuration() {
        return 0;
    }
}

class Track {
    public static final int MORNING_SESSION_DURATION = 180;
    public static final int LUNCH_DURATION = 60;
    // private static final int MORNING_SESSION_DURATION = 180; // 9am - 12pm
    static final int AFTERNOON_SESSION_DURATION = 240; // 1pm - 4pm
  //  private static final int LUNCH_DURATION = 60;

    private final List<Session> sessions = new ArrayList<>();

    public void addSession(Session session) {
        sessions.add(session);
    }

    public List<Session> getSessions() {
        return sessions;
    }
}

public class ConferenceScheduler {
    private static final int NETWORKING_EVENT_START_TIME = 240; // 4pm

    public static void main(String[] args) {
        // List of talks
        List<Talk> talks = new ArrayList<>();
        talks.add(new Talk("Writing Fast Tests Against Enterprise Rails", 60));
        talks.add(new Talk("Overdoing it in Python", 45));
        talks.add(new Talk("Lua for the Masses", 30));
        talks.add(new Talk("Ruby Errors from Mismatched Gem Versions", 45));
        talks.add(new Talk("Common Ruby Errors", 45));
        talks.add(new Talk("Rails for Python Developers", 5)); // lightning
        talks.add(new Talk("Communicating Over Distance", 60));
        talks.add(new Talk("Accounting-Driven Development", 45));
        talks.add(new Talk("Woah", 30));
        talks.add(new Talk("Sit Down and Write", 30));
        talks.add(new Talk("Pair Programming vs Noise", 45));
        talks.add(new Talk("Rails Magic", 60));
        talks.add(new Talk("Ruby on Rails: Why We Should Move On", 60));
        talks.add(new Talk("Clojure Ate Scala (on my project)", 45));
        talks.add(new Talk("Programming in the Boondocks of Seattle", 30));
        talks.add(new Talk("Ruby vs. Clojure for Back-End Development", 30));
        talks.add(new Talk("Ruby on Rails Legacy App Maintenance", 60));
        talks.add(new Talk("A World Without HackerNews", 30));
        talks.add(new Talk("User Interface CSS in Rails Apps", 30));

        // Sort talks in descending order of duration
        Collections.sort(talks, (t1, t2) -> Integer.compare(t2.getDuration(), t1.getDuration()));

        List<Track> tracks = scheduleConferenceTracks(talks);

        // Print the scheduled tracks and talks
        for (int i = 0; i < tracks.size(); i++) {
            Track track = tracks.get(i);
            System.out.println("Track " + (i + 1) + ":");

            List<Session> sessions = track.getSessions();
            for (int j = 0; j < sessions.size(); j++) {
                Session session = sessions.get(j);
                List<Talk> sessionTalks = session.getTalks();

                int startTime = (j == 0) ? 9 * 60 : 13 * 60; // 9am or 1pm

                for (Talk talk : sessionTalks) {
                    String formattedTime = String.format("%02d:%02d", startTime / 60, startTime % 60);
                    System.out.println(formattedTime + " " + talk.getTitle()+" "+ talk.getDuration()+"min");
                    startTime += talk.getDuration();
                }
                if (j == 0 && session.getRemainingDuration() >= Track.LUNCH_DURATION) {
                    int lunchStartTime = 12 * 60; // 12pm
                    String formattedLunchTime = String.format("%02d:%02d", lunchStartTime / 60, lunchStartTime % 60);
                    System.out.println(formattedLunchTime + " Lunch");
                    startTime += Track.LUNCH_DURATION;
                }
                else {
                    System.out.println("12:00PM Lunch ");
                }

            }

            // Add networking event
            String formattedTime = String.format("%02d:%02d", NETWORKING_EVENT_START_TIME / 60,
                    NETWORKING_EVENT_START_TIME % 60);
            System.out.println(formattedTime + " Networking Event");
        }
    }

    private static List<Track> scheduleConferenceTracks(List<Talk> talks) {
        List<Track> tracks = new ArrayList<>();
        int remainingTalks = talks.size();
        int trackIndex = 0;

        while (remainingTalks > 0) {
            Track track = new Track();
            Session morningSession = createSessionWithTalks(talks, Track.MORNING_SESSION_DURATION);
            Session afternoonSession = createSessionWithTalks(talks, Track.AFTERNOON_SESSION_DURATION);

            track.addSession(morningSession);
            track.addSession(afternoonSession);

            tracks.add(track);
            remainingTalks -= (morningSession.getTalks().size() + afternoonSession.getTalks().size());
            trackIndex++;
        }

        return tracks;
    }
    private static Session createSessionWithTalks(List<Talk> talks, int sessionDuration) {
        Session session = new Session();
        session.remainingDuration = sessionDuration;
        List<Talk> talksToRemove = new ArrayList<>();

        for (Talk talk : talks) {
            if (session.hasEnoughDuration(talk)) {
                session.addTalk(talk);
                talksToRemove.add(talk);
            }
        }

        talks.removeAll(talksToRemove); // Remove the talks

        return session;
    }
}
