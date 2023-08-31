@file:Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")

package com.nedaluof.mihawk

import android.content.Context
import com.nedaluof.mihawk.mifacade.MiHawkFacade
import com.nedaluof.mihawk.mifacade.MiHawkFacadeImpl
import com.nedaluof.mihawk.milogger.MiLogger
import com.nedaluof.mihawk.miutil.MiServiceLocator
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.lang.Exception

/**
 * Created by NedaluOf on 11/10/2021.
 */
object MiHawk {
  private lateinit var miHawkFacade: MiHawkFacade

  fun init(context: Context) {
    miHawkFacade = MiHawkFacadeImpl(MiServiceLocator.provideMiPreferences(context))
  }

  inline fun <reified T> put(key: String, value: T) {
    checkMiHawkFacade()
    miHawkFacade.putData(key, value)
  }

  inline fun <reified T> get(key: String, crossinline result: (T?) -> Unit) {
    checkMiHawkFacade()
    runBlocking {
      result(miHawkFacade.getData(key, T::class.java).first())
    }
  }

  inline fun <reified T> get(key: String): T? {
    checkMiHawkFacade()
    return try {
      runBlocking {
        return@runBlocking miHawkFacade.getData(key, T::class.java).first()
      }
    } catch (e: Exception) {
      null
    }
  }

  inline fun <reified T> get(key: String, defaultValue: T, crossinline result: (T?) -> String) {
    checkMiHawkFacade()
    runBlocking {
      result(miHawkFacade.getData(key, T::class.java).first() ?: defaultValue)
    }
  }

  inline fun <reified T> get(key: String, defaultValue: T): T {
    checkMiHawkFacade()
    return try {
      runBlocking {
        return@runBlocking miHawkFacade.getData(key, T::class.java).first() ?: defaultValue
      }
    } catch (e: Exception) {
      defaultValue
    }
  }

  fun remove(key: String, result: (Boolean) -> Unit) {
    checkMiHawkFacade()
    miHawkFacade.removeData(key, result)
  }

  fun contains(key: String, result: (Boolean) -> Unit) {
    checkMiHawkFacade()
    miHawkFacade.contains(key, result)
  }

  fun deleteAll(result: (Boolean) -> Unit) {
    checkMiHawkFacade()
    miHawkFacade.deleteAll(result)
  }

  private fun checkMiHawkFacade() {
    if (!(this::miHawkFacade.isInitialized)) {
      throw RuntimeException("You Forgot To call MiHawk.init(context)")
    }
  }
}