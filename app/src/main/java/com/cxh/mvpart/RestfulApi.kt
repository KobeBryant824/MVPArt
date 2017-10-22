package com.cxh.mvpart

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/7
 */
interface RestfulApi {

    @GET("repos/KobeBryant824/MVPArt/stargazers")
    fun getStargazers(): Observable<String>


}
