package org.lyndon.algo.schedule;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class SchedulerWithCooldown {

    @RequiredArgsConstructor
    @EqualsAndHashCode(of = {"id"})
    private static class Job {
        final String id;
        final long priority;
        final long cooldown;
        final long seq;
        long releaseAt;
    }

    private final Map<String, Job> jobs = new HashMap<>();
    private final TreeSet<Job> ready = new TreeSet<>((a, b) -> a.priority != b.priority ? Long.compare(b.priority, a.priority) : Long.compare(a.seq, b.seq));
    private final TreeSet<Job> cooldown = new TreeSet<>((a, b) -> a.releaseAt != b.releaseAt ? Long.compare(a.releaseAt, b.releaseAt) : Long.compare(a.seq, b.seq));

    private long timestamp = 0;
    private long seq = 0;

    public boolean addJob(String id, long priority, long cooldown) {
        if (jobs.containsKey(id)) {
            return false;
        }
        Job job = new Job(id, priority, cooldown, seq++);
        jobs.put(id, job);
        ready.add(job);
        return true;
    }

    public String getJob() {
        timestamp++;
        while (!cooldown.isEmpty() && cooldown.getFirst().releaseAt <= timestamp) {
            Job job = cooldown.removeFirst();
            ready.add(job);
        }
        if (!ready.isEmpty()) {
            Job job = ready.removeFirst();
            job.releaseAt = timestamp + job.cooldown + 1;
            cooldown.add(job);
            return job.id;
        }
        return "";
    }

    public boolean removeJob(String id) {
        if (!jobs.containsKey(id)) {
            return false;
        }
        Job victim = jobs.remove(id);
        ready.remove(victim);
        cooldown.remove(victim);
        return true;
    }

}
