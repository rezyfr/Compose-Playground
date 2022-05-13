package io.rezyfr.detail

import io.rezyfr.component.base.BaseViewModel

class DetailViewModel:
    BaseViewModel<DetailContract.Event, DetailContract.State, DetailContract.Effect>() {
    override fun setInitialState(): DetailContract.State {

    }
    override fun handleEvents(event: DetailContract.Event) {

    }
}