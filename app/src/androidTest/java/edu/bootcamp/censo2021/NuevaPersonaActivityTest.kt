package edu.bootcamp.censo2021

import android.widget.DatePicker
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import edu.bootcamp.censo2021.View.NuevaPersonaActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NuevaPersonaActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<NuevaPersonaActivity> = ActivityTestRule(NuevaPersonaActivity::class.java)

    @Test
    fun clickCancelar(){
        Espresso.onView(ViewMatchers.withId(R.id.btn_cancelar)).perform(ViewActions.scrollTo(),ViewActions.click())
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
    }

    @Test
    fun clickGuardar(){
        Espresso.onView(ViewMatchers.withId(R.id.et_nombre)).perform(ViewActions.typeText("Correa Maria Teresa"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.rb_ci)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.et_numero_documento)).perform(ViewActions.typeText("20989898"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_fecha_nacimiento)).perform(ViewActions.click())
        Espresso.onView(withClassName(Matchers.equalTo(DatePicker::class.java.name))).perform(PickerActions.setDate(1951, 11, 23));
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.et_edad)).perform(ViewActions.typeText("69"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.rb_femenino)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.et_direccion)).perform(ViewActions.typeText("Av. Salta 1256"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_telefono)).perform(ViewActions.typeText("+54 381 478-4789"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_ocupacion)).perform(ViewActions.typeText("Jubilado"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_ingreso_mensual)).perform(ViewActions.typeText("30000"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_guardar_persona)).perform(ViewActions.click())
    }
}