package edu.bootcamp.censo2021

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import edu.bootcamp.censo2021.Adapter.PersonaAdapter
import edu.bootcamp.censo2021.View.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.util.regex.Matcher

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickSalir(){
        Espresso.onView(ViewMatchers.withId(R.id.opSalir)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
    }

    @Test
    fun clickNuevaPersona(){
        Espresso.onView(ViewMatchers.withId(R.id.fab_nueva_persona)).perform(ViewActions.click())
    }

    @Test
    fun filtrado(){
        Espresso.onView(ViewMatchers.withId(R.id.sp_filtro_personas)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Por debajo de la línea de pobreza")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.sp_filtro_personas)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Mayores de 18 años desocupadas")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.sp_filtro_personas)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Todas")).perform(ViewActions.click())
    }

    @Test
    fun verDetallesPersona(){
        Espresso.onView(ViewMatchers.withId(R.id.rv_personas)).perform(RecyclerViewActions.actionOnItemAtPosition<PersonaAdapter.ViewHolder>(0,ViewActions.click()))
    }

    @Test
    fun clickEditarPersona(){
        Espresso.onView(ViewMatchers.withId(R.id.rv_personas))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<PersonaAdapter.ViewHolder>(0, object : ViewAction{
                    override fun getConstraints(): org.hamcrest.Matcher<View>? {
                        return null
                    }

                    override fun getDescription(): String {
                        return "Click on specific button"
                    }

                    override fun perform(uiController: UiController?, view: View?) {
                        val button: View = view!!.findViewById(R.id.btn_editar)
                        button.performClick()
                    }
                })
            )
    }

    @Test
    fun clickEliminarPersona(){
        Espresso.onView(ViewMatchers.withId(R.id.rv_personas))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<PersonaAdapter.ViewHolder>(0, object : ViewAction{
                    override fun getConstraints(): org.hamcrest.Matcher<View>? {
                        return null
                    }

                    override fun getDescription(): String {
                        return "Click on specific button"
                    }

                    override fun perform(uiController: UiController?, view: View?) {
                        val button: View = view!!.findViewById(R.id.btn_eliminar)
                        button.performClick()
                    }
                })
            )
    }
}