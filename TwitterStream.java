import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStream {
	public static void main(String[] a) {

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey("CONSUMER KEY");
		cb.setOAuthConsumerSecret("CONSUMER SECRET");
		cb.setOAuthAccessToken("OAUTH ACCESS TOKEN");
		cb.setOAuthAccessTokenSecret("OAUTH ACCESS TOKEN SECRET");
		cb.setHttpProxyHost("proxy.company.host");
		cb.setHttpProxyPort(1234);
		cb.setHttpProxyUser("proxyuser");
		cb.setHttpProxyPassword("ProxyUs3rPa$$w0rD");

		Twitter twitter = new TwitterFactory(cb.build()).getInstance();

		while (true) {
			try {
				List<Status> statuses = twitter.getHomeTimeline();
				System.out.println("Showing home timeline.");
				for (Status status : statuses) {
					System.out.println(status.getUser().getName() + ":" + status.getText());
					System.out.println(status.getRetweetCount());
					System.out.println(status.getFavoriteCount());
					System.out.println(status.getGeoLocation() + "\n");
				}
			} catch (TwitterException te) {
				System.out.println("Exception caught :::::: " + te);
				te.printStackTrace();
			}
			System.out.println("Now Running Desired Query...");
			try {
				Query query = new Query("IPL2017");
				QueryResult result;
				int totalTweets = 0;

				do {
					result = twitter.search(query);
					List<Status> tweets = result.getTweets();
					System.out.println("Tweets extracted ::" + tweets.size());
					System.out.println("TWEETS BELOW ::");
					for (Status tweet : tweets) {
						System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
						System.out.println("Tweet Favorite Count :: " + tweet.getFavoriteCount());
						System.out.println("Retweet Count :: " + tweet.getRetweetCount() + "\n");

					}
					totalTweets = tweets.size() + totalTweets;
					System.out.println("FINAL COUNT::: " + totalTweets);
				} while ((query = result.nextQuery()) != null);
				System.exit(0);

			} catch (TwitterException e) {

				e.printStackTrace();
			}
		}

	}
}
