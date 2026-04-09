
package week2;

import java.util.HashMap;

class TokenBucket {

    int tokens;
    int maxTokens;
    long lastRefillTime;
    int refillRate;

    public TokenBucket(int maxTokens, int refillRate) {
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
        this.tokens = maxTokens;
        this.lastRefillTime = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {

        refill();

        if (tokens > 0) {
            tokens--;
            return true;
        }

        return false;
    }

    private void refill() {

        long now = System.currentTimeMillis();
        long seconds = (now - lastRefillTime) / 1000;

        if (seconds > 0) {
            int refillTokens = (int) (seconds * refillRate);
            tokens = Math.min(maxTokens, tokens + refillTokens);
            lastRefillTime = now;
        }
    }

    public int getRemainingTokens() {
        refill();
        return tokens;
    }
}

class RateLimiter {

    private HashMap<String, TokenBucket> clients = new HashMap<>();

    public boolean checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(1000, 1));

        TokenBucket bucket = clients.get(clientId);

        if (bucket.allowRequest()) {
            System.out.println("Allowed (" + bucket.getRemainingTokens() + " requests remaining)");
            return true;
        } else {
            System.out.println("Denied (Rate limit exceeded)");
            return false;
        }
    }

    public void getRateLimitStatus(String clientId) {

        TokenBucket bucket = clients.get(clientId);

        if (bucket != null) {
            int used = 1000 - bucket.getRemainingTokens();
            System.out.println("{used: " + used + ", limit: 1000}");
        } else {
            System.out.println("Client not found");
        }
    }
}

public class problem6 {

    public static void main(String[] args) {

        RateLimiter limiter = new RateLimiter();

        for (int i = 0; i < 5; i++) {
            limiter.checkRateLimit("abc123");
        }

        limiter.getRateLimitStatus("abc123");
    }
}
