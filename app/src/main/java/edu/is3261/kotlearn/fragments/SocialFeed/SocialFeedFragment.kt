package edu.is3261.kotlearn.fragments.SocialFeed


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import edu.is3261.kotlearn.R
import edu.is3261.kotlearn.json.RedditFeed
import kotlinx.android.synthetic.main.fragment_social_feed.*
import net.dean.jraw.http.UserAgent
import net.dean.jraw.oauth.OAuthHelper
import net.dean.jraw.RedditClient
import net.dean.jraw.http.OkHttpNetworkAdapter
import net.dean.jraw.models.Listing
import net.dean.jraw.models.Submission
import net.dean.jraw.models.SubredditSort
import net.dean.jraw.models.TimePeriod
import net.dean.jraw.oauth.Credentials
import org.w3c.dom.Text
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SocialFeedFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_social_feed, container, false)

        // initialize the feed
        RedditFeed(view.findViewById(R.id.social_feed)).execute()

        return view
    }


}
