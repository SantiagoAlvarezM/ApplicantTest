package me.santiagoalvarez.kogiaplicanttest.twitter.feed

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.tweetui.Timeline
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_twitter_landing.*
import me.santiagoalvarez.kogiaplicanttest.R
import me.santiagoalvarez.kogiaplicanttest.di.ActivityScoped
import javax.inject.Inject

/**
 * @author santiagoalvarez
 */
@ActivityScoped
class TwitterLandingFragment @Inject constructor() : DaggerFragment() {

    lateinit var timelineAdapter: TweetTimelineRecyclerViewAdapter
    @Inject lateinit var timeline: Timeline<Tweet>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timelineAdapter = TweetTimelineRecyclerViewAdapter.Builder(context)
                .setTimeline(timeline)
                .build()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_twitter_landing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rVTwitterLandind.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rVTwitterLandind.adapter = timelineAdapter
    }
}
