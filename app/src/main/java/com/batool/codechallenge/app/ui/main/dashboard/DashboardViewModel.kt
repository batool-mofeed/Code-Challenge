package com.batool.codechallenge.app.ui.main.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.batool.codechallenge.R
import com.batool.codechallenge.app.base.BaseViewModel
import com.batool.codechallenge.data.datasource.remote.responsemodel.Article
import com.batool.codechallenge.domain.model.Resource
import com.batool.codechallenge.domain.usecases.GeneralUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val generalUseCases: GeneralUseCases
) : BaseViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    val articles = MutableLiveData<List<Article>>()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getViewedArticles() {
        generalUseCases.getViewedArticles(
            viewModelScope,
        ) { resource ->
            when (resource) {
                is Resource.Error -> _errorMessage.value = resource.error
                is Resource.Loading -> {
                    _loading.value = resource.loading
                }
                is Resource.Success -> {
                    articles.postValue(resource.data?.results?.map {
                        val formatter =
                            java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                        val dateTime = LocalDateTime.parse(it.updated, formatter)
                        it.calculatedDate = dateTime.differenceToNow()
                        it
                    } ?: emptyList())
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun LocalDateTime.differenceToNow(): String {
        val now = LocalDateTime.now()
        val years = ChronoUnit.YEARS.between(this, now)
        val months = ChronoUnit.MONTHS.between(this, now) % 12
        val weeks = ChronoUnit.WEEKS.between(this, now)
        val days = ChronoUnit.DAYS.between(this, now)
        val hours = ChronoUnit.HOURS.between(this, now)
        var humanTime = ""
        if (years != 0L) {
            humanTime += if (years > 1) {
                " $years ${resourceProvider.provideString(R.string.years)},"
            } else {
                " $years ${resourceProvider.provideString(R.string.year)},"
            }
        }
        if (months != 0L) {
            humanTime += if (months > 1) {
                " $months ${resourceProvider.provideString(R.string.months)},"
            } else {
                " $months ${resourceProvider.provideString(R.string.month)},"
            }
        }
        if (weeks != 0L) {
            humanTime += if (weeks > 1) {
                " $weeks ${resourceProvider.provideString(R.string.weeks)},"
            } else {
                " $weeks ${resourceProvider.provideString(R.string.week)},"
            }
        }
        if (days != 0L) {
            humanTime += if (days > 1) {
                " $days ${resourceProvider.provideString(R.string.days)},"
            } else {
                " $days ${resourceProvider.provideString(R.string.day)},"
            }
        }
        if (hours != 0L) {
            humanTime += if (hours > 1) {
                "$hours ${resourceProvider.provideString(R.string.hours)}"
            } else {
                "$hours ${resourceProvider.provideString(R.string.hour)}"
            }
        }
        humanTime += " ${resourceProvider.provideString(R.string.ago)}"
        return humanTime
    }

}