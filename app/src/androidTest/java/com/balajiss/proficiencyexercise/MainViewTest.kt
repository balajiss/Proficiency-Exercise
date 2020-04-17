package com.balajiss.proficiencyexercise

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.balajiss.proficiencyexercise.ui.main.MainActivity
import com.balajiss.proficiencyexercise.ui.main.ViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun scrollList() {
        val fragment = rule.activity.supportFragmentManager.fragments[0]
        Thread.sleep(5000)
        Espresso.onView(ViewMatchers.withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(fragment.view!!.findViewById<RecyclerView>(R.id.list).adapter!!.itemCount-9))
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(fragment.view!!.findViewById<RecyclerView>(R.id.list).adapter!!.itemCount-6))
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(fragment.view!!.findViewById<RecyclerView>(R.id.list).adapter!!.itemCount-3))
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(fragment.view!!.findViewById<RecyclerView>(R.id.list).adapter!!.itemCount-1))
    }
}