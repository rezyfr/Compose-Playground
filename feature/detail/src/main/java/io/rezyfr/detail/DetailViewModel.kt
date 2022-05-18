package io.rezyfr.detail

import androidx.lifecycle.viewModelScope
import io.rezyfr.component.base.BaseViewModel
import io.rezyfr.domain.model.MovieDomainModel
import kotlinx.coroutines.launch

class DetailViewModel(

):
    BaseViewModel<DetailContract.Event, DetailContract.State, DetailContract.Effect>() {
    override fun setInitialState(): DetailContract.State {
        return DetailContract.State.Loading
    }
    override fun handleEvents(event: DetailContract.Event) {

    }

    fun toggleFavorite(favorite: Boolean, movie: MovieDomainModel) {
        viewModelScope.launch {
//            topicsRepository.toggleFollowedTopicId(topicId, favorite)
        }
    }
}