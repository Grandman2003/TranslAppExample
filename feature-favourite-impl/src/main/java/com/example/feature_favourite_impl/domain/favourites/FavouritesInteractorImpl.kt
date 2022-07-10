package com.example.feature_favourite_impl.domain.favourites

import com.example.core_app_api.TranslationApi
import com.example.core_app_api.models.TranslatedEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavouritesInteractorImpl @Inject constructor(
    private val translationApi: TranslationApi
) :
    FavouritesInteractor {
    override fun deleteFromFavourites(entity: TranslatedEntity): Completable =
        translationApi
            .translateInteractor()
            .deleteFavouriteFromDictionary(entity)

    override fun getFavouritesList(): Single<List<TranslatedEntity>> =
        translationApi.translateInteractor()
            .getDictionaryWords()
            .subscribeOn(Schedulers.io())
            .filter { it.isFavourite }
            .toList()
}
