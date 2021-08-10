package edu.bootcamp.censo2021

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import edu.bootcamp.censo2021.View.ModificarPersonaActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ModificarPersonaActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<ModificarPersonaActivity> = ActivityTestRule(ModificarPersonaActivity::class.java)

    @Test
    fun clickBotonCancelar(){
        Espresso.onView(ViewMatchers.withId(R.id.btn_cancelar)).perform(ViewActions.scrollTo(),ViewActions.click())
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
    }

    @Test
    fun clickGuardar(){
        Espresso.onView(ViewMatchers.withId(R.id.et_direccion)).perform(ViewActions.replaceText("Av. Siria 125"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_telefono)).perform(ViewActions.replaceText("+54 381 454-4789"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_ingreso_mensual)).perform(ViewActions.scrollTo(),ViewActions.replaceText("35000"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_guardar_persona)).perform(ViewActions.scrollTo(),ViewActions.click())
    }
}