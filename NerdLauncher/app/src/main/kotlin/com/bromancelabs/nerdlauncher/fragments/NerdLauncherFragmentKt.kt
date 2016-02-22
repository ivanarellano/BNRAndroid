package com.bromancelabs.nerdlauncher.fragments

import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bromancelabs.nerdlauncher.R
import kotlinx.android.synthetic.main.fragment_nerd_launcher.*
import java.util.*


class NerdLauncherFragmentKt : Fragment() {

    companion object {
        val TAG = NerdLauncherFragmentKt::class.java.simpleName
        fun newInstance() = NerdLauncherFragmentKt()
    }

    val recylerView by lazy { rv_fragment_nerd_launcher }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_nerd_launcher, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupAdapter()

        recylerView.layoutManager = LinearLayoutManager(activity)
    }

    fun setupAdapter() {
        val startupIntent = Intent(Intent.ACTION_MAIN)
        startupIntent.addCategory(Intent.CATEGORY_LAUNCHER)

        val activities: List<ResolveInfo> = activity.packageManager.queryIntentActivities(startupIntent, 0)
        if (!activities.isEmpty()) {
            Log.i(TAG, "Found ${activities.size} activities")

            activities.sortByCaseInsensitive({ it.loadLabel(activity.packageManager).toString() })
            recylerView.adapter = ActivityAdapterKt(activities)
        }
    }

    fun <T> List<T>.sortByCaseInsensitive(transform: (T) -> String) {
        Collections.sort(this, { a, b ->
            String.CASE_INSENSITIVE_ORDER.compare(transform(a), transform(b))
        })
    }

    inner class ActivityAdapterKt(val activities: List<ResolveInfo>) : RecyclerView.Adapter<ActivityHolderKt>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityHolderKt? {
            val view = LayoutInflater.from(activity).inflate(android.R.layout.simple_list_item_1, parent, false)
            return ActivityHolderKt(view)
        }

        override fun getItemCount() = activities.size

        override fun onBindViewHolder(activityHolder: ActivityHolderKt, position: Int) = activityHolder.bindActivity(activities[position])
    }

    inner class ActivityHolderKt(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameTextView: TextView
        var resolveInfo: ResolveInfo? = null

        init {
            nameTextView = itemView as TextView
            nameTextView.setOnClickListener {
                resolveInfo?.activityInfo?.let {
                    val intent = Intent(Intent.ACTION_MAIN)
                            .setClassName(it.applicationInfo.packageName, it.name)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            }
        }

        fun bindActivity(resolveInfo: ResolveInfo) {
            this.resolveInfo = resolveInfo
            nameTextView.text = resolveInfo.loadLabel(activity.packageManager).toString()
        }
    }
}