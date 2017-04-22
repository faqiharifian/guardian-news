package com.arifian.udacity.guardiannews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arifian.udacity.guardiannews.adapters.NewsRecyclerAdapter;
import com.arifian.udacity.guardiannews.entities.News;
import com.arifian.udacity.guardiannews.utils.JSONUtils;

import org.json.JSONException;

import java.text.ParseException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView newsRecyclerView;
    NewsRecyclerAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String json = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":107084,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":10709,\"orderBy\":\"oldest\",\"results\":[{\"id\":\"crosswords/quick/14320\",\"type\":\"crossword\",\"sectionId\":\"crosswords\",\"sectionName\":\"Crosswords\",\"webPublicationDate\":\"2016-04-01T00:00:03Z\",\"webTitle\":\"Quick crossword No 14,320\",\"webUrl\":\"https://www.theguardian.com/crosswords/quick/14320\",\"apiUrl\":\"https://content.guardianapis.com/crosswords/quick/14320\",\"isHosted\":false},{\"id\":\"crosswords/cryptic/26847\",\"type\":\"crossword\",\"sectionId\":\"crosswords\",\"sectionName\":\"Crosswords\",\"webPublicationDate\":\"2016-04-01T00:00:03Z\",\"webTitle\":\"Cryptic crossword No 26,847\",\"webUrl\":\"https://www.theguardian.com/crosswords/cryptic/26847\",\"apiUrl\":\"https://content.guardianapis.com/crosswords/cryptic/26847\",\"isHosted\":false},{\"id\":\"us-news/2016/mar/31/san-francisco-police-racist-homophobic-text-scandal\",\"type\":\"article\",\"sectionId\":\"us-news\",\"sectionName\":\"US news\",\"webPublicationDate\":\"2016-04-01T00:01:51Z\",\"webTitle\":\"Second group of officers investigated for exchanging racist and homophobic texts\",\"webUrl\":\"https://www.theguardian.com/us-news/2016/mar/31/san-francisco-police-racist-homophobic-text-scandal\",\"apiUrl\":\"https://content.guardianapis.com/us-news/2016/mar/31/san-francisco-police-racist-homophobic-text-scandal\",\"fields\":{\"thumbnail\":\"https://media.guim.co.uk/ea14bda42c13befab9b2556f5e9c2b7ce9897365/0_0_3496_2097/500.jpg\"},\"isHosted\":false},{\"id\":\"australia-news/2016/apr/01/malcolm-turnbull-says-smaller-states-will-have-level-playing-field-in-tax-plan\",\"type\":\"article\",\"sectionId\":\"australia-news\",\"sectionName\":\"Australia news\",\"webPublicationDate\":\"2016-04-01T00:17:18Z\",\"webTitle\":\"Malcolm Turnbull says smaller states will have 'level playing field' in tax plan\",\"webUrl\":\"https://www.theguardian.com/australia-news/2016/apr/01/malcolm-turnbull-says-smaller-states-will-have-level-playing-field-in-tax-plan\",\"apiUrl\":\"https://content.guardianapis.com/australia-news/2016/apr/01/malcolm-turnbull-says-smaller-states-will-have-level-playing-field-in-tax-plan\",\"fields\":{\"thumbnail\":\"http://media.guim.co.uk/188dae2f27f24d66af99121942952159fa6e7108/125_0_3838_2303/500.jpg\"},\"isHosted\":false},{\"id\":\"technology/2016/mar/31/reddit-removes-warrant-canary-signaling-us-sought-its-user-data\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2016-04-01T00:21:05Z\",\"webTitle\":\"Reddit removes 'warrant canary', signaling US sought its user data\",\"webUrl\":\"https://www.theguardian.com/technology/2016/mar/31/reddit-removes-warrant-canary-signaling-us-sought-its-user-data\",\"apiUrl\":\"https://content.guardianapis.com/technology/2016/mar/31/reddit-removes-warrant-canary-signaling-us-sought-its-user-data\",\"fields\":{\"thumbnail\":\"http://media.guim.co.uk/373eeb1e02cd8d340096d988d1ab24015751f931/0_0_2560_1536/500.jpg\"},\"isHosted\":false},{\"id\":\"world/2016/mar/31/turkish-president-erdogan-washington-dc-brookings\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2016-04-01T00:43:37Z\",\"webTitle\":\"Turkish journalists in clashes with bodyguards during Erdoğan’s US visit\",\"webUrl\":\"https://www.theguardian.com/world/2016/mar/31/turkish-president-erdogan-washington-dc-brookings\",\"apiUrl\":\"https://content.guardianapis.com/world/2016/mar/31/turkish-president-erdogan-washington-dc-brookings\",\"fields\":{\"thumbnail\":\"http://media.guim.co.uk/d2e2cfd05fb0786cdba3bf0714bb6d6ddcf0705b/0_51_3500_2101/500.jpg\"},\"isHosted\":false},{\"id\":\"music/2016/apr/01/byron-bay-bluesfest-review-mixed-bag-lineup-brings-mixed-bag-crowd-to-waterlogged-but-glorious-weekend\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2016-04-01T00:45:16Z\",\"webTitle\":\"Byron Bay Bluesfest review – mixed bag lineup brings mixed bag crowd to waterlogged but glorious weekend\",\"webUrl\":\"https://www.theguardian.com/music/2016/apr/01/byron-bay-bluesfest-review-mixed-bag-lineup-brings-mixed-bag-crowd-to-waterlogged-but-glorious-weekend\",\"apiUrl\":\"https://content.guardianapis.com/music/2016/apr/01/byron-bay-bluesfest-review-mixed-bag-lineup-brings-mixed-bag-crowd-to-waterlogged-but-glorious-weekend\",\"fields\":{\"thumbnail\":\"http://media.guim.co.uk/e134b6cf674be058d1534aef705dd0ce1ded7a33/0_112_2998_1800/500.jpg\"},\"isHosted\":false},{\"id\":\"world/2016/apr/01/not-fit-to-lead-letter-attacking-xi-jinping-sparks-panic-in-beijing\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2016-04-01T01:22:12Z\",\"webTitle\":\"'Not fit to lead': letter attacking Xi Jinping sparks witch-hunt in Beijing\",\"webUrl\":\"https://www.theguardian.com/world/2016/apr/01/not-fit-to-lead-letter-attacking-xi-jinping-sparks-panic-in-beijing\",\"apiUrl\":\"https://content.guardianapis.com/world/2016/apr/01/not-fit-to-lead-letter-attacking-xi-jinping-sparks-panic-in-beijing\",\"fields\":{\"thumbnail\":\"http://media.guim.co.uk/ca7300e15a21a0f06c3143b842e8e0a635a41d8b/0_183_3725_2236/500.jpg\"},\"isHosted\":false},{\"id\":\"australia-news/2016/apr/01/political-polls-matter-but-the-overall-trend-is-what-to-look-at\",\"type\":\"article\",\"sectionId\":\"australia-news\",\"sectionName\":\"Australia news\",\"webPublicationDate\":\"2016-04-01T01:38:56Z\",\"webTitle\":\"Political polls matter, but the overall trend is what to look at | Ben Raue\",\"webUrl\":\"https://www.theguardian.com/australia-news/2016/apr/01/political-polls-matter-but-the-overall-trend-is-what-to-look-at\",\"apiUrl\":\"https://content.guardianapis.com/australia-news/2016/apr/01/political-polls-matter-but-the-overall-trend-is-what-to-look-at\",\"fields\":{\"thumbnail\":\"http://media.guim.co.uk/ab034a515b20a5da176824ed554cabb5d26e7a43/17_307_2464_1478/500.jpg\"},\"isHosted\":false},{\"id\":\"australia-news/2016/apr/01/were-tired-of-fighting-for-marriage-equality-lets-just-do-it-and-move-on\",\"type\":\"article\",\"sectionId\":\"australia-news\",\"sectionName\":\"Australia news\",\"webPublicationDate\":\"2016-04-01T01:41:17Z\",\"webTitle\":\"Pass marriage equality or there's a gay florist strike around the corner | Benjamin Law\",\"webUrl\":\"https://www.theguardian.com/australia-news/2016/apr/01/were-tired-of-fighting-for-marriage-equality-lets-just-do-it-and-move-on\",\"apiUrl\":\"https://content.guardianapis.com/australia-news/2016/apr/01/were-tired-of-fighting-for-marriage-equality-lets-just-do-it-and-move-on\",\"fields\":{\"thumbnail\":\"http://media.guim.co.uk/e71475bd194e15c2675fdf370ecc522d9ef7912e/0_191_4500_2700/500.jpg\"},\"isHosted\":false}]}}";

        try {
            List<News> newsList = JSONUtils.parseJSON(json);
            newsRecyclerView = (RecyclerView) findViewById(R.id.recycler_book);
            newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            newsRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            newsAdapter = new NewsRecyclerAdapter(this);
            newsRecyclerView.setAdapter(newsAdapter);
            newsAdapter.setNews(newsList);
        }catch (JSONException je){
            je.printStackTrace();
        }catch (ParseException pe){
            pe.printStackTrace();
        }
    }
}
