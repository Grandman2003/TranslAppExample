package com.example.translapptesttask.presentation.presenters

import com.example.core_app_api.TranslatorInteractor
import com.example.core_app_api.models.TranslatedEntity
import com.example.core_app_api.models.TranslatedWord
import com.example.feature_favourite_api.FavouriteFeatureAPI
import com.example.translapptesttask.presentation.view.translator.TranslatorView
import com.example.translapptesttask.presentation.view.translator.`TranslatorView$$State`
import com.github.terrakok.cicerone.Router
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TranslatorPresenterTest {
    @MockK lateinit var favouriteFeatureAPI: FavouriteFeatureAPI
    @MockK lateinit var ciceroneRouter: Router
    @MockK lateinit var interactor: TranslatorInteractor

    @MockK private lateinit var tranlatorView: TranslatorView
    @MockK private lateinit var translatorViewState: `TranslatorView$$State`

    private lateinit var observeScheduler: Scheduler
    private lateinit var translatorPresenterPrivate: TranslatorPresenter
    private lateinit var translatorPresenter: TranslatorPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        observeScheduler = TestScheduler()
        val mockDisposable = mockk<List<TranslatedEntity>>()
        val answerSingle = Single.just(
            spyk(
                TranslatedWord(
                    text = "Translated Word", mockk<List<TranslatedWord.WordMeaning>>()
                )
            )
        )
        translatorPresenterPrivate = TranslatorPresenter(
            favouriteFeatureAPI = favouriteFeatureAPI,
            translatorInteractor = interactor,
            router = ciceroneRouter,
            scheduler = observeScheduler
        )
        translatorPresenter =
            spyk<TranslatorPresenter>(translatorPresenterPrivate, recordPrivateCalls = true)

        every { interactor.getDictionaryWords().toList() } returns Single.just(mockDisposable)
        every { tranlatorView.setDictionaryElements(any()) } returns Unit

        with(translatorPresenterPrivate) {
            attachView(tranlatorView)
            setViewState(translatorViewState)
        }

        every { tranlatorView.showFavouriteError() } throws Exception("Cannot set favourite state")
        every { tranlatorView.showRequestError() } returns Unit
        every { tranlatorView.setDeletingError() } throws Exception("Cannot delete word from Dictionary ")
        every { translatorPresenter invokeNoArgs "updateDictionary" } returns Unit
        every {
            translatorPresenter invoke "addToDictionaryAndCheckFavourite" withArguments listOf(
                mockk<TranslatedWord>()
            )
        } returns Unit

        every { interactor.proceedTranslationRequest(any(), any(), any()) } returns answerSingle
        every { interactor.addWordToDictionary(any()) } returns mockk<Completable>()
        every {
            interactor.proceedTranslationRequest(any(), any(), any()).observeOn(observeScheduler)
        } returns spyk(answerSingle.observeOn(observeScheduler))

        every {
            interactor.proceedTranslationRequest(any(), any(), any()).observeOn(observeScheduler)
                .subscribe(any(), any())
        } returns mockk<Disposable>()
    }

    @Test
    fun `check if interactor is initialised`() {
        assertNotNull(
            "Interactor has not been initialised in test", interactor
        )
        assertNotNull(
            "Presenter has not been initialised in test", translatorPresenter
        )
        assertNotNull(
            "View has not been initialised in test", tranlatorView
        )
        assertNotNull(
            "ViewState has not been initialised in test", translatorViewState
        )
    }

    @Test
    fun `check if presenter works correctly - normal mode`() {
        val wordList: MutableList<String> = mutableListOf<String>(
            "Favourite", "Testing", "Application", "Module"
        )
        wordList.forEach {
            translatorPresenter.translateTextCommand(it, "English", "Russian")
            verify { interactor.proceedTranslationRequest(any(), any(), any()) }
            verify { tranlatorView.setDictionaryElements(any()) }
            verify(exactly = 0) { tranlatorView.showRequestError() }
            verify { interactor.proceedTranslationRequest(any(), any(), any()).observeOn(observeScheduler).subscribe(any(), any()) }
        }
    }
}
