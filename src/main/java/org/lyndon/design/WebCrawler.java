package org.lyndon.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LC1242
 */
public class WebCrawler {

    private interface HtmlParser {
        List<String> getUrls(String url);
    }

    private static final Pattern hostPattern = Pattern.compile("https?://([^/]+)");

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        Set<String> visited = ConcurrentHashMap.newKeySet();
        visited.add(startUrl);
        final String host = getHost(startUrl);
        int pending = 1;
        ExecutorService pool = Executors.newFixedThreadPool(Math.min(64, Runtime.getRuntime().availableProcessors() * 8));
        CompletionService<List<String>> service = new ExecutorCompletionService<>(pool);
        service.submit(() -> htmlParser.getUrls(startUrl));
        try {
            while (pending > 0) {
                List<String> children = service.take().get();
                pending--;
                for (String child : children) {
                    if (!host.equals(getHost(child)) || !visited.add(child)) {
                        continue;
                    }
                    service.submit(() -> htmlParser.getUrls(child));
                    pending++;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            pool.shutdown();
        }
        return new ArrayList<>(visited);
    }

    private String getHost(String url) {
        Matcher matcher = hostPattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public List<String> crawl2(String startUrl, HtmlParser htmlParser) {
        final String host = getHost(startUrl);
        Set<String> visited = ConcurrentHashMap.newKeySet();
        visited.add(startUrl);
        try (ExecutorService service = Executors.newVirtualThreadPerTaskExecutor()) {
            dfs(startUrl, host, htmlParser, visited, service);
        }
        return new ArrayList<>(visited);
    }

    private void dfs(String url, String host, HtmlParser htmlParser, Set<String> visited, ExecutorService service) {
        List<Future<?>> futures = new ArrayList<>();
        for (String child : htmlParser.getUrls(url)) {
            if (!host.equals(getHost(child)) || !visited.add(child)) {
                continue;
            }
            futures.add(service.submit(() -> dfs(child, host, htmlParser, visited, service)));
        }
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        var webCrawler = new WebCrawler();
        System.out.println(webCrawler.getHost("https://www.baidu.com/"));
    }

}
