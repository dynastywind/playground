package org.lyndon.design;

import java.util.*;

public class FileSharing {

    private final PriorityQueue<Integer> freeIds;

    private final Map<Integer, TreeSet<Integer>> chunkOwners;

    private final Map<Integer, Set<Integer>> userChunks;

    private int nextId = 1;

    public FileSharing(int m) {
        this.freeIds = new PriorityQueue<>();
        this.chunkOwners = new HashMap<>(m);
        this.userChunks = new HashMap<>();
    }

    public int join(List<Integer> ownedChunks) {
        int id;
        if (!freeIds.isEmpty()) {
            id = freeIds.poll();
        } else {
            id = nextId++;
        }
        Set<Integer> owned = new HashSet<>();
        for (int chunk : ownedChunks) {
            chunkOwners.computeIfAbsent(chunk, k -> new TreeSet<>()).add(id);
            owned.add(chunk);
        }
        userChunks.put(id, owned);
        return id;
    }

    public void leave(int userID) {
        Set<Integer> chunks = userChunks.remove(userID);
        if (chunks == null) {
            return;
        }
        for (int chunk : chunks) {
            TreeSet<Integer> owners = chunkOwners.get(chunk);
            if (owners != null) {
                owners.remove(userID);
                if (owners.isEmpty()) {
                    chunkOwners.remove(chunk);
                }
            }
        }
        freeIds.add(userID);
    }

    public List<Integer> request(int userID, int chunkID) {
        TreeSet<Integer> owners = chunkOwners.get(chunkID);
        if (owners == null || owners.isEmpty()) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>(owners);
        Set<Integer> mine = userChunks.get(userID);
        if (mine != null) {
            mine.add(chunkID);
            owners.add(userID);
        }
        return result;
    }

}
