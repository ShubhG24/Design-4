class Twitter {
    
    class Tweet{
        int id;
        int CreatedAt;
        public Tweet(int id, int time){
            this.id = id;
            this.CreatedAt = time;
        }    
    }
    
    HashMap<Integer,HashSet<Integer>> users;
    HashMap<Integer,List<Tweet>> tweets;
    int time;
    
    public Twitter() {
    users = new HashMap<>();
    tweets = new HashMap<>();
    }
    //O(1)
    public void postTweet(int userId, int tweetId) {
        if(!tweets.containsKey(userId)){
            tweets.put(userId, new ArrayList<>());
        }
        tweets.get(userId).add(new Tweet(tweetId,time++));
    }
    // O(mnlogk)
    public List<Integer> getNewsFeed(int userId) {
        follow(userId,userId);
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a,b)-> a.CreatedAt-b.CreatedAt);
        HashSet<Integer> followedPpl = users.get(userId);
        for(int user : followedPpl){
            List<Tweet> alltweets = tweets.get(user);
            if(alltweets!=null){
                for(Tweet tw : alltweets){
                    pq.add(tw);
                    if(pq.size()>10){
                        pq.poll();
                    }
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(0,pq.poll().id);
        }
        return result;
    }
    // O(1)
    public void follow(int followerId, int followeeId) {
        if(!users.containsKey(followerId)){
            users.put(followerId, new HashSet<>());
        }
        users.get(followerId).add(followeeId);
    }
    // O(1)
    public void unfollow(int followerId, int followeeId) {
        if(users.containsKey(followerId) && followerId!=followeeId){
            users.get(followerId).remove(followeeId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */