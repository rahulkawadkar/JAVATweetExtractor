import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.security.auth.login.Configuration;

import twitter4j.Paging;
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
		cb.setOAuthAccessTokenSecret("TOKEN SECRET");
		cb.setHttpProxyHost("PROXY HOST");
		cb.setHttpProxyPort(1234);
		cb.setHttpProxyUser("useruser");
		cb.setHttpProxyPassword("Us3rPa$$w0rD");

		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		System.out.println("TwitterFactory initialized!!!");

		/*int pageno = 1;
		String user = "cnn";
		List<Object> statuses = new ArrayList<Object>();*/

		while (true) {

			try {
				Query query = new Query("Morning");
				QueryResult result;
				//FileWriter fw = new FileWriter(file, true);
				//BufferedWriter bw = new BufferedWriter(fw);
				do {
					result = twitter.search(query);
					List<Status> tweets = result.getTweets();
					for (Status tweet : tweets) {
						System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
						System.out.println("Tweet Favorite Count :: " + tweet.getFavoriteCount());
						System.out.println("Retweet Count :: " + tweet.getRetweetCount() + "\n");
					}
				} while ((query = result.nextQuery()) != null);
				System.exit(0);
				//bw.close();

				/*
				 * int size = statuses.size(); Paging page = new
				 * Paging(pageno++, 100);
				 * statuses.addAll(twitter.getUserTimeline(user, page)); if
				 * (statuses.size() == size) break;
				 */
			} catch (TwitterException e) {

				e.printStackTrace();
			}
		}

		// System.out.println("Total: " + statuses.size());
	}
}
