package com.example.feature_favourite_impl.domain.favourites

import com.example.core_app_api.models.TranslatedEntity
import io.reactivex.Completable
import io.reactivex.Single

interface FavouritesInteractor {
    fun deleteFromFavourites(entity: TranslatedEntity): Completable
    fun getFavouritesList(): Single<List<TranslatedEntity>>
}
