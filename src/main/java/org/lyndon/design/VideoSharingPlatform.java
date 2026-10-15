package org.lyndon.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class VideoSharingPlatform {

    private static class Video {
        String content;
        int views;
        int likes;
        int dislikes;

        Video(String content) {
            this.content = content;
        }
    }

    private final Map<Integer, Video> videos;
    private final TreeSet<Integer> reclaimedIds;
    private int seq = 0;

    public VideoSharingPlatform() {
        this.videos = new HashMap<>();
        this.reclaimedIds = new TreeSet<>();
    }

    public int upload(String video) {
        int id;
        if (!reclaimedIds.isEmpty()) {
            id = reclaimedIds.removeFirst();
        } else {
            id = seq++;
        }
        videos.put(id, new Video(video));
        return id;
    }

    public void remove(int videoId) {
        Video victim = videos.remove(videoId);
        if (victim != null) {
            reclaimedIds.add(videoId);
        }
    }

    public String watch(int videoId, int startMinute, int endMinute) {
        if (!videos.containsKey(videoId)) {
            return "-1";
        }
        Video video = videos.get(videoId);
        video.views++;
        return video.content.substring(startMinute, Math.min(endMinute + 1, video.content.length()));
    }

    public void like(int videoId) {
        if (!videos.containsKey(videoId)) {
            return;
        }
        videos.get(videoId).likes++;
    }

    public void dislike(int videoId) {
        if (!videos.containsKey(videoId)) {
            return;
        }
        videos.get(videoId).dislikes++;
    }

    public int[] getLikesAndDislikes(int videoId) {
        if (!videos.containsKey(videoId)) {
            return new int[]{-1};
        }
        Video video = videos.get(videoId);
        return new int[]{video.likes, video.dislikes};
    }

    public int getViews(int videoId) {
        if (!videos.containsKey(videoId)) {
            return -1;
        }
        return videos.get(videoId).views;
    }

}
