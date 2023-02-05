package app.trian.socialapp.viewmodeltest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelTest : ViewModel() {
    private var _isShowSetting = MutableLiveData<Boolean>()
    val isShowSetting: LiveData<Boolean> = _isShowSetting

    fun showSetting(show: Boolean) {
        _isShowSetting.value = show
    }
}