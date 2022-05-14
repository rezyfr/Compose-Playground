package io.rezyfr.detail

import io.rezyfr.component.base.BaseViewModel
import io.rezyfr.domain.model.MovieDomainModel

class DetailViewModel:
    BaseViewModel<DetailContract.Event, DetailContract.State, DetailContract.Effect>() {
    override fun setInitialState(): DetailContract.State {
        return DetailContract.State()
    }
    override fun handleEvents(event: DetailContract.Event) {

    }
}