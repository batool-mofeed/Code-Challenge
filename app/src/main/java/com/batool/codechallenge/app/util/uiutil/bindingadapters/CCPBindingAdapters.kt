package com.batool.codechallenge.app.util.uiutil.bindingadapters

import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.hbb20.CountryCodePicker
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
object CCPBindingAdapters {
    @JvmStatic
    @BindingAdapter("countryCode")
    fun CountryCodePicker.setCountryCode(value: MutableStateFlow<String>) {
        textView_selectedCountry?.doOnTextChanged { text, _, _, _ ->
            value.value = text.toString()
        }
    }

    @JvmStatic
    @BindingAdapter("userCountryCode")
    fun CountryCodePicker.setUserCountryCode(countryCode: String) {
        textView_selectedCountry.text = countryCode
    }
}